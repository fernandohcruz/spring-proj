package com.fernandoh.dto;

import java.math.BigDecimal;

public record ClientDTO(String firstName, String lastName, String document, BigDecimal balance) {
}
