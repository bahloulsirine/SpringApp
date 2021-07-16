package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User getUserByEmail(String email);
    List<User>getUsersByFirstName(String firstname);
    List<User>getUsersByLastName(String lastname);
    List<User>getUsersByAddress(String address);
    User getUserById(Long id);

    List<User> getUsersByIdIn(List<Long> userIds);
    List<User>getUsersByTotalOrderIsGreaterThanEqual(int orderTotal);

@Modifying
    @Query(value = "UPDATE `user` SET `total_order` = 0 WHERE id IN (?1)", nativeQuery = true)
    void  setUsersTotalOrder(List<Long> userIds);
}
