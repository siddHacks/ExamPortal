package com.exam.examportal.services.impl;

import com.exam.examportal.models.User;
import com.exam.examportal.models.UserRole;
import com.exam.examportal.repositories.RoleRepository;
import com.exam.examportal.repositories.UserRepository;
import com.exam.examportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    //user creation
    @Override
    public User createUser(User user, Set<UserRole> userRole) throws Exception {
        User newuser  = this.userRepository.findByUsername(user.getUsername());
        if(newuser  != null){
            throw new Exception("User already exists");
        }else{
            //user creation
            for(UserRole ur : userRole){
                roleRepository.save(ur.getRole());
            }
            user.getUserRole().addAll(userRole);
            newuser = this.userRepository.save(user);

        }
        return newuser;
    }

    //getting user by username
    @Override
    public User getUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new Exception("Username Invalid!!");
        }else{
            return user;
        }
    }

    @Override
    public String DeleteUserByUsername(Long userId) throws Exception {
       User user = userRepository.findById(userId).get();
       if(user == null){
           throw new Exception("User not Found!");
       }else{
           userRepository.deleteById(userId);
           return "User deleted Successfully!";
       }
    }
}
