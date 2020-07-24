package com.stockuserservice.Interface.External;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockuserservice.Interface.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

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
}
