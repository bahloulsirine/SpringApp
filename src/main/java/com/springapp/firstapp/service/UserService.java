package com.springapp.firstapp.service;

import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User createUser(User user) {

        user.setTotalOrder(0);
        return userRepo.save(user);
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);}


    public List<User> getUsersByLastname(String lastname) {
        return userRepo.getUsersByLastName(lastname);
    }

    public List<User> getUsersByFirstname(String firstname) {
        return userRepo.getUsersByFirstName(firstname);
    }

    public List<User> getUsersByAddress(String address) {
        return userRepo.getUsersByAddress(address);
    }

    public List<User> getUsersByIdIn(List<Long> userIds) {
        return  userRepo.getUsersByIdIn(userIds);
    }
    public List<User> getWonUsers(int orderTotal){
        List<User> users=userRepo.getUsersByTotalOrderIsGreaterThanEqual(orderTotal);
        return users;
    }
}
