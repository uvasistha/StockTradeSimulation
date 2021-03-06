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
@Table(name ="Stock")
public class Stock {

    @Id
    @Column(name = "id")
    String symbol;
    @Column(name = "open_val")
    String open_val;
    @Column(name = "high")
    String high;
    @Column(name = "low")
    String low;
    @Column(name = "price")
    String price;
    @Column(name = "volume")
    String volume;
    @Column(name = "latest_trading_day")
    String latest_trading_day;
    @Column(name = "previous_close")
    String previous_close;
    @Column(name = "change")
    String change;
    @Column(name = "change_percent")
    String change_percent;
}
