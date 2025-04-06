class ScenarioTrack:
    def __init__(self):
        self.subscenario_progress = 0
        self.scenario_number = 0
        self.scenario_data = []
        self.clues_used = 0
        self.solutions_used = 0

    def update_progress(self):
        self.subscenario_progress += 1

    def set_scenario_data(self, docker_dir_path):
        md_file = docker_dir_path + "/Aufgabenstellung.md"
        scenario_list = []

        with open(md_file,  encoding="utf-8") as file:
            lines = file.readlines()
            current_hint = ""
            current_description = ""
            current_solution = ""
            parsing_solution = False  
            
            for l in lines:
                l = l.strip()

                if l.startswith("!!"):
                    if current_description or current_hint or current_solution:
                        scenario_list.append({
                            "hint": current_hint.strip(),
                            "solution": current_solution.strip(),
                            "description": current_description.strip()
                        })
                        current_hint = ""
                        current_solution = ""
                    
                    current_description = l[2:].strip()
                    parsing_solution = False 

                elif l.startswith("\_"):
                    current_hint = l.lstrip("\_").strip()
                    parsing_solution = False 

                elif l.startswith("`"):  
                    current_solution = l.strip()
                    parsing_solution = True  

                elif l.startswith("###") or l == "# EOF":
                    # Save last scenario before moving on
                    if current_description or current_hint or current_solution:
                        scenario_list.append({
                            "hint": current_hint.strip(),
                            "solution": current_solution.strip("`"),
                            "description": current_description.strip()
                        })
                    current_hint = ""
                    current_solution = ""
                    current_description = ""
                    parsing_solution = False  


            if current_description or current_hint or current_solution:
                scenario_list.append({
                    "hint": current_hint.strip(),
                    "solution": current_solution.strip(),
                    "description": current_description.strip()
                })

        self.scenario_data = scenario_list

        for scenario in self.scenario_data:
            print(scenario)


    def get_clue(self):
        self.clues_used += 1
        return self.scenario_data[self.subscenario_progress].get("hint", "")

    def get_desc(self):
        return self.scenario_data[self.subscenario_progress].get("description", "")
    
    def get_solution(self):
        self.solutions_used += 1
        return self.scenario_data[self.subscenario_progress].get("solution", "")


    def is_last_level(self):
        return self.subscenario_progress == len(self.scenario_data) - 1