package com.solvd.shop.model;

public abstract class User{
    private final String recommendation = "Please do NOT share your account info";
    private int priority;

    protected String name;

    public User(int priority) {
        super();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
