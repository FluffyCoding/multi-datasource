package com.unity.multi.sources.invoicing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Invoice")
@Table(name = "invoices", schema = "sql_invoicing")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false, length = 50)
    private String number;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "invoice_total", nullable = false, precision = 9, scale = 2)
    private BigDecimal invoiceTotal;

    @Column(name = "payment_total", nullable = false, precision = 9, scale = 2)
    private BigDecimal paymentTotal;

    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    /*@OneToMany(mappedBy = "invoice")
    private Set<Payment> payments = new LinkedHashSet<>();*/


}