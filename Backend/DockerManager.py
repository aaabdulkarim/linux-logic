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
    
    userContainerConnections = []    
    client = docker.from_env()

    
    def createDockerContainer(self, userSessionId, userName, frontendChoice):

        docker_dir_path = f"scenarios/{frontendChoice}"

        container_tag = userName+frontendChoice 
        container_name = userSessionId[:4] + frontendChoice + userName
        """
        Erstellt von einer Auswahl an Namen einen neuen Namen mit Zahlen der userSessionId
        """
        client = docker.from_env()
        try:
            client.images.build(path=docker_dir_path, tag=container_tag)
            
            container = client.containers.run(container_tag, name=container_name, ports={1000: 1000}, detach=True)
            return container


        except docker.errors.DockerException as e:
            print(f"Error: {e}")


    # https://stackoverflow.com/questions/60291082/wait-for-docker-container-healthcheck-to-succeed-before-detaching
    def get_container_health(self, container):
        api_client = docker.APIClient()
        inspect_results = api_client.inspect_container(container.name)
        return inspect_results['State']['Health']['Status']


    


    def addConnection(self, userSessionId, userName, frontendChoice):
        container = self.createDockerContainer(userSessionId, userName, frontendChoice)
        try:
            userDockerConnection = {
                userSessionId : container
            }
            self.userContainerConnections.append(userDockerConnection)
                

        except ValueError:
            print("sessionId Value is invalid")

    def close(self, userSessionId):
        """
        Schließt Container und löscht Connection in der Liste
        """
