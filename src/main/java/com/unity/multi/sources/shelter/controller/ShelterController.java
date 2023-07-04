package com.unity.multi.sources.shelter.controller;

import com.unity.multi.sources.shelter.entity.Animal;
import com.unity.multi.sources.shelter.service.AnimalService;
import com.unity.multi.sources.stores.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/animals")
public class ShelterController {

    private final AnimalService animalService;

    @GetMapping(path = "/all")
    public Page<Animal> getClintesByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return animalService.getAnimalByPage(page, size);
    }


}
