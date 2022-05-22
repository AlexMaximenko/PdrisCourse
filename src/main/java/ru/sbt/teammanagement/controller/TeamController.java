package ru.sbt.teammanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.teammanagement.entities.Employee;
import ru.sbt.teammanagement.service.TeamService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/createTeam")
    public void createTeam(String team_name, int teamlead_id){
        teamService.createTeam(team_name, teamlead_id);
    }

    @GetMapping("/getTeamMembers")
    public Set<Employee> getTeamMembers(String team_name){
        return teamService.getTeamMembers(team_name);
    }

    @GetMapping("/addEmployee")
    public void addEmployee(Integer employee_id, Integer team_id){
        teamService.addEmployee(employee_id, team_id);
    }
}
