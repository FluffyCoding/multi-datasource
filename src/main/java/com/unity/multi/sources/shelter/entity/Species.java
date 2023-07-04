package com.unity.multi.sources.shelter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Species", schema = "Animal_Shelter")
public class Species {
    @Id
    @Size(max = 10)
    @Column(name = "Species", nullable = false, length = 10)
    private String species;

    //TODO [JPA Buddy] generate columns from DB
}