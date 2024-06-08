package com.users.usersMicroservices.service;

import com.users.usersMicroservices.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     User addUser(String email, String userName, String password);
     User removeUser(String userName,String password);
     User updateUserPassword(String userName,String email,String password);
     User getUser(String userName,String password);
}
