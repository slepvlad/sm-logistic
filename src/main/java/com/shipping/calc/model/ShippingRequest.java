package com.shipping.calc.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShippingRequest {

    @NotNull(message = "Quantity in square meters is required")
    @DecimalMin(value = "0.01", message = "Quantity must be greater than 0")
    private Double quantitySquareMeters;

    @NotNull(message = "Currency cross rate is required")
    @DecimalMin(value = "0.0001", message = "Currency rate must be greater than 0")
    private Double currencyCrossRate;

    @NotNull(message = "Price per square meter is required")
    @DecimalMin(value = "0.01", message = "Price per sqm must be greater than 0")
    private Double pricePerSquareMeter;
    
}
