package com.solvd.shop.threads;

import com.solvd.shop.singleton.LoginManager;

public class User1Thread extends Thread {
    @Override
    public void run() {
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.login("Maria");
        System.out.println(getName() + " sees user: " + loginManager.getCurrentUser());
        loginManager.logout();
    }
}
