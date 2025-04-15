import docker
from ScenarioTrack import ScenarioTrack
from UserDockerConnection import UserDockerConnection
from datetime import datetime, timedelta


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
            print(f"Error: {e}")

    async def get_dynamic_port(self, container_name):
        container = self.client.containers.get(container_name)
        ports = container.attrs['NetworkSettings']['Ports']
        print(ports)
        return ports['1000/tcp'][0]['HostPort']


    # https://stackoverflow.com/questions/60291082/wait-for-docker-container-healthcheck-to-succeed-before-detaching
    async def get_container_health(self, container_name):
        api_client = docker.APIClient()
        inspect_results = api_client.inspect_container(container_name)
        return inspect_results['State']['Health']['Status']


    async def reconnect(self, userSessionId, userName, frontendChoice):
        return self.userContainerConnections.get(userName + frontendChoice)  # Return full connection object


    async def add_connection(self, userSessionId, userName, frontendChoice):
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
        connectionKey = userName + frontendChoice
        try:
            user_container_connection = self.userContainerConnections[connectionKey]
            return user_container_connection.scm


        except Exception:
            print("Couldn't find connection with connection key: " + connectionKey)



    async def close(self, userName, frontendChoice):
        """
        Schließt Container und löscht Connection in der Liste
        """
        try:
            connectionKey = userName + frontendChoice
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

    async def start_auto_delete_containers(self):
        print(self.userContainerConnections)
