package com.stockrealtime.stockrealtime.Interface.External;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiKey {
    @JsonProperty("key")
    String key;
}
