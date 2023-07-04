package com.unity.multi.sources.invoicing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity(name = "Client")
@Table(name = "clients", schema = "sql_invoicing")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "phone", length = 50)
    private String phone;

    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private Set<Invoice> invoices = new LinkedHashSet<>();




}