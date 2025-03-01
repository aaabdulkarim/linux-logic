class ScenarioTrack:
    def __init__(self):
        self.scenario_index = 0
        self.scenario_data = []
        

    def set_scenario_data(self, new_scenario_data):
        self.scenario_data = new_scenario_data



    def set_scenario_data(self, docker_dir_path):
        # Liste von Tuples
        scenario_list = []
        md_file = docker_dir_path + "/Aufgabenstellung.md"
        print(md_file)
        with open(md_file) as file:
            lines = file.readlines()

            current_hint
            for l in lines:

                
                # Falls ein neues Subszenario anfängt, ein neues Tuple zur Liste hinzufügen
                if l[0:3] == "###":
                    

                elif l[0:2] == "\_" :
                    current_hint += l[1:]
    

        self.set_scenario_data(scenario_list)


    def get_clue(self, userScenarioIndex):
        pass