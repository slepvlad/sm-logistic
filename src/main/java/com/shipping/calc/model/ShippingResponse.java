package com.shipping.calc.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShippingResponse {
    
    private double quantitySquareMeters;
    private double pricePerSqmInSourceCurrency;
    private double fobSumInSourceCurrency;
    private double currencyCrossRate;
    private double fobSumInTargetCurrency;
    private String currency;
    private double customPriceInTargetCurrency;
    private double customDutyInTargetCurrency;
    private double totalCostInTargetCurrency;
    private double vatTotal;
    private double customFeeInTargetCurrency;
    private double seeDeliveryInTargetCurrency;
    private double railwayDeliveryInTargetCurrency;
    private double lastMileDeliveryInTargetCurrency;
    private double agentFee;
    private double totalPrice;
    private double totalPricePerSqmInTargetCurrency;

}
