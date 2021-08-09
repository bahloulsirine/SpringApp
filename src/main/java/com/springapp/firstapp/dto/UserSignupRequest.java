package com.springapp.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignupRequest {
    private  String firstName;
    private  String lastName;
    private  String sex;
    private  String email;
    private  String password;
    private Long cin ;
    private Long driverLicence ;
    private String phoneNumber;
    private  String address;
    private Date birthday;
}
