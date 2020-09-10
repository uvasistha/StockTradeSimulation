package com.stockdataservice.Interface.Rest;

import com.stockdataservice.Interface.External.model.StockExternal;
import com.stockdataservice.Interface.Rest.Model.User;
import com.stockdataservice.domain.StockUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/dataService")
public class Controller {

    @Autowired
    Handler handler;

    @Autowired
    ObjectToJson objectToJson;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/get/stock/{id}")
    public HttpEntity<String> getStock(@PathVariable String id){
        return new HttpEntity<String>(objectToJson.convert(handler.getStock(id)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/get/user/{id}")
    public HttpEntity<String> getUser(@PathVariable String id){
        return new HttpEntity<String>(objectToJson.convert(handler.getUser(id)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/save/stock")
    public HttpEntity<String> saveStock(@RequestBody StockExternal stock){
        return new HttpEntity<String>(objectToJson.convert(handler.saveStock(stock)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/save/user")
    public HttpEntity<String> saveUser(@RequestBody User user){
        return new HttpEntity<String>(objectToJson.convert(handler.saveUser(user)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/update/stock")
    public HttpEntity<String> updateStock(@RequestBody StockExternal stock){
        return new HttpEntity<String>(objectToJson.convert(handler.updateStock(stock)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/update/user")
    public HttpEntity<String> updateUser(@RequestBody User user){
        return new HttpEntity<String>(objectToJson.convert(handler.updateUser(user)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/edit/user")
    public HttpEntity<String> editUser(@RequestBody User user){
        return new HttpEntity<String>(objectToJson.convert(handler.editUser(user)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/portfolio/{id}")
    public HttpEntity<String> getUserStock(@PathVariable String id){
        return new HttpEntity<String>(objectToJson.convert(handler.getUserStock(id)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/portfolio/all/{userid}")
    public HttpEntity<String> getUserAllStock(@PathVariable String userid){
        return new HttpEntity<String>(objectToJson.convert(handler.getUserAllStock(userid)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/portfolio/add/")
    public HttpEntity<String> saveUserStock(@RequestBody StockUser stockUser){
        return new HttpEntity<String>(objectToJson.convert(handler.saveUserStock(stockUser)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/portfolio/trade/{id}/{volume}")
    public HttpEntity<String> tradeUserStock(@PathVariable String id,@PathVariable String volume){
        return new HttpEntity<String>(objectToJson.convert(handler.tradeUserStock(id,volume)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/explore")
    public HttpEntity<String> exploreStock(){
        return new HttpEntity<String>(objectToJson.convert(handler.exploreStock()));
    }
}