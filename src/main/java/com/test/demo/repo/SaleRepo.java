package com.test.demo.repo;

import com.test.demo.id.SaleId;
import com.test.demo.models.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepo extends CrudRepository<Sale, SaleId> {
    List<Sale> getBySaleMonthAndSaleYear(Integer saleMonth, Integer saleYear);

    Optional<Sale> getByIdAndSaleMonthAndSaleYear(Integer id, Integer saleMonth, Integer saleYear);
}
