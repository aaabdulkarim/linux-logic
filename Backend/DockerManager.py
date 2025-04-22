import docker

from ScenarioTrack import ScenarioTrack
from UserDockerConnection import UserDockerConnection
from datetime import datetime, timedelta
import asyncio


RUNNING_CONTAINER_LIMIT = 100

class DockerManager():

    """
    Jeder Container-Userverbindung beinhaltet auch ein ScenarioTrackModel.
    Jede Verbindung bietet folgende Funktionen:
    - Container Starten
    - Verbindung Schließen
    - Socket starten


    userId inform von SessionId die bei Socket Verbindung mitgegeben wird
    """
    
    userContainerConnections = {} 
    client = docker.from_env()
    

    def get_connection_by_key(self, connectionKey):
        try:
            user_container_connection = self.userContainerConnections[connectionKey]
            return user_container_connection


        except KeyError:
            print("Couldn't find connection with connection key: " + connectionKey)


    async def create_docker_container(self, userSessionId, userName, frontendChoice):

        docker_dir_path = f"scenarios/{frontendChoice}"

        container_tag = userName + frontendChoice
        container_name = f"{userName}_{frontendChoice}_{userSessionId[:8].replace('-', '')}"

        try:
            self.client.images.build(path=docker_dir_path, tag=container_tag)
            
            container = self.client.containers.run(
                container_tag,
                name=container_name,
                ports={"1000/tcp": None},  
                detach=True,
                privileged=False,
                mem_limit="512m",
                nano_cpus=500_000_000,
                pids_limit=100,
                security_opt=["no-new-privileges"],
                # Kein network_mode hier
            )
            return container_name  


        except docker.errors.DockerException as e:
            # TODO: Löschen vom Dictionary Eintrag
            print(f"Error: {e}")

    async def get_dynamic_port(self, container_name):
        try:

            container = self.client.containers.get(container_name)
            ports = container.attrs['NetworkSettings']['Ports']
            print(ports)
            return ports['1000/tcp'][0]['HostPort']
        
        except docker.errors.DockerException as e:
            # TODO: Löschen vom Dictionary Eintrag

            print(f"Error: {e}")


    # https://stackoverflow.com/questions/60291082/wait-for-docker-container-healthcheck-to-succeed-before-detaching
    async def get_container_health(self, container_name):
        try:
            api_client = docker.APIClient()
            inspect_results = api_client.inspect_container(container_name)
            return inspect_results['State']['Health']['Status']
        
        except docker.errors.DockerException as e:
            # TODO: Löschen vom Dictionary Eintrag

            print(f"Error: {e}")

    async def reconnect(self, userSessionId, userName, frontendChoice):
        return self.get_connection_by_key(userName+frontendChoice)


    async def add_connection(self, userSessionId, userName, frontendChoice):
        if len(self.userContainerConnections) >= RUNNING_CONTAINER_LIMIT:
            return None
        
        # Before adding or reconnect, check if number of already running containers is exceeding the RUNNING_CONTAINER_LIMIT
        existing_connection = await self.reconnect(userSessionId, userName, frontendChoice)

        if existing_connection:
            print(f"Reusing existing container: {existing_connection.container_name}")
            return existing_connection.container_name  # Keep the same SCM object

        container_name  = await self.create_docker_container(userSessionId, userName, frontendChoice)
        scm = ScenarioTrack()
        scm.set_scenario_data("scenarios/"+frontendChoice)
        

        print("Created container : ", container_name)
        if container_name:
            self.userContainerConnections[userName + frontendChoice] = UserDockerConnection(scm, container_name)  
            print(f"Container gestartet: {container_name}")
            return container_name
        else:
            print("Fehler beim Erstellen des Containers")
            return None


    async def get_scm(self, userName, frontendChoice):
        user_container_connection = self.get_connection_by_key(userName+frontendChoice)
        return user_container_connection.scm



    async def close_by_key(self, connectionKey):
        """
        Schließt Container und löscht Connection in der Liste
        """
        try:
            # Get Container name
            userDockerConnection = self.userContainerConnections.get(connectionKey)

            # Get Container
            container = self.client.containers.get(userDockerConnection.container_name)

            # Remove Container from Connection Dictionary and Stop with docker client
            container.stop()
            container.remove()
            self.userContainerConnections.pop(connectionKey)

        except Exception as e:
            print(e)
            print("Couldn't find container or it was found and couldn't be stopped/removed")



    async def close(self, userName, frontendChoice):
        await self.close_by_key(userName+frontendChoice)


    async def start_auto_delete_containers(self):
        print("Starting Task, check for obsolete Container")
        while True:
            try:
                now = datetime.now()
                to_delete = []
                
                for key, conn in list(self.userContainerConnections.items()):
                    # 60*24 ist ein
                    if (now - conn.last_interaction) > timedelta(days=2):
                        print(f"Auto-deleting inactive container: {conn.container_name}")
                        print(key)
                        await self.close_by_key(key)
                        to_delete.append(key)

            
                await asyncio.sleep(600)  
            except Exception as e:
                print(f"Error in auto-delete task: {e}")


    async def delete_containers_not_in_userlist(self):
        # TODO: Implement function reading all containers and deleting unneccessary ones
        pass

    async def update_last_interaction(self, userName, frontendChoice):
        connectionKey = userName + frontendChoice
        # Get Container name
        userDockerConnection = self.get_connection_by_key(connectionKey)

        userDockerConnection.update_interaction()
