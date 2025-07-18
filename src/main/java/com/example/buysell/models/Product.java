package com.example.buysell.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description", columnDefinition = "text")
    private String description;
    @Column(name="price")
    private Integer price;
    @Column(name="city")
    private String city;


    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
    private User user;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated=LocalDateTime.now();
    }



}
