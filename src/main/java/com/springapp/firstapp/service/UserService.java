package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.UserSignupRequest;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserSignupRequest userRequest) {
        User user = new User(
                null, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getSex(),
                userRequest.getEmail(), "", userRequest.getCin(), passwordEncoder.encode(userRequest.getPassword()), "", userRequest.getDriverLicence(), userRequest.getPhoneNumber(), 0, true
                , true, true, true, "USER"
        );
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }


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
        return userRepo.getUsersByIdIn(userIds);
    }

    public List<User> getWonUsers(int orderTotal) {
        List<User> users = userRepo.getUsersByTotalOrderIsGreaterThanEqual(orderTotal);
        return users;
    }


    public void resetUsersOrder(List<Long> userIds) {
        userRepo.setUsersTotalOrder(userIds);
    }
}
