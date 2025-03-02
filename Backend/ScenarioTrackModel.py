class ScenarioTrack:
    def __init__(self):
        self.scenario_number = 0
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

            current_hint = ""
            prev_row_hint = False
            for l in lines:

                # Falls ein neues Subszenario anfängt, ein neues Tuple zur Liste hinzufügen
                if l[0:2] == "\_" :
                    current_hint += l[1:]
                    prev_row_hint = True

                elif prev_row_hint:
                    #append to list
                    self.scenario_data.append((self.scenario_number, current_hint))
                    prev_row_hint = False
                    current_hint = ""

                elif l[0:3] == "###":
                    self.scenario_number += 1


                
        for da in self.scenario_data:
            print(da)

        self.set_scenario_data(scenario_list)


    def get_clue(self, userScenarioIndex):
        pass