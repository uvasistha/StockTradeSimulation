package com.stockdataservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonStockSymbol {
    @JsonProperty("keygen")
    List<Key> keygen;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Key{
        @JsonProperty("key")
        String key;
    }
}
