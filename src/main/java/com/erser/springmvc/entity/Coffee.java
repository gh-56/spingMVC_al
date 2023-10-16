package com.erser.springmvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Coffee {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String price;

    public void patch(Coffee coffee){
        if(coffee.name != null){
            this.name = coffee.name;
        }
        if(coffee.price != null){
            this.price = coffee.price;
        }
    }
}
