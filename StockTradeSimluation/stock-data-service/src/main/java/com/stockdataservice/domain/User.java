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
@Table(name ="Userinfo")
public class User {

    @Id
    @Column(name = "id")
    String id;
    @Column(name = "stock_list")
    String stock_list;
    @Column(name = "balance")
    String balance;
    @Column(name = "profit")
    String profit;
    @Column(name = "no_of_stock")
    String no_of_stock;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String email;
    @Column(name = "mobile")
    String mobile;
    @Column(name = "password")
    String password;
    @Column(name = "premium_customer")
    String premium_customer;
}
