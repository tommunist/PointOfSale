package com.fancypants.pos.parser;

import com.fancypants.pos.domain.Pricing;
import com.fancypants.pos.exception.PricingParseException;
import com.fancypants.pos.io.CsvFileReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PricingParser {
    private CsvFileReader csvFileReader;

    public PricingParser(CsvFileReader csvFileReader) {
        this.csvFileReader = csvFileReader;
    }

    public List<Pricing> parse() throws PricingParseException, IOException {
        List<String> values;
        List<Pricing> pricing = new ArrayList<Pricing>();
        while ((values = csvFileReader.readLine()) != null) {
            if (values.size() != 4) {
                throw new PricingParseException("Could not parse, had only " + values.size() + " columns in file instead of 4");
            }
            pricing.add(new Pricing(values.get(0), new BigDecimal(values.get(1)), new BigDecimal(values.get(2)), Integer.valueOf(values.get(3))));
        }
        return pricing;
    }
}
