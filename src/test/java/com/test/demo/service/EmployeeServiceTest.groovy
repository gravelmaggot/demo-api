package com.test.demo.service

import com.test.demo.models.Employee
import com.test.demo.models.Role
import com.test.demo.models.Sale
import com.test.demo.repo.EmployeeRepo
import com.test.demo.repo.RoleRepo
import com.test.demo.repo.SaleRepo
import spock.lang.Specification

class EmployeeServiceTest extends Specification {

    EmployeeService service
    EmployeeRepo employeeRepo
    RoleRepo roleRepo
    SaleRepo saleRepo

    void setup() {
        employeeRepo = Mock(EmployeeRepo)
        roleRepo = Mock(RoleRepo)
        saleRepo = Mock(SaleRepo)
        service = new EmployeeService(employeeRepo, roleRepo, saleRepo)
    }

    //TODO: Adicionar casos de teste para erros

    void "should get total salary and benefits paid"() {
        when:
        service.getSalaryAndBenefitsPaid(12, 2022)
        then:
        1 * employeeRepo.getEmployees(_ as Integer, _ as Integer) >> [new Employee(1, "test", 1, 12, 2022)]
        1 * roleRepo.findAll() >> [new Role(1, "vendedor", 2000.00, 350.00, 0.2)]
        1 * saleRepo.getByIdAndSaleMonthAndSaleYear(_ as Integer, _ as Integer, _ as Integer) >> Optional.of(new Sale(1, 250.00, 12, 2022))
        0 * _
    }

    void "should get total salary paid"() {
        when:
        service.getSalaryPaid(12, 2022)
        then:
        1 * employeeRepo.getEmployees(_ as Integer, _ as Integer) >> [new Employee(1, "test", 1, 12, 2022)]
        1 * roleRepo.findAll() >> [new Role(1, "vendedor", 2000.00, 350.00, 0.2)]
        0 * _
    }

    void "should get total benefits paid"() {
        when:
        service.getBenefitsPaid(12, 2022)
        then:
        1 * employeeRepo.getEmployeesWithBenefits(_ as Integer, _ as Integer) >> [new Employee(1, "test", 1, 12, 2022)]
        1 * roleRepo.findAll() >> [new Role(1, "vendedor", 2000.00, 350.00, 0.2)]
        1 * saleRepo.getByIdAndSaleMonthAndSaleYear(_ as Integer, _ as Integer, _ as Integer) >> Optional.of(new Sale(1, 250.00, 12, 2022))
        0 * _
    }

    void "should get highest paid employee"() {
        when:
        service.getHighestPaidEmployee(12, 2022)
        then:
        1 * employeeRepo.getEmployees(_ as Integer, _ as Integer) >> [new Employee(1, "test", 1, 12, 2022)]
        1 * roleRepo.findAll() >> [new Role(1, "vendedor", 2000.00, 350.00, 0.2)]
        1 * saleRepo.getByIdAndSaleMonthAndSaleYear(_ as Integer, _ as Integer, _ as Integer) >> Optional.of(new Sale(1, 250.00, 12, 2022))
        0 * _
    }

    void "should get employee with highest benefits"() {
        when:
        service.getHighestBenefitsEmployee(12, 2022)
        then:
        1 * employeeRepo.getEmployeesWithBenefits(_ as Integer, _ as Integer) >> [new Employee(1, "test", 1, 12, 2022)]
        1 * roleRepo.findAll() >> [new Role(1, "vendedor", 2000.00, 350.00, 0.2)]
        1 * saleRepo.getByIdAndSaleMonthAndSaleYear(_ as Integer, _ as Integer, _ as Integer) >> Optional.of(new Sale(1, 250.00, 12, 2022))
        0 * _
    }

    void "should get employee with highest sale"() {
        when:
        service.getHighestSaleEmployee(12, 2022)
        then:
        1 * saleRepo.getBySaleMonthAndSaleYear(_ as Integer, _ as Integer) >> [new Sale(1, 250.00, 12, 2022)]
        1 * employeeRepo.findById(_ as Integer) >> [new Employee(1, "test", 1, 12, 2022)]
        0 * _
    }

}
