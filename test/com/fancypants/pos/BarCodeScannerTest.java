package com.fancypants.pos;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class BarCodeScannerTest {

    @Test
    public void shouldRecogniseTypeAProduct() throws ProductNotRecognisedException {
        Product productA = new BarCodeScanner().scan("A");
        assertThat(productA.getCode(), equalTo("A"));
        assertThat(productA.getPrice(), equalTo(new BigDecimal("2.00")));
    }

    @Test
    public void shouldRecogniseTypeBProduct() throws ProductNotRecognisedException {
        Product productA = new BarCodeScanner().scan("B");
        assertThat(productA.getCode(), equalTo("B"));
        assertThat(productA.getPrice(), equalTo(new BigDecimal("12.00")));
    }

    @Test
    public void shouldRecogniseTypeCProduct() throws ProductNotRecognisedException {
        Product productA = new BarCodeScanner().scan("C");
        assertThat(productA.getCode(), equalTo("C"));
        assertThat(productA.getPrice(), equalTo(new BigDecimal("1.25")));
    }

    @Test
    public void shouldRecogniseTypeDProduct() throws ProductNotRecognisedException {
        Product productA = new BarCodeScanner().scan("D");
        assertThat(productA.getCode(), equalTo("D"));
        assertThat(productA.getPrice(), equalTo(new BigDecimal("0.15")));
    }

    @Test
    public void shouldIndicateErrorWhenProductNotRecognised() {
        try {
            new BarCodeScanner().scan("E");
            fail("Expected exception but none thrown");
        } catch (ProductNotRecognisedException e) {
            assertThat(e.getMessage(), is("Error: could not recognise product with code ['E']."));
        }

    }
}
