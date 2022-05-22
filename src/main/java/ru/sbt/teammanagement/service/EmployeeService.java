package ru.sbt.teammanagement.service;

import org.springframework.stereotype.Service;
import ru.sbt.teammanagement.entities.Employee;
import ru.sbt.teammanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(String name, String role, String position){
        this.employeeRepository.save(new Employee(name, role, position));
    }
}
