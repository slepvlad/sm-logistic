package com.shipping.calc.model;

public class ShippingResponse {

    private double quantitySquareMeters;
    private double currencyCrossRate;
    private double pricePerSquareMeter;
    private double baseCost;
    private double totalCostConverted;
    private String currency;

    public ShippingResponse(double quantitySquareMeters, double currencyCrossRate,
                            double pricePerSquareMeter, double baseCost,
                            double totalCostConverted, String currency) {
        this.quantitySquareMeters = quantitySquareMeters;
        this.currencyCrossRate = currencyCrossRate;
        this.pricePerSquareMeter = pricePerSquareMeter;
        this.baseCost = baseCost;
        this.totalCostConverted = totalCostConverted;
        this.currency = currency;
    }

    public double getQuantitySquareMeters() { return quantitySquareMeters; }
    public double getCurrencyCrossRate() { return currencyCrossRate; }
    public double getPricePerSquareMeter() { return pricePerSquareMeter; }
    public double getBaseCost() { return baseCost; }
    public double getTotalCostConverted() { return totalCostConverted; }
    public String getCurrency() { return currency; }
}
