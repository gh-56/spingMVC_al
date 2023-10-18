package com.erser.springmvc.service;

import com.erser.springmvc.dto.CoffeeForm;
import com.erser.springmvc.entity.Coffee;
import com.erser.springmvc.repository.CoffeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CoffeeService {
    private CoffeeRepository coffeeRepository;
    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id){
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto){
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }
    public Coffee update(Long id, CoffeeForm dto){
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null || id != target.getId()){
            return null;
        }
        target.patch(coffee);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null){
            return null;
        }
        coffeeRepository.delete(coffee);
        return coffee;
    }

    @Transactional
    public List<Coffee> createCoffees(List<CoffeeForm> dtos){
        List<Coffee> coffeeList = dtos.stream().map(dto -> dto.toEntity()).toList();
        coffeeList.forEach(coffeeRepository::save);
        return coffeeList;
    }
}
