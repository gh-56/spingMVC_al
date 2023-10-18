package com.erser.springmvc.repository;

import com.erser.springmvc.entity.PizzaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PizzaRepository extends CrudRepository<PizzaEntity, Long> {
    @Override
    ArrayList<PizzaEntity> findAll();
}
