package com.test.demo.controller;

import com.test.demo.models.Employee;
import com.test.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Inject
    EmployeeService service;

    @GetMapping("/salaryAndBenefits")
    Double getSalaryAndBenefitsPaid(@RequestParam Integer month, @RequestParam Integer year) {
        return service.getSalaryAndBenefitsPaid(month, year);
    }

    @GetMapping("/salary")
    Double getSalaryPaid(@RequestParam Integer month, @RequestParam Integer year) {
        return service.getSalaryPaid(month, year);
    }

    @GetMapping("/benefits")
    Double getBenefitsPaid(@RequestParam Integer month, @RequestParam Integer year) {
        return service.getBenefitsPaid(month, year);
    }

    @GetMapping("/highestPaid")
    Employee getHighestPaidEmployee(@RequestParam Integer month, @RequestParam Integer year) {
        return service.getHighestPaidEmployee(month, year);
    }

    @GetMapping("/highestBenefits")
    Employee getHighestBenefitsEmployee(@RequestParam Integer month, @RequestParam Integer year) {
        return service.getHighestBenefitsEmployee(month, year);
    }

    @GetMapping("/highestSale")
    Employee getHighestSaleEmployee(@RequestParam Integer month, @RequestParam Integer year) {
        return service.getHighestSaleEmployee(month, year);
    }

}
