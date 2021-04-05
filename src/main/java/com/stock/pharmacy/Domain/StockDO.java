package com.stock.pharmacy.Domain;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medicine_stock")
public class StockDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String type;//ilacın türü
    private String name;//ilacın adı
    private String purposeOfUsage;//kullanım amacı
    private int amountOfSubstance;//ilacın dozu
    private int piece;//adet
    private String company;//şirket
}
