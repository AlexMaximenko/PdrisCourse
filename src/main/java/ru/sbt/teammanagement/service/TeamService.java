package ru.sbt.teammanagement.service;

import org.springframework.stereotype.Service;
import ru.sbt.teammanagement.entities.Employee;
import ru.sbt.teammanagement.entities.Team;
import ru.sbt.teammanagement.repository.EmployeeRepository;
import ru.sbt.teammanagement.repository.TeamRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    public TeamService(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    public void createTeam(String team_name, Integer teamlead_id){
        if (teamRepository.existsTeamByName(team_name)){
            throw new RuntimeException("Team already exists, name=" + team_name);
        }
        Employee employee = employeeRepository.findEmployeeById(teamlead_id).orElseThrow(() ->
                new RuntimeException("Employee is not found, id="+teamlead_id));

        Team team = new Team(team_name, teamlead_id);
        team.addEmployee(employee);
        teamRepository.save(team);
    }

    public Set<Employee> getTeamMembers(String team_name) {
        return teamRepository.findTeamByName(team_name).orElseThrow(() ->
                new RuntimeException("Team is not found, name=" + team_name)
        ).getEmployees();
    }

    public void addEmployee(Integer employee_id, Integer team_id) {
        Employee employee = employeeRepository.findEmployeeById(employee_id).orElseThrow(() ->
                new RuntimeException("Employee is not found, id=" + employee_id));

        Team team = teamRepository.findTeamById(team_id).orElseThrow(() ->
                new RuntimeException("Team is not found, id="+team_id));

        team.addEmployee(employee);
        teamRepository.save(team);
    }
}
