package com.fancypants.pos.repository;

import com.fancypants.pos.exception.PriceNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class PricingRepositoryTest {

    private PricingRepository pricingRepository;

    @Before
    public void setUp() {
        Map<String, BigDecimal> productCodeToPriceMap = new HashMap<String, BigDecimal>();
        productCodeToPriceMap.put("A", new BigDecimal("1.23"));
        pricingRepository = new PricingRepository(productCodeToPriceMap);
    }

    @Test
    public void shouldReturnPriceIfItExistsInTheRepository() throws PriceNotFoundException {
        assertThat(pricingRepository.getPriceFor("A"), equalTo(new BigDecimal("1.23")));
    }

    @Test
    public void shouldIndicateErrorIfTryingToRetrieveADiscountThatDoesNotExist() {
        try {
            pricingRepository.getPriceFor("Z");
            fail("Expected exception to be thrown, none was");
        } catch (PriceNotFoundException e) {
            assertThat(e.getMessage(), equalTo("Error: Could not find price for product ['Z']"));
        }
    }

}
