package com.stock.pharmacy.Repository;

import com.stock.pharmacy.Domain.StockDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface StockRepository extends JpaRepository<StockDO, Long> {
    List<StockDO> findByNameAndAmountOfSubstance(String name,int substance);

}
