package com.test.demo.repo;

import com.test.demo.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
    @Query(value = "SELECT new Employee(emp.id, emp.name, emp.roleId, emp.hireMonth, emp.hireYear) FROM Employee emp WHERE emp.hireMonth <= (?1) AND emp.hireYear <= (?2)")
    List<Employee> getEmployees(Integer month, Integer year);

    @Query(value = "SELECT new Employee(emp.id, emp.name, emp.roleId, emp.hireMonth, emp.hireYear) FROM Employee emp JOIN Role role ON (emp.roleId = role.id) WHERE emp.hireMonth <= (?1) AND emp.hireYear <= (?2) AND role.benefits > 0")
    List<Employee> getEmployeesWithBenefits(Integer month, Integer year);
}
