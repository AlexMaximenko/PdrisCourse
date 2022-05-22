package ru.sbt.teammanagement.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "team_employee",
            joinColumns = @JoinColumn(name="team_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
    )
    private Set<Employee> employees = new HashSet<>();

    public Team(String name, int team_lead_id) {
        this.name = name;
        this.team_lead_id = team_lead_id;
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee);
        employee.getTeams().add(this);
    }

    private int team_lead_id;

    public void setId(int team_id) {
        this.id = team_id;
    }

    public void setTeam_lead_id(int team_lead_id) {
        this.team_lead_id = team_lead_id;
    }

    public void setName(String team_name) {
        this.name = team_name;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public int getTeam_lead_id() {
        return team_lead_id;
    }

    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Team() {
    }
}
