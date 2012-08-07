package com.fancypants.pos;

import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.PriceNotFoundException;
import com.fancypants.pos.exception.ProductCodeNotRecognisedException;
import com.fancypants.pos.repository.PricingRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ScannerTest {

    private Scanner scanner;
    private PricingRepository pricingRepository;

    @Before
    public void setUp() throws Exception {
        pricingRepository = mock(PricingRepository.class);
        scanner = new Scanner(pricingRepository);
    }

    @Test
    public void shouldPassCheckWhenProductCodeKnown() throws PriceNotFoundException {
        Pricing pricing = mock(Pricing.class);
        when(pricingRepository.getPricingFor("A")).thenReturn(pricing);
        assertThat(scanner.scan("A"), equalTo(pricing));
    }

    @Test
    public void shouldIndicateErrorIfProductCodeIsUnknown() throws PriceNotFoundException {
        try {
            doThrow(new ProductCodeNotRecognisedException("exception from repository")).when(pricingRepository).getPricingFor("B");
            scanner.scan("B");
            fail("Expected exception but none thrown");
        } catch (ProductCodeNotRecognisedException e) {
            assertThat(e.getMessage(), is("exception from repository"));
        }

    }
}
