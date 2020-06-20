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
public class Stock {

        @JsonProperty("01. symbol")
        String symbol;
        @JsonProperty("02. open")
        String open;
        @JsonProperty("03. high")
        String high;
        @JsonProperty("04. low")
        String low;
        @JsonProperty("05. price")
        String price;
        @JsonProperty("06. volume")
        String volume;
        @JsonProperty("07. latest trading day")
        String latest_trading_day;
        @JsonProperty("08. previous close")
        String previous_close;
        @JsonProperty("09. change")
        String change;
        @JsonProperty("10. change percent")
        String change_percent;
}
