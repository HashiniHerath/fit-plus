package com.pafassigment.fitplus.service;

import com.pafassigment.fitplus.entity.User;
import com.pafassigment.fitplus.dao.UserDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByUserId(String id) {
        return userDao.findById(id).orElseGet(User::new);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public Map<String, String> deleteUserById(String id) {
        Optional<User> user = userDao.findById(id);
        Map<String, String> response = new HashMap<>();
        if (user.isPresent()) {
            userDao.deleteById(id);
            response.put("response", "User deleted successfully");
        } else {
            response.put("response", "User not found");
        }
        return response;
    }
    @Override
    public User editUserById(String id, User user) {
        Optional<User> oldUser = userDao.findById(id);
        if (oldUser.isPresent()) {
            userDao.save(user);
        } else {
            return new User();
        }
        return user;
    }
}
