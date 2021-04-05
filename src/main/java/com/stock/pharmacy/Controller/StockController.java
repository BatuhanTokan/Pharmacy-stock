package com.stock.pharmacy.Controller;
import com.stock.pharmacy.Domain.StockDO;
import com.stock.pharmacy.Dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.stock.pharmacy.Service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pharmacy/stock")
public class StockController {
    @Autowired
    private StockService stockservice;
    @ApiOperation(value = "Create Stock")
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StockDO> createStock(@RequestBody StockDO stockDO) {
        StockDO createdStock = stockservice.createStock(stockDO);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Updates an existing Stock")
    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StockDTO> updateStock(@RequestBody StockDO stockDO) {
        StockDTO updatedStock = stockservice.updateStock(stockDO);
        return new ResponseEntity<>(updatedStock, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Gets Stock by id")
    @GetMapping(path = "/get/{StockId}")
    public ResponseEntity<StockDO> getStock(@PathVariable Long StockId) {
        StockDO stock = stockservice.getStock(StockId);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
    @ApiOperation(value = "Updates Stock By Name And Substance ")
    @PutMapping(path = "/Update/{name}/{amountOfsubstance}/{piece}")
    public ResponseEntity<List<StockDO>> updateStokcByNameAndSubstance(@PathVariable String name, @PathVariable int amountOfsubstance,@PathVariable int piece) {
        List<StockDO> updateStokByNameAndSubstance=stockservice.getStockByNameAndSubstance(name,amountOfsubstance);
        for(StockDO update: updateStokByNameAndSubstance )
        {
            update.setPiece(piece);
            stockservice.updateStock(update);
        }
        return new ResponseEntity <>(updateStokByNameAndSubstance, HttpStatus.OK);
    }
    @ApiOperation(value = "Deletes Stock By Name And Substance ")
    @DeleteMapping(path = "/Delete/{name}/{amountOfsubstance}")
    public ResponseEntity<String> deleteStokcByNameAndSubstance(@PathVariable String name, @PathVariable int amountOfsubstance) {
        List<StockDO> deleteStokByNameAndSubstance =stockservice.getStockByNameAndSubstance(name,amountOfsubstance);
        for(StockDO delete: deleteStokByNameAndSubstance )
        {
            stockservice.deleteStock(delete.getId());
        }
        return new ResponseEntity <>("Stock with Name: " + name +"And AmountOfSubstance: "+ amountOfsubstance + " is deleted.", HttpStatus.OK);
    }
    @ApiOperation(value = "Gets Stock By Name And Substance ")
    @GetMapping(path = "/Get/{name}/{amountOfsubstance}")
    public ResponseEntity<List<StockDO>> getStokcByNameAndSubstance(@PathVariable String name, @PathVariable int amountOfsubstance) {
        List<StockDO> getStockByAdAndDoz =stockservice.getStockByNameAndSubstance(name,amountOfsubstance);

        return new ResponseEntity <>(getStockByAdAndDoz, HttpStatus.OK);
    }
    @ApiOperation(value = "Gets all stock")
    @GetMapping(path = "/gets-all")
    public ResponseEntity<List<StockDO>> getAllStock() {
        List<StockDO> allStock = stockservice.getAllStock();
        return new ResponseEntity<>(allStock, HttpStatus.OK);
    }
    @ApiOperation(value = "Deletes Stock by id")
    @DeleteMapping(path = "/delete/{stockId}")
    public ResponseEntity<String> deleteStock(@PathVariable(value = "stockId") Long stockId) {
        stockservice.deleteStock(stockId);
        return new ResponseEntity<>("Stock with id: " + stockId + " is deleted.", HttpStatus.OK);
    }

}
