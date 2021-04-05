package com.stock.pharmacy.Dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
@Data
public class StockDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private long id;
    private String type;//ilacın türü
    private String name;//ilacın ismi
    private String purposeOfUsage;//ilacın kullanım amacı
    private int amountOfSubstance;//ilacın dozu
    private int piece;//ilacın adeti
    private String company;//ilacın firması
}
