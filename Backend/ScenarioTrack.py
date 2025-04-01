class ScenarioTrack:
    def __init__(self):
        self.subscenario_progress = 0
        self.scenario_number = 0
        self.scenario_data = []

    def update_progress(self):
        self.subscenario_progress += 1

    def set_scenario_data(self, docker_dir_path):
        md_file = docker_dir_path + "/Aufgabenstellung.md"
        scenario_list = []

        with open(md_file) as file:
            lines = file.readlines()
            current_hint = ""
            current_description = ""
            current_solution = ""

            prev_row_hint = False
            prev_row_desc = False

            for l in lines:
                l = l.strip()
                
                if l.startswith("\_"):
                    if "`" in l:
                        current_solution = l.split("`", 2)[1]
                        print(current_solution)
                        l = l.replace(current_solution, "***redacted***")
                    current_hint += l.lstrip("\_")
                    prev_row_hint = True
                    prev_row_desc = False

                elif l.startswith("!!"):
                    current_description += l[2:] + " "
                    prev_row_desc = True
                    prev_row_hint = False

                elif l.startswith("###") or l == "# EOF":
                    if current_hint or current_description or current_solution:
                        scenario_list.append({
                            "hint": current_hint.strip(),
                            "solution": current_solution.strip(),
                            "description": current_description.strip()
                        })
                    
                    if l.startswith("###"):
                        self.scenario_number += 1

                    current_hint = ""
                    current_solution = ""
                    current_description = ""
                    prev_row_hint = False
                    prev_row_desc = False

        self.scenario_data = scenario_list

        for scenario in self.scenario_data:
            print(scenario)

    def get_clue(self):
        return self.scenario_data[self.subscenario_progress].get("hint", "")

    def get_desc(self):
        return self.scenario_data[self.subscenario_progress].get("description", "")
    
    def get_solution(self):
        return self.scenario_data[self.subscenario_progress].get("solution", "")