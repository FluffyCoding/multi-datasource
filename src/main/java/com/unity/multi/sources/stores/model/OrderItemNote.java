package com.unity.multi.sources.stores.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_item_notes", schema = "sql_store")
public class OrderItemNote {
    @Id
    @Column(name = "note_id", nullable = false)
    private Integer id;

    @Column(name = "order_Id", nullable = false)
    private Integer orderId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "note", nullable = false)
    private String note;

}