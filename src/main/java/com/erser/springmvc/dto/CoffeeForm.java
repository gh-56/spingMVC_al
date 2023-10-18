package com.erser.springmvc.dto;

import com.erser.springmvc.entity.Coffee;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CoffeeForm {
    private String name;
    private String price;

    public Coffee toEntity(){
        return new Coffee(null, name, price);
    }
}
