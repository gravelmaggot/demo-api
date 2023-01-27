package com.test.demo.service;

import com.test.demo.models.Employee;
import com.test.demo.models.Role;
import com.test.demo.models.Sale;
import com.test.demo.repo.EmployeeRepo;
import com.test.demo.repo.RoleRepo;
import com.test.demo.repo.SaleRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Named
public class EmployeeService {

    EmployeeRepo employeeRepo;
    RoleRepo roleRepo;
    SaleRepo saleRepo;

    @Inject
    EmployeeService(EmployeeRepo employeeRepo, RoleRepo roleRepo, SaleRepo saleRepo) {
        this.employeeRepo = employeeRepo;
        this.roleRepo = roleRepo;
        this.saleRepo = saleRepo;
    }

    // TODO: Criar CalculateUtil ou BaseService para reaproveitar e simplificar o código. Criar objeto Response para padronizar o retorno.
    public Double getSalaryAndBenefitsPaid(Integer month, Integer year) {
        List<Employee> employees = employeeRepo.getEmployees(month, year);
        List<Role> roles = (List<Role>) roleRepo.findAll();

        Double totalValue = 0.0;
        for (Employee employee : employees) {
            Role employeeRole = roles.stream().filter(role -> Objects.equals(role.id, employee.roleId)).findFirst().orElseThrow();

            //Salary + yearly raises
            Double totalSalary = employeeRole.salary + (employeeRole.salaryModifier * (year - employee.hireYear));

            Double benefit = getBenefits(totalSalary, employee, employeeRole, month, year);

            totalValue += (totalSalary + benefit);
        }

        return totalValue;
    }

    public Double getSalaryPaid(Integer month, Integer year) {
        List<Employee> employees = employeeRepo.getEmployees(month, year);
        List<Role> roles = (List<Role>) roleRepo.findAll();

        Double totalValue = 0.0;
        for (Employee employee : employees) {
            Role employeeRole = roles.stream().filter(role -> Objects.equals(role.id, employee.roleId)).findFirst().orElseThrow();

            //Salary + yearly raises
            Double totalSalary = employeeRole.salary + (employeeRole.salaryModifier * (year - employee.hireYear));

            totalValue += totalSalary;
        }

        return totalValue;
    }

    public Double getBenefitsPaid(Integer month, Integer year) {
        List<Employee> employees = employeeRepo.getEmployeesWithBenefits(month, year);
        List<Role> roles = (List<Role>) roleRepo.findAll();

        Double totalValue = 0.0;
        for (Employee employee : employees) {
            Role employeeRole = roles.stream().filter(role -> Objects.equals(role.id, employee.roleId)).findFirst().orElseThrow();

            //Salary + yearly raises
            Double totalSalary = employeeRole.salary + (employeeRole.salaryModifier * (year - employee.hireYear));

            Double benefit = getBenefits(totalSalary, employee, employeeRole, month, year);

            totalValue += benefit;
        }

        return totalValue;
    }

    public Employee getHighestPaidEmployee(Integer month, Integer year) {
        List<Employee> employees = employeeRepo.getEmployees(month, year);
        List<Role> roles = (List<Role>) roleRepo.findAll();

        Double highestValue = 0.0;
        Employee highestPaidEmployee = new Employee();
        for (Employee employee : employees) {
            Role employeeRole = roles.stream().filter(role -> Objects.equals(role.id, employee.roleId)).findFirst().orElseThrow();

            //Salary + yearly raises
            Double totalSalary = employeeRole.salary + (employeeRole.salaryModifier * (year - employee.hireYear));

            Double benefit = getBenefits(totalSalary, employee, employeeRole, month, year);

            // Total Salary + benefits
            Double totalValue = (totalSalary + benefit);

            if(totalValue > highestValue) {
                highestPaidEmployee = employee;
            }
            highestValue = highestValue > totalValue ? highestValue : totalValue;
        }

        return highestPaidEmployee;
    }

    public Employee getHighestBenefitsEmployee(Integer month, Integer year) {
        List<Employee> employees = employeeRepo.getEmployeesWithBenefits(month, year);
        List<Role> roles = (List<Role>) roleRepo.findAll();

        Double highestBenefits = 0.0;
        Employee highestBenefitsEmployee = new Employee();
        for (Employee employee : employees) {
            Role employeeRole = roles.stream().filter(role -> Objects.equals(role.id, employee.roleId)).findFirst().orElseThrow();

            //Salary + yearly raises
            Double totalSalary = employeeRole.salary + (employeeRole.salaryModifier * (year - employee.hireYear));

            Double benefit = getBenefits(totalSalary, employee, employeeRole, month, year);

            if(benefit > highestBenefits) {
                highestBenefitsEmployee = employee;
            }
            highestBenefits = highestBenefits > benefit ? highestBenefits : benefit;
        }

        return highestBenefitsEmployee;
    }

    public Employee getHighestSaleEmployee(Integer month, Integer year) {
        List<Sale> saleList = saleRepo.getBySaleMonthAndSaleYear(month, year);

        Sale highestSale = saleList.stream().max(Comparator.comparing(sale -> sale.sale)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return employeeRepo.findById(highestSale.id).orElseThrow();
    }

    private Double getBenefits(Double totalSalary, Employee employee, Role employeeRole, Integer month, Integer year) {
        Double benefit = totalSalary * employeeRole.benefits;

        // Calculates benefits over sale if employee is a salesperson
        if (employeeRole.role.equalsIgnoreCase("vendedor")) {
            Double monthSale = saleRepo.getByIdAndSaleMonthAndSaleYear(employee.id, month, year).orElse(new Sale(0, 0.0, 0, 0)).sale;
            benefit = monthSale * employeeRole.benefits;
        }

        return benefit;
    }

}
