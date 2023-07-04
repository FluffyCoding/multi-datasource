package com.unity.multi.sources.shelter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AnimalId implements Serializable {
    private static final long serialVersionUID = -8591069883158359849L;
    @Size(max = 20)
    @NotNull
    @Column(name = "Name", nullable = false, length = 20)
    private String name;

    @Size(max = 10)
    @NotNull
    @Column(name = "Species", nullable = false, length = 10)
    private String species;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimalId entity = (AnimalId) o;
        return Objects.equals(this.species, entity.species) &&
                Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, name);
    }

}