package com.erser.springmvc.api;

import com.erser.springmvc.dto.PizzaDto;
import com.erser.springmvc.entity.PizzaEntity;
import com.erser.springmvc.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PizzaApiController {

    private final PizzaService pizzaService;
    @GetMapping("/api/pizza")
    public List<PizzaEntity> showList(){
        return pizzaService.showList();
    }
    @GetMapping("/api/pizza/{id}")
    public PizzaEntity showId(@PathVariable Long id){
        return pizzaService.showId(id);
    }
    @PostMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaEntity> create(@PathVariable Long id, @RequestBody PizzaDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
