package com.springapp.firstapp.service;

import com.springapp.firstapp.dto.UserSignupRequest;
import com.springapp.firstapp.dto.UserUpdateRequest;
import com.springapp.firstapp.module.User;
import com.springapp.firstapp.repo.RoleRepo;
import com.springapp.firstapp.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    public User  createUser(UserSignupRequest userRequest) {
        User user = new User(
                null, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getSex(),
                userRequest.getEmail(), userRequest.getAddress(), userRequest.getCin(), passwordEncoder.encode(userRequest.getPassword()), userRequest.getBirthday(), userRequest.getDriverLicence(), userRequest.getPhoneNumber(), 0, true
                , true, true, true,roleRepo.getRoleById(2L)

        );
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public User updateUser(UserUpdateRequest updateRequest) {
        User user = userRepo.getUserById(updateRequest.getId());
        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());
        user.setSex(updateRequest.getSex());
        user.setEmail(updateRequest.getEmail());
        user.setBirthday(updateRequest.getBirthday());
        user.setPhoneNumber(updateRequest.getPhoneNumber());
        user.setAddress(updateRequest.getAddress());
        user.setDriverLicence(updateRequest.getDriverLicence());
        user.setCin(updateRequest.getCin());
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

    public void disableUserAccount(Long id) {
        User user=getUserById(id).get();
        user.setIsEnabled(false);
        userRepo.save(user);
    }

    public User makeDelivery(User user) {
        user.setRole(roleRepo.getRoleById(4L));
        userRepo.save(user);
        return user;
    }

    public User makeProvider(User user) {
        user.setRole(roleRepo.getRoleById(3L));
        userRepo.save(user);
        return user;
    }

    public User getUserByCin(Long cin) {
        return userRepo.getUserByCin(cin);
    }
    public List<User>getProviders(){
        List<User> users=getAllUsers();
        List<User> providers=new ArrayList<>();
        for (User user:users){
            if (user.hasRole("PROVIDER")){
                providers.add(user);
            }
        }
        return(providers);
    }

    public Boolean comparePassword(String password) {
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentPassword=user.getPassword();
        return passwordEncoder.matches(password,currentPassword);
    }

    public User updatePassword(String password) {
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPassword(passwordEncoder.encode(password));
        return(userRepo.save(user));
    }
}
