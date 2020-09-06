package com.stockuserservice.Interface.External;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockuserservice.Interface.Model.StockUser;
import com.stockuserservice.Interface.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class DBManager {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    private String key = null;

    @PostConstruct
    private void info(){
        log.info("DBManager Interface Begins");
    }

    public User getUser(String userID){
        User user =null;
        final String uri = URI.getUser.replace("{id}",userID);
        try {
            user = objectMapper.readValue(restTemplate.getForObject(uri, String.class), User.class);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
        return user;
    }

    public void saveUser(User user){
        final String uri = URI.saveUser;
        try {
            restTemplate.put(uri,user);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        final String uri = URI.updateUser;
        try {
            restTemplate.put(uri,user);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
    }

    public void editUser(User user){
        final String uri = URI.editUser;
        try {
            restTemplate.put(uri,user);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
    }

    public StockUser getUserStock(String id){
        StockUser stockUser =null;
        final String uri = URI.getUserStock.replace("{id}",id);
        try {
            stockUser  = objectMapper.readValue(restTemplate.getForObject(uri, String.class), StockUser.class);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
        return stockUser;
    }

    public List<StockUser> getUserAllStock(String userid){
        List<StockUser> stockUser = null;
        final String uri = URI.getUserAllStock.replace("{userid}",userid);
        try {
            StockUser[] temp  = objectMapper.readValue(restTemplate.getForObject(uri, String.class), StockUser[].class);
            stockUser = Arrays.asList(temp);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
        return stockUser;
    }

    public void saveUserStock(StockUser stockUser){
        final String uri = URI.saveUserStock;
        try {
            restTemplate.put(uri,stockUser);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
    }

    public void tradeUserStock(String id, String volume){
        final String uri = URI.tradeUserStock.replace("{id}",id).replace("{volume}",volume);
        try {
            restTemplate.put(uri,null);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
    }
}
