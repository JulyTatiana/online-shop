package com.solvd.shop.model;

public class Admin extends User {
    private String username;

    public Admin(String username, int priority){
        super(priority);
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    @Override
    public int getPriority(){
        return 5;
    }
}
