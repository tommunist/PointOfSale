package com.fancypants.pos;

import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.exception.ProductCodeNotRecognisedException;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ScannerTest {


    @Test
    public void shouldReturnPriceIfItExistsInTheRepository() throws PriceNotFoundException {
        Map<String, Pricing> productCodeToPricingMap = new HashMap<String, Pricing>();
        Pricing pricingA = mock(Pricing.class);
        productCodeToPricingMap.put("A", pricingA);
        Scanner scanner = new Scanner(productCodeToPricingMap);

        assertThat(scanner.scan("A"), equalTo(pricingA));
    }

    @Test
    public void shouldIndicateErrorIfItDoesNotExistInTheRepository() {
        Map<String, Pricing> productCodeToPricingMap = new HashMap<String, Pricing>();
        Scanner scanner = new Scanner(productCodeToPricingMap);

        try {
            scanner.scan("Z");
            Assert.fail("Expected exception to be thrown, none was");
        } catch (ProductCodeNotRecognisedException e) {
            assertThat(e.getMessage(), equalTo("Error: Could not find price for product ['Z']"));
        }
    }

}
