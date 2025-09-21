package com.solvd.shop.threads;

import com.solvd.shop.singleton.LoginManager;

public class User3Thread extends Thread {
    @Override
    public void run() {
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.login("Marisa");
        System.out.println(getName() + " sees user: " + loginManager.getCurrentUser());
        loginManager.logout();
    }
}