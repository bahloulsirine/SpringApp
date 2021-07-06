package com.springapp.firstapp.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long code;
    private int stock;
    private int TVA;
    private String description;
    private String color;
    private int weight;
    private int price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private SubCategory subCategory;
}
