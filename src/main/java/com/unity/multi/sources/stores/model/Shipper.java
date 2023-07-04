package com.unity.multi.sources.stores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shippers", schema = "sql_store")
public class Shipper {
    @Id
    @Column(name = "shipper_id", nullable = false)
    private Short id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "shipper")
    private Set<Order> orders = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipper shipper)) return false;

        if (!Objects.equals(id, shipper.id)) return false;
        if (!Objects.equals(name, shipper.name)) return false;
        return Objects.equals(orders, shipper.orders);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }
}