package com.test.demo.models;

import com.test.demo.id.SaleId;

import javax.persistence.*;

@Entity
@Table(name = "VENDA")
@IdClass(SaleId.class)
public class Sale {

    public Sale() {}

    public Sale(Integer id, Double sale, Integer saleMonth, Integer saleYear) {
        this.id = id;
        this.sale = sale;
        this.saleMonth = saleMonth;
        this.saleYear = saleYear;
    }

    @Id
    @Column(name = "ID_FUNCIONARIO")
    public Integer id;

    @Column(name = "VALOR_VENDA")
    public Double sale;

    @Id
    @Column(name = "MES_VENDA")
    public Integer saleMonth;

    @Id
    @Column(name = "ANO_VENDA")
    public Integer saleYear;
}
