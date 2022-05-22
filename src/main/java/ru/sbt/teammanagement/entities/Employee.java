package ru.sbt.teammanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String role;
    private String position;

    @JsonIgnore
    @ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Team> teams = new HashSet<>();

    public Employee(String name, String role, String position) {
        this.name = name;
        this.role = role;
        this.position = position;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPosition() {
        return position;
    }

    public void setId(int employee_id) {
        this.id = employee_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
