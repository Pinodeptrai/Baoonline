package com.nhh.news24h.activity;

public interface LoginView {

    void loginFail();

    void loginSuccessful();

    void navigateHome();

    void setErrorUsername();
    void setErrorPassword();

}
