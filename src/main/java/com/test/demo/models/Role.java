package com.test.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARGO")
public class Role {

    public Role() {}

    public Role(Integer id, String role, Double salary, Double salaryModifier, Double benefits) {
        this.id = id;
        this.role = role;
        this.salary = salary;
        this.salaryModifier = salaryModifier;
        this.benefits = benefits;
    }

    @Id
    @Column(name = "ID")
    public Integer id;

    @Column(name = "CARGO")
    public String role;

    @Column(name = "SALARIO")
    public Double salary;

    @Column(name = "MODIFICADOR_SALARIO")
    public Double salaryModifier;

    @Column(name = "BENEFICIO")
    public Double benefits;
}
