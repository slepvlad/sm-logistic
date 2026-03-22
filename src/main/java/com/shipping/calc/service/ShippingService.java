package com.shipping.calc.service;

import com.shipping.calc.model.ShippingRequest;
import com.shipping.calc.model.ShippingResponse;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    // MVP constants — will be replaced by DB values in future
    private static final String TARGET_CURRENCY = "RUR";

    public ShippingResponse calculate(ShippingRequest request) {
        double qty = request.getQuantitySquareMeters();
        double rate = request.getCurrencyCrossRate();
        double pricePerSqm = request.getPricePerSquareMeter();

        // Step 1: Base cost in seller's currency
        double baseCost = qty * pricePerSqm;

        // Step 2: Convert to target currency using cross rate
        double totalCostConverted = baseCost * rate;

        return new ShippingResponse(
                qty,
                rate,
                pricePerSqm,
                Math.round(baseCost * 100.0) / 100.0,
                Math.round(totalCostConverted * 100.0) / 100.0,
                TARGET_CURRENCY
        );
    }
}
