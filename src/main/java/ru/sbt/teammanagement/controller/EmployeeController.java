package ru.sbt.teammanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.teammanagement.entities.Employee;
import ru.sbt.teammanagement.repository.EmployeeRepository;
import ru.sbt.teammanagement.service.EmployeeService;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/createEmployee")
    public void createEmployee(String name, String role, String position){
        this.employeeService.createEmployee(name, role, position);
    }
}
