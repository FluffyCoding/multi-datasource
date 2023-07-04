package com.unity.multi.sources.shelter.repository;

import com.unity.multi.sources.shelter.entity.Animal;
import com.unity.multi.sources.shelter.entity.AnimalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, AnimalId> {
}