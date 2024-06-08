package com.users.usersMicroservices.dao;

import lombok.Data;

@Data
public class PasswordReset {
    private String email;
    private  String password;
}
