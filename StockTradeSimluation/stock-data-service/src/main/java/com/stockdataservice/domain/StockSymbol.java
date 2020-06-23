package com.stockdataservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="StockSymbol")
public class StockSymbol {

    @Id
    @Column(name = "id")
    String id;
    @Column(name = "name")
    String name;
}
