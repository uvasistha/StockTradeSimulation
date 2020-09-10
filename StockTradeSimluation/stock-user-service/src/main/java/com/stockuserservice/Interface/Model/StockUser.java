package com.stockuserservice.Interface.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockUser {

    @JsonProperty("id")
    String id;

    @JsonProperty("stock_name")
    String stock_name;

    @JsonProperty("stock_symbol")
    String stock_symbol;

    @JsonProperty("stock_volume")
    String stock_volume;

    @JsonProperty("user_id")
    String user_id;

    @JsonProperty("current_value")
    String current_value;

    @JsonProperty("price_of_stock")
    String price_of_stock;

    @JsonProperty("change_percent")
    String change_percent;
}

