package com.stock.pharmacy.Service;

import com.stock.pharmacy.Domain.StockDO;
import com.stock.pharmacy.Dto.StockDTO;

import java.util.List;

public interface StockService {
    StockDO createStock(StockDO stock);

    StockDTO updateStock(StockDO stock);
    void deleteStock(Long StokId);
    StockDO getStock(Long StokId);
    List<StockDO> getStockByNameAndSubstance(String name,int substance);
    List<StockDO> getAllStock();
}
