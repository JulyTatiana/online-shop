package com.solvd.shop.threads;

import com.solvd.shop.singleton.LoginManager;

public class User2Thread extends Thread {
    @Override
    public void run() {
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.login("Henry");
        System.out.println(getName() + " sees user: " + loginManager.getCurrentUser());
        loginManager.logout();
    }
}