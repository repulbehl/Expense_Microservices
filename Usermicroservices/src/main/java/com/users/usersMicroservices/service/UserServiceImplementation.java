package com.users.usersMicroservices.service;


import com.users.usersMicroservices.entity.User;
import com.users.usersMicroservices.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImplementation implements  UserService{

    @Autowired
    UserRepo userRepo ;

    @Override
    public User addUser(String email, String userName, String password) {
        if(!email.isEmpty() && !userName.isEmpty() && !password.isEmpty()){
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setUsername(userName);
            userRepo.save(user);
            return  user;
        }

        return null;
    }

    private  User getUserId(String userName){
        if (userName.isEmpty())
        {
            return null;
        }
        List<User> users = userRepo.findAll();
        for(User user : users ){
            if(user.getUsername().trim().equals(userName.trim())){
                return user;
            }
        }
        return null;
    }
    @Override
    public User removeUser(String userName, String password) {
        User user = getUserId(userName);
        if(!password.isEmpty() && password.trim().equals(user.getPassword().trim())){
            userRepo.delete(user);
            return user;
        }
        return null;
    }

    @Override
    public User updateUserPassword(String userName,String email, String passowrd) {
        User user = getUserId(userName);
        if(!email.isEmpty() && user.getEmail().trim().equals(email)){
            user.setPassword(passowrd);
            userRepo.save(user);
            return  user;
        }
        return null;
    }

    @Override
    public User getUser(String userName, String password) {
        User user = getUserId(userName);
        if (user != null &&  user.getPassword().trim().equals(password)){
            return  user;
        }
        return null;
    }
}
