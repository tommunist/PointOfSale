package com.fancypants.pos.repository;

import com.fancypants.pos.VolumeDiscount;
import com.fancypants.pos.exception.DiscountNotFoundException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class DiscountRepositoryTest {

    @Test
    public void shouldReturnDiscountRuleIfItExistsInTheRepository() throws DiscountNotFoundException {
        Map<String, VolumeDiscount> productCodeToDiscountMap = new HashMap<String, VolumeDiscount>();
        VolumeDiscount discountA = mock(VolumeDiscount.class);
        productCodeToDiscountMap.put("A", discountA);
        DiscountRepository discountRepository = new DiscountRepository(productCodeToDiscountMap);

        assertThat(discountRepository.getDiscountRuleFor("A"), equalTo(discountA));
    }

    @Test
    public void shouldIndicateErrorIfTryingToRetrieveADiscountThatDoesNotExist() {
        DiscountRepository discountRepository = new DiscountRepository(new HashMap<String, VolumeDiscount>());
        try {
            discountRepository.getDiscountRuleFor("A");
            fail("Expected exception to be thrown, none was");
        } catch (DiscountNotFoundException e) {
            assertThat(e.getMessage(), equalTo("Error: Could not find discount for product ['A']"));
        }
    }

    @Test
    public void shouldIndicateProductHasNoDiscountIfItIsNotInTheRepository() throws DiscountNotFoundException {
        Map<String, VolumeDiscount> productCodeToDiscountMap = new HashMap<String, VolumeDiscount>();
        DiscountRepository discountRepository = new DiscountRepository(productCodeToDiscountMap);

        assertThat(discountRepository.doesDiscountExistFor("A"), is(false));
    }

    @Test
    public void shouldIndicateProductHasDiscountIfItIsInTheRepository() throws DiscountNotFoundException {
        Map<String, VolumeDiscount> productCodeToDiscountMap = new HashMap<String, VolumeDiscount>();
        productCodeToDiscountMap.put("A", mock(VolumeDiscount.class));

        DiscountRepository discountRepository = new DiscountRepository(productCodeToDiscountMap);

        assertThat(discountRepository.doesDiscountExistFor("A"), is(true));
    }

}
