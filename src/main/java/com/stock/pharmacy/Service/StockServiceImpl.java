package com.stock.pharmacy.Service;
import com.stock.pharmacy.Domain.StockDO;
import com.stock.pharmacy.Dto.StockDTO;
import org.modelmapper.ModelMapper;
import com.stock.pharmacy.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository StockRepository;

//Stok oluşturan metot
    @Override
    public StockDO createStock(StockDO stock) {
        return StockRepository.save(stock);
    }

 // id numarasına göre güncelleyen metot
    @Override
    public StockDTO updateStock(StockDO stock) {
        long StockId = stock.getId();
        Optional<StockDO> currentStock = StockRepository.findById(StockId);
        if (currentStock.isPresent()) {
            currentStock.get().setType(stock.getType());
            currentStock.get().setName(stock.getName());
            currentStock.get().setPurposeOfUsage(stock.getPurposeOfUsage());
            currentStock.get().setAmountOfSubstance(stock.getAmountOfSubstance());
            currentStock.get().setPiece(stock.getPiece());
            currentStock.get().setCompany(stock.getCompany());



            StockRepository.save(currentStock.get());

            StockDTO stockDTO = new ModelMapper().map(currentStock.get(), StockDTO.class);


            return stockDTO;
        }
        return null;
    }
// id numarasına göre silen metot
    @Override
    public void deleteStock(Long StockId) {
        Optional<StockDO> currentStock = StockRepository.findById(StockId);
        if (currentStock.isPresent()) {
            StockRepository.deleteById(StockId);
        }
    }
    // id numarasına göre kayıt getiren metot
    @Override
    public StockDO getStock(Long StockId) {
        Optional<StockDO> currentStock = StockRepository.findById(StockId);
        if (currentStock.isPresent()) {
            return currentStock.get();
        }
        return null;
    }
    // İlacın adına ve dozuna göre kayıdı getiren metot
    @Override
    public List<StockDO> getStockByNameAndSubstance(String name, int substance) {
        List<StockDO> currentStock = StockRepository.findByNameAndAmountOfSubstance(name,substance);
        return currentStock;
    }
// Tüm kayıtları listeleyen metot
    @Override
    public List<StockDO> getAllStock() {
        return StockRepository.findAll();
    }
}
