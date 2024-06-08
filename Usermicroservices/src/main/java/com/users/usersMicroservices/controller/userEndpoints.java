package com.users.usersMicroservices.controller;


import com.users.usersMicroservices.dao.LogInDao;
import com.users.usersMicroservices.dao.PasswordReset;
import com.users.usersMicroservices.dao.UserSaveDao;
import com.users.usersMicroservices.dao.UserRemoveDao;
import com.users.usersMicroservices.entity.User;
import com.users.usersMicroservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
public class userEndpoints {
    @Autowired
    UserService userService;

    @GetMapping("/logIn")
    public ResponseEntity<User> getUser(@RequestBody LogInDao logInDao) {
        User user = userService.getUser(logInDao.getUsername() ,logInDao.getPassword());
        if(user!=null){
        return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> addUser(@RequestBody UserSaveDao userSaveDao) {
        User  user = userService.addUser(userSaveDao.email,userSaveDao.username,userSaveDao.password);
        if(user != null){
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> removeUser(@RequestBody  UserRemoveDao userRemoveDao) {
        User user =  userService.removeUser(userRemoveDao.getUsername(), userRemoveDao.getPassword());
        if(user != null){
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{userName}")
    public ResponseEntity<User> updateUserPassword(@PathVariable String userName ,@RequestBody PasswordReset passwordReset) {
        User user =  userService.updateUserPassword(userName,passwordReset.getEmail(),passwordReset.getPassword());
        if(user != null){
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
