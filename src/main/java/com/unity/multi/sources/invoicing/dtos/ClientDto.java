package com.unity.multi.sources.invoicing.dtos;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link com.unity.multi.sources.invoicing.model.Client}
 */
@Data
public class ClientDto implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String phone;
    private List<InvoiceDto> invoices = new ArrayList<>();
    /*private List<PaymentDto> payments = new ArrayList<>();*/
}