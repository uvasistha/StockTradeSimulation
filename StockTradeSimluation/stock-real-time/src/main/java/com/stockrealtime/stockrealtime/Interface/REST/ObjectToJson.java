package com.stockrealtime.stockrealtime.Interface.REST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class ObjectToJson {

    @Autowired
    ObjectMapper objectMapper;

    public String convert(Object o){
        String json = null;
        try {
            ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  json;
    }
}