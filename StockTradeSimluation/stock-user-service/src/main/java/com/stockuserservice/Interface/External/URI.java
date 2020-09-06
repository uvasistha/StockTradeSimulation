package com.stockuserservice.Interface.External;

public class URI {

    public  static String getUser = "http://localhost:8982/dataService/get/user/{id}";
    public  static String saveUser = "http://localhost:8982/dataService/save/user";
    public  static String updateUser = "http://localhost:8982/dataService/update/user";
    public  static String editUser = "http://localhost:8982/dataService/edit/user";

    public  static String getUserStock = "http://localhost:8982/dataService/portfolio/{id}";
    public  static String getUserAllStock = "http://localhost:8982/dataService/portfolio/all/{userid}";
    public  static String saveUserStock = "http://localhost:8982/dataService/portfolio/add/";
    public  static String tradeUserStock = "http://localhost:8982/dataService/portfolio/trade/{id}/{volume}";

}

