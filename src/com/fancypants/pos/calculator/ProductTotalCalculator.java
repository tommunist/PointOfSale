package com.fancypants.pos.calculator;

import com.fancypants.pos.VolumeDiscount;
import com.fancypants.pos.exception.DiscountNotFoundException;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.repository.DiscountRepository;
import com.fancypants.pos.repository.PricingRepository;

import java.math.BigDecimal;

public class ProductTotalCalculator {
    private DiscountRepository discountRepository;
    private PricingRepository pricingRepository;

    public ProductTotalCalculator(DiscountRepository discountRepository, PricingRepository pricingRepository) {
        this.discountRepository = discountRepository;
        this.pricingRepository = pricingRepository;
    }

    public BigDecimal calculateTotalFor(String productCode, Integer quantity) throws DiscountNotFoundException, PriceNotFoundException {
        if (discountRepository.doesDiscountExistFor(productCode)) {
            return calculateDiscountedTotal(productCode, quantity);
        }
        return calculateUndiscountedTotal(productCode, quantity);
    }

    private BigDecimal calculateUndiscountedTotal(String productCode, Integer quantity) throws PriceNotFoundException {
        return pricingRepository.getPriceFor(productCode).multiply(new BigDecimal(quantity));
    }

    private BigDecimal calculateDiscountedTotal(String productCode, Integer quantity) throws DiscountNotFoundException, PriceNotFoundException {
        VolumeDiscount discount = discountRepository.getDiscountRuleFor(productCode);
        Integer thresholdQuantity = discount.getQuantity();
        BigDecimal discountedChunkAmount = discount.getAmount();
        BigDecimal discountedItemsSubtotal = new BigDecimal(quantity / thresholdQuantity).multiply(discountedChunkAmount);
        BigDecimal undiscountedItemsSubtotal = calculateUndiscountedTotal(productCode, quantity % thresholdQuantity);
        return discountedItemsSubtotal.add(undiscountedItemsSubtotal);
    }
}
