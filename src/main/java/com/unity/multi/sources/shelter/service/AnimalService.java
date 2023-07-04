package com.unity.multi.sources.shelter.service;

import com.unity.multi.sources.shelter.entity.Animal;
import com.unity.multi.sources.shelter.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;


    @Transactional(readOnly = true)
    public Page<Animal> getAnimalByPage(int page, int size) {
        return animalRepository.findAll(PageRequest.of(page, size));
    }


}
