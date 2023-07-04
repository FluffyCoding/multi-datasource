package com.unity.multi.sources.invoicing.dtos;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.unity.multi.sources.invoicing.model.Payment}
 */
@Value
public class PaymentDto implements Serializable {
    Integer id;
    LocalDate date;
    BigDecimal amount;
}