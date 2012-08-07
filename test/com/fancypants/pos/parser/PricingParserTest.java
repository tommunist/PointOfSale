package com.fancypants.pos.parser;

import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.PricingParseException;
import com.fancypants.pos.io.CsvFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PricingParserTest {
    private CsvFileReader csvFileReader;
    private PricingParser pricingParser;

    @Before
    public void setUp() throws Exception {
        csvFileReader = mock(CsvFileReader.class);
        pricingParser = new PricingParser(csvFileReader);
    }

    @Test
    public void shouldIndicateErrorIfNumberOfColumnsDoesNotEqualFour() throws PricingParseException, IOException {
        List<String> values = new ArrayList<String>();
        values.add("1");
        when(csvFileReader.readLine()).thenReturn(values);
        try {
            pricingParser.parse();
            fail("expected exception but none was thrown");
        } catch (PricingParseException e) {
            assertThat(e.getMessage(), is("Could not parse, had only 1 columns in file instead of 4"));
        }

    }

    @Test
    public void shouldCreateValidPricingFromCsvValues() throws PricingParseException, IOException {
        List<String> values = new ArrayList<String>();
        values.add("A");
        values.add("2.00");
        values.add("8.00");
        values.add("5");

        when(csvFileReader.readLine())
                .thenReturn(values)
                .thenReturn(null);

        List<Pricing> pricing = pricingParser.parse();
        assertThat(pricing.size(), equalTo(1));
        assertThat(pricing.get(0).getProductCode(), equalTo("A"));
        assertThat(pricing.get(0).getUnitPrice(), equalTo(new BigDecimal("2.00")));
        assertThat(pricing.get(0).getVolumePrice(), equalTo(new BigDecimal("8.00")));
        assertThat(pricing.get(0).getVolumeQuantity(), equalTo(5));

    }


}
