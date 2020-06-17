package com.stockrealtime.stockrealtime.Interface.REST;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "/*")
@RequestMapping("/realTime")
public class Controller {

    @Autowired
    Handler handler;
    @Autowired
    ObjectToJson objectToJson;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/get/{id}")
    public HttpEntity<String> getExamplePath(@PathVariable String id){
        return new HttpEntity(objectToJson.convert(handler.getStockInfo(id)));
    }

}
