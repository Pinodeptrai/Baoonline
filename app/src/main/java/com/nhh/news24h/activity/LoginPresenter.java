package com.nhh.news24h.activity;

public class LoginPresenter {

    LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String username, String password) {
        if (username.isEmpty()) {
            loginView.setErrorPassword();
            return;
        } else if (password.isEmpty()) {
            loginView.setErrorPassword();
            return;
        } else {
            if (username.equalsIgnoreCase("builong")
                    && password.
                    equalsIgnoreCase("doanxem")) {
                loginView.loginSuccessful();
                loginView.navigateHome();
            } else {
                loginView.loginFail();
            }
        }
    }
}
