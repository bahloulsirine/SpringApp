package com.springapp.firstapp.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private String modeOfPayment;
    private int amount;
    private String state;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;
}
