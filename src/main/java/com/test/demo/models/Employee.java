package com.test.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUNCIONARIO")
public class Employee {

    public Employee() {}

    public Employee(Integer id, String name, Integer roleId, Integer hireMonth, Integer hireYear) {
        this.id = id;
        this.name = name;
        this.roleId = roleId;
        this.hireMonth = hireMonth;
        this.hireYear = hireYear;
    }

    @Id
    @Column(name = "ID")
    public Integer id;

    @Column(name = "NOME")
    public String name;

    @Column(name = "ID_CARGO")
    public Integer roleId;

    @Column(name = "MES_CONTRATACAO")
    public Integer hireMonth;

    @Column(name = "ANO_CONTRATACAO")
    public Integer hireYear;
}
