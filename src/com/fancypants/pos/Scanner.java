package com.fancypants.pos;

import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.ProductCodeNotRecognisedException;
import com.fancypants.pos.repository.PricingRepository;

public class Scanner {
    private PricingRepository pricingRepository;

    public Scanner(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

    public Pricing scan(String productCode) throws ProductCodeNotRecognisedException {
        return pricingRepository.getPricingFor(productCode);
    }

}
