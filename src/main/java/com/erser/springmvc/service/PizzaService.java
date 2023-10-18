package com.erser.springmvc.service;

import com.erser.springmvc.entity.PizzaEntity;
import com.erser.springmvc.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> showList(){
        return pizzaRepository.findAll();
    }

    public PizzaEntity showId(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }
}
