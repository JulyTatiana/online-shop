package com.shop.model;

public abstract class Satisfaction <T> {
    public T comments;

    public Satisfaction(T comments){
        this.comments=comments;
    }

    public T getComments(){
        return comments;
    }

    public void setComments(T comments) {
        this.comments = comments;
    }
}
