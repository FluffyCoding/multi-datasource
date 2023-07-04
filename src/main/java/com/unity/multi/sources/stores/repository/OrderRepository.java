package com.unity.multi.sources.stores.repository;

import com.unity.multi.sources.stores.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}