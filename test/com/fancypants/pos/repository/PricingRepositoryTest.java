package com.fancypants.pos.repository;

import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.exception.ProductCodeNotRecognisedException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class PricingRepositoryTest {

    private PricingRepository pricingRepository;

    @Test
    public void shouldReturnPriceIfItExistsInTheRepository() throws PriceNotFoundException {
        Map<String, Pricing> productCodeToPricingMap = new HashMap<String, Pricing>();
        Pricing pricingA = mock(Pricing.class);
        productCodeToPricingMap.put("A", pricingA);
        pricingRepository = new PricingRepository(productCodeToPricingMap);

        assertThat(pricingRepository.getPricingFor("A"), equalTo(pricingA));
    }

    @Test
    public void shouldIndicateErrorIfItDoesNotExistInTheRepository() {
        Map<String, Pricing> productCodeToPricingMap = new HashMap<String, Pricing>();
        pricingRepository = new PricingRepository(productCodeToPricingMap);

        try {
            pricingRepository.getPricingFor("Z");
            fail("Expected exception to be thrown, none was");
        } catch (ProductCodeNotRecognisedException e) {
            assertThat(e.getMessage(), equalTo("Error: Could not find price for product ['Z']"));
        }
    }

}
