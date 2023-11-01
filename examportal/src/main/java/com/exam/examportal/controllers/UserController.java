package com.exam.examportal.controllers;

import com.exam.examportal.models.Role;
import com.exam.examportal.models.User;
import com.exam.examportal.models.UserRole;
import com.exam.examportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Creating user
    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody User user) throws Exception {
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
       role.setRoleId(45l);
        role.setRoleName("Normal");
       UserRole userRole = new UserRole();
       userRole.setUser(user);
       userRole.setRole(role);
       roles.add(userRole);

        return new ResponseEntity(this.userService.createUser(user,roles),HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity getUser(@PathVariable("username") String username) throws Exception {
        return new ResponseEntity(this.userService.getUserByUsername(username),HttpStatus.FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") Long userId) throws Exception {
        return  new ResponseEntity(this.userService.DeleteUserByUsername(userId), HttpStatus.OK);
    }
}
