package com.stockdataservice.Interface.Rest.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @JsonProperty("id")
    String id;
    @JsonProperty("stock_list")
    String stock_list;
    @JsonProperty( "balance")
    String balance;
    @JsonProperty("profit")
    String profit;
    @JsonProperty("no_of_stock")
    String no_of_stock;
    @JsonProperty("name")
    String name;
    @JsonProperty("email")
    String email;
    @JsonProperty("mobile")
    String mobile;
    @JsonProperty("password")
    String password;
    @JsonProperty("premium_customer")
    String premium_customer;
}