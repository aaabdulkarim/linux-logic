import docker
from ScenarioTrack import ScenarioTrack
from UserDockerConnection import UserDockerConnection

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
            
            # TODO: Dynamische Ports
            container = self.client.containers.run(
                container_tag, 
                name=container_name, 
                ports={1000: None}, 
                detach=True)
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
        user_container_connection = self.userContainerConnections.get(userName + frontendChoice)
        if user_container_connection is not None:
            return user_container_connection.container_name




    async def add_connection(self, userSessionId, userName, frontendChoice):
        container_name  = await self.create_docker_container(userSessionId, userName, frontendChoice)
        scm = ScenarioTrack()
        scm.set_scenario_data("scenarios/"+frontendChoice)
        

        print("Created container : " + container_name)
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



    async def close(self, container_name):
        """
        Schließt Container und löscht Connection in der Liste
        """
        container = self.client.containers.get(container_name)
        container.stop()
        container.remove()
