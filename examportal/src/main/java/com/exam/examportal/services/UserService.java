package com.exam.examportal.services;

import com.exam.examportal.models.User;
import com.exam.examportal.models.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRole) throws Exception;
    public User getUserByUsername(String username)throws Exception;

    public String DeleteUserByUsername(Long userId)throws Exception;
}
