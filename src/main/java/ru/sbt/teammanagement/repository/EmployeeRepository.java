package ru.sbt.teammanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbt.teammanagement.entities.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findEmployeeById(Integer employee_id);

    public boolean existsEmployeeById(Integer employee_id);
}
