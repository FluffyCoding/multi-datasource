package com.unity.multi.sources.stores.repository;

import com.unity.multi.sources.stores.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}