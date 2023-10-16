package com.erser.springmvc.api;

import com.erser.springmvc.dto.CoffeeForm;
import com.erser.springmvc.entity.Coffee;
import com.erser.springmvc.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoffeeApiController {

    private final CoffeeRepository coffeeRepository;
    // GET
    @GetMapping("/api/coffee")
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }
    @GetMapping("/api/coffee/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }
    // POST
    @PostMapping("/api/coffee")
    public Coffee create(CoffeeForm dto){
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }
    // PATCH
    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, CoffeeForm dto){
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null || id != target.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // DELETE
    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffeeRepository.delete(coffee);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
