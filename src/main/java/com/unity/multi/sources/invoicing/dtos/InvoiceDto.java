package com.unity.multi.sources.invoicing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.unity.multi.sources.invoicing.model.Invoice}
 */
@Data
@RequiredArgsConstructor
public class InvoiceDto implements Serializable {
    private Integer id;
    private String number;
    private BigDecimal invoiceTotal;
    private BigDecimal paymentTotal;
}