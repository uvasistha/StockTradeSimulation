package com.stockuserservice.Interface.REST;

import com.stockuserservice.Interface.Model.Trade;
import com.stockuserservice.Interface.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "/*")
@RequestMapping("/userService")
public class Controller {

    @Autowired
    Handler handler;

    @Autowired
    ObjectToJson objectToJson;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/validate/")
    public HttpEntity<String> getStock(@RequestBody User user){
        return new HttpEntity<String>(objectToJson.convert(handler.validateUser(user)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/get/user/{id}")
    public HttpEntity<String> getUser(@PathVariable String id){
        return new HttpEntity<String>(objectToJson.convert(handler.getUser(id)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/save/user/")
    public HttpEntity<String> saveUser(@RequestBody User user){
        handler.saveUser(user);
        return new HttpEntity<String>("OK");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/edit/user")
    public HttpEntity<String> editUser(@RequestBody User user){
        handler.editUser(user);
        return new HttpEntity<String>("OK");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/update/user/{id}/{purchase}")
    public HttpEntity<String> updateUser(@PathVariable String id, @PathVariable Boolean purchase,@RequestBody Trade trade){
        return new HttpEntity<String>(objectToJson.convert( handler.updateUser(id,purchase,trade)));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/portfolio/{userid}")
    public HttpEntity<String> getUserPortfolio(@PathVariable String userid){
        return new HttpEntity<String>(objectToJson.convert(handler.getUserPortfolioUser(userid)));
    }
}