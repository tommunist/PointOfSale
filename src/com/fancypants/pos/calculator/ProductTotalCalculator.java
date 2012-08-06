package com.fancypants.pos.calculator;

import com.fancypants.pos.QuantityDiscountRule;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.repository.DiscountRepository;
import com.fancypants.pos.repository.UnitPriceRepository;

import java.math.BigDecimal;

public class ProductTotalCalculator {
    private DiscountRepository discountRepository;
    private UnitPriceRepository unitPriceRepository;

    public ProductTotalCalculator(DiscountRepository discountRepository, UnitPriceRepository unitPriceRepository) {
        this.discountRepository = discountRepository;
        this.unitPriceRepository = unitPriceRepository;
    }

    public BigDecimal calculateTotalFor(String productCode, Integer quantity) throws DiscountNotFoundException, PriceNotFoundException {
        if (discountRepository.doesDiscountExistFor(productCode)) {
            return calculateDiscountedTotal(productCode, quantity);
        }
        return calculateUndiscountedTotal(productCode, quantity);
    }

    private BigDecimal calculateUndiscountedTotal(String productCode, Integer quantity) throws PriceNotFoundException {
        return unitPriceRepository.getPriceFor(productCode).multiply(new BigDecimal(quantity));
    }

    private BigDecimal calculateDiscountedTotal(String productCode, Integer quantity) throws DiscountNotFoundException, PriceNotFoundException {
        QuantityDiscountRule discountRule = discountRepository.getDiscountRuleFor(productCode);
        Integer thresholdQuantity = discountRule.getThresholdQuantity();
        BigDecimal discountedChunkAmount = discountRule.getDiscountedAmount();
        BigDecimal discountedItemsSubtotal = new BigDecimal(quantity / thresholdQuantity).multiply(discountedChunkAmount);
        BigDecimal undiscountedItemsSubtotal = calculateUndiscountedTotal(productCode, quantity % thresholdQuantity);
        return discountedItemsSubtotal.add(undiscountedItemsSubtotal);
    }
}
