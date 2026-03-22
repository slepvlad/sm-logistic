package com.shipping.calc.service;

import com.shipping.calc.model.ShippingRequest;
import com.shipping.calc.model.ShippingResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {
	
	// MVP constants — will be replaced by DB values in future
	private static final Currency SOURCE_CURRENCY = Currency.getInstance("CNY");
	private static final Currency TARGET_CURRENCY = Currency.getInstance("RUR");
	private static final BigDecimal DELIVERY_TO_BORDER_AMOUNT_RUB = BigDecimal.valueOf(182750.0);
	private static final BigDecimal CUSTOM_DUTY_RATE_PERCENT = BigDecimal.valueOf(0.15);
	private static final BigDecimal VAT_RATE = BigDecimal.valueOf(0.22);
	private static final BigDecimal CUSTOM_FEE_IN_TARGET_CURRENCY = BigDecimal.valueOf(20000);
	private static final BigDecimal SEE_DELIVERY_IN_TARGET_CURRENCY = BigDecimal.valueOf(182750);
	private static final BigDecimal RAILWAY_DELIVERY_IN_TARGET_CURRENCY = BigDecimal.valueOf(232900);
	private static final BigDecimal LAST_MILE_DELIVERY_IN_TARGET_CURRENCY = BigDecimal.valueOf(51810);
	private static final BigDecimal AGENT_FEE_RATE = BigDecimal.valueOf(0.06);
	
	
	public ShippingResponse calculate(ShippingRequest request) {
		var quantitySquareMeters = BigDecimal.valueOf(request.getQuantitySquareMeters());
		var currencyCrossRate = BigDecimal.valueOf(request.getCurrencyCrossRate());
		var pricePerSqmInSourceCurrency = BigDecimal.valueOf(request.getPricePerSquareMeter());
		
		//сумма FOB в валюте поставщика
		var fobSumInSourceCurrency = quantitySquareMeters.multiply(pricePerSqmInSourceCurrency);
		var fobSumInTargetCurrency = fobSumInSourceCurrency.multiply(currencyCrossRate);
		var customPriceInTargetCurrency = fobSumInTargetCurrency.add(DELIVERY_TO_BORDER_AMOUNT_RUB);
		var customDutyInTargetCurrency = CUSTOM_DUTY_RATE_PERCENT.multiply(
				customPriceInTargetCurrency);
		var totalCostInTargetCurrency = customPriceInTargetCurrency.add(customDutyInTargetCurrency);
		var vatTotal = totalCostInTargetCurrency.multiply(VAT_RATE);
		var agentFee = AGENT_FEE_RATE.multiply(fobSumInTargetCurrency);
		var totalPrice = totalCostInTargetCurrency
				.add(vatTotal)
				.add(CUSTOM_FEE_IN_TARGET_CURRENCY)
				.add(SEE_DELIVERY_IN_TARGET_CURRENCY)
				.add(RAILWAY_DELIVERY_IN_TARGET_CURRENCY)
				.add(LAST_MILE_DELIVERY_IN_TARGET_CURRENCY)
				.add(agentFee);
		
		var totalPricePerSqmInTargetCurrency = totalPrice
				.divide(quantitySquareMeters, RoundingMode.HALF_UP);
		
		return ShippingResponse.builder()
				.quantitySquareMeters(quantitySquareMeters.doubleValue())
				.pricePerSqmInSourceCurrency(pricePerSqmInSourceCurrency.doubleValue())
				.fobSumInSourceCurrency(fobSumInSourceCurrency.doubleValue())
				.currencyCrossRate(currencyCrossRate.doubleValue())
				.fobSumInTargetCurrency(fobSumInTargetCurrency.doubleValue())
				.customPriceInTargetCurrency(customPriceInTargetCurrency.doubleValue())
				.customDutyInTargetCurrency(customDutyInTargetCurrency.doubleValue())
				.totalCostInTargetCurrency(totalCostInTargetCurrency.doubleValue())
				.vatTotal(vatTotal.doubleValue())
				.agentFee(agentFee.doubleValue())
				.totalPrice(totalPrice.doubleValue())
				.totalPricePerSqmInTargetCurrency(totalPricePerSqmInTargetCurrency.doubleValue())
				.currency(TARGET_CURRENCY.getCurrencyCode())
				.build();
	}
}
