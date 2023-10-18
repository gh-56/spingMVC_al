package com.erser.springmvc.api;

import com.erser.springmvc.dto.ArticleForm;
import com.erser.springmvc.dto.CoffeeForm;
import com.erser.springmvc.entity.Article;
import com.erser.springmvc.entity.Coffee;
import com.erser.springmvc.repository.CoffeeRepository;
import com.erser.springmvc.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoffeeApiController {

    private final CoffeeService coffeeService;
    // GET
    @GetMapping("/api/coffee")
    public List<Coffee> index(){
        return coffeeService.index();
    }
    @GetMapping("/api/coffee/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeService.show(id);
    }
    // POST
    @PostMapping("/api/coffee")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto){
        return coffeeService.create(dto) != null ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    // PATCH
    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto){
        return coffeeService.update(id, dto) != null ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    // DELETE
    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        return coffeeService.delete(id) != null ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/api/coffee/transaction-test")
    public ResponseEntity<List<Coffee>> createCoffees(List<CoffeeForm> dtos){
        List<Coffee> coffees = coffeeService.createCoffees(dtos);
        return coffees != null ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
