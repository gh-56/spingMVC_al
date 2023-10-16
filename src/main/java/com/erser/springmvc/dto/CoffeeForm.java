package com.erser.springmvc.dto;

import com.erser.springmvc.entity.Coffee;

public class CoffeeForm {
    private String name;
    private String price;

    public Coffee toEntity(){
        return new Coffee(null, name, price);
    }
}
