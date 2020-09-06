package com.stockuserservice.Interface.Model;

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
public class Trade {

    @JsonProperty("id")
    String stockSymbol;

    @JsonProperty("stockName")
    String stockName;

    @JsonProperty("stockVolume")
    String stockVolume;

    @JsonProperty("amount")
    String amount;
}
