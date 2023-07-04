package com.unity.multi.sources.invoicing.repository;

import com.unity.multi.sources.invoicing.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}