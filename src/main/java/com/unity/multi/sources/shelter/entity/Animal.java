package com.unity.multi.sources.shelter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Animals", schema = "Animal_Shelter")
public class Animal {
    @EmbeddedId
    private AnimalId id;

    @MapsId("species")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Species")
    private Species species;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Primary_Color")
    private Color primaryColor;

    @Size(max = 36)
    @NotNull
    @Column(name = "Implant_Chip_ID", nullable = false, length = 36)
    private String implantChipId;

    @Size(max = 50)
    @Column(name = "Breed", length = 50)
    private String breed;

    @Size(max = 1)
    @NotNull
    @Column(name = "Gender", nullable = false, length = 1)
    private String gender;

    @NotNull
    @Column(name = "Birth_Date", nullable = false)
    private LocalDate birthDate;

    @Size(max = 20)
    @NotNull
    @Column(name = "Pattern", nullable = false, length = 20)
    private String pattern;

    @NotNull
    @Column(name = "Admission_Date", nullable = false)
    private LocalDate admissionDate;

}