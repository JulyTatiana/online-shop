package com.shop.model;

public class StandardUser extends User {
    private String username;

    public StandardUser(String username, int priority){
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
        return 3;
    }
}
