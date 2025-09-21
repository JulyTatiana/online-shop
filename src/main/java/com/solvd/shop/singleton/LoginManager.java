package com.solvd.shop.singleton;

public class LoginManager {

    private static volatile LoginManager instance;

    // ThreadLocal ensures each thread has its own copy of currentUser
    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

    private LoginManager() {
        System.out.println("LoginManager instance created.");
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    // Each thread sets its own user
    public void login(String username) {
        currentUser.set(username);
        System.out.println(Thread.currentThread().getName() +
                " logged in as: " + username);
    }

    // Each thread logs out its own user
    public void logout() {
        System.out.println(Thread.currentThread().getName() +
                " logged out: " + currentUser.get());
        currentUser.remove(); // cleanup
    }

    // Each thread can see its own user
    public String getCurrentUser() {
        return currentUser.get();
    }
}
