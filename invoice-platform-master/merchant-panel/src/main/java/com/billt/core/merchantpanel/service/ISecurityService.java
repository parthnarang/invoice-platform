package com.billt.core.merchantpanel.service;

public interface ISecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
