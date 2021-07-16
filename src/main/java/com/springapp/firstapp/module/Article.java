package com.springapp.firstapp.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private SubCategory subCategory;

    @ManyToMany
    @JsonIgnore
    private List<Article> articles;

    @ManyToOne
    private User user;
}
