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
@Table(name = "Colors", schema = "Animal_Shelter")
public class Color {
    @Id
    @Size(max = 10)
    @Column(name = "Color", nullable = false, length = 10)
    private String color;

    //TODO [JPA Buddy] generate columns from DB
}