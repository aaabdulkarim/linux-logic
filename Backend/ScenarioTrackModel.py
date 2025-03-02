class ScenarioTrack:
    def __init__(self):
        self.scenario_number = 0
        self.scenario_data = []
        


    def set_scenario_data(self, docker_dir_path):
        # Liste von Tuples
        scenario_list = []
        md_file = docker_dir_path + "/Aufgabenstellung.md"

        with open(md_file) as file:
            lines = file.readlines()

            print(lines[-1])

            current_hint = ""
            prev_row_hint = False
            for l in lines:
                print(l is lines[-1])

                # Falls ein neues Subszenario anfängt, ein neues Tuple zur Liste hinzufügen
                if l[0:2] == "\_" :
                    current_hint += l[1:]
                    prev_row_hint = True

                elif prev_row_hint or l == "# EOF":

                    # Nur falls es Hinweise zum Hinzufügen gibt
                    if current_hint != "":
                        #append to list
                        scenario_list.append((self.scenario_number, current_hint))
                        prev_row_hint = False
                        current_hint = ""

                elif l[0:3] == "###":
                    
                    self.scenario_number += 1
                    print(l[3:])


        self.scenario_data = scenario_list

        for da in self.scenario_data:
            print(da)



    def get_clue(self, userScenarioIndex):
        pass