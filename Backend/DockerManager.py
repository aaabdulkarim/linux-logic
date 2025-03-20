import docker

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

    
    async def createDockerContainer(self, userSessionId, userName, frontendChoice):

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


    


    async def addConnection(self, userSessionId, userName, frontendChoice):
        container_name  = await self.createDockerContainer(userSessionId, userName, frontendChoice)
        print("Created container : " + container_name)
        if container_name:
            self.userContainerConnections[container_name] = container_name  
            print(f"Container gestartet: {container_name}")
            return container_name
        else:
            print("Fehler beim Erstellen des Containers")
            return None

    async def close(self, userSessionId):
        """
        Schließt Container und löscht Connection in der Liste
        """
