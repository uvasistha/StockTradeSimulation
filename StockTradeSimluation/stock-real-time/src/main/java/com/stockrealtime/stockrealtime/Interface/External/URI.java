package com.stockrealtime.stockrealtime.Interface.External;

import lombok.Data;

@Data
public class URI {
    public static String quote ="https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=StockName&apikey=StockKey";
    public static String search ="https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=StockName&apikey=StockKey";
}
