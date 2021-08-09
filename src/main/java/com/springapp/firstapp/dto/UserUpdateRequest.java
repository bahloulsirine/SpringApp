package com.springapp.firstapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springapp.firstapp.module.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private Long id;
    private  String firstName;
    private  String lastName;
    private  String address;
    private  String password;
    private  Long driverLicence;
    private  String phoneNumber;
    private  String sex;
    private  String email;
    private  Long cin;
    private Date birthday;
}
