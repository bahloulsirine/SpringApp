package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    List<User>getUsersByFirstName(String firstname);
    List<User>getUsersByLastName(String lastname);
    List<User>getUsersByAddress(String address);
    User getUserById(Long id);

}
