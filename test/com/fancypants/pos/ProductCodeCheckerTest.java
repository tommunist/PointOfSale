package com.fancypants.pos;

import com.fancypants.pos.exception.ProductNotRecognisedException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ProductCodeCheckerTest {

    private ProductCodeChecker productCodeChecker;

    @Before
    public void setUp() throws Exception {
        productCodeChecker = new ProductCodeChecker();
    }

    @Test
    public void shouldPassCheckWhenProductCodeKnown() throws ProductNotRecognisedException {
        productCodeChecker.add("A");
        productCodeChecker.checkProductCode("A");
    }

    @Test
    public void shouldIndicateErrorIfProductCodeIsUnknown() {
        try {
            productCodeChecker.checkProductCode("B");
            fail("Expected exception but none thrown");
        } catch (ProductNotRecognisedException e) {
            assertThat(e.getMessage(), is("Error: could not recognise product with code ['B']."));
        }

    }
}
