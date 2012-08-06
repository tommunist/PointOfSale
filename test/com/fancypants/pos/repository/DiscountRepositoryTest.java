package com.fancypants.pos.repository;

import com.fancypants.pos.QuantityDiscountRule;
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
        Map<String, QuantityDiscountRule> productCodeToDiscountMap = new HashMap<String, QuantityDiscountRule>();
        QuantityDiscountRule discountRuleA = mock(QuantityDiscountRule.class);
        productCodeToDiscountMap.put("A", discountRuleA);
        DiscountRepository discountRepository = new DiscountRepository(productCodeToDiscountMap);

        assertThat(discountRepository.getDiscountRuleFor("A"), equalTo(discountRuleA));
    }

    @Test
    public void shouldIndicateErrorIfTryingToRetrieveADiscountThatDoesNotExist() {
        DiscountRepository discountRepository = new DiscountRepository(new HashMap<String, QuantityDiscountRule>());
        try {
            discountRepository.getDiscountRuleFor("A");
            fail("Expected exception to be thrown, none was");
        } catch (DiscountNotFoundException e) {
            assertThat(e.getMessage(), equalTo("Error: Could not find discount for product ['A']"));
        }
    }

    @Test
    public void shouldIndicateProductHasNoDiscountIfItIsNotInTheRepository() throws DiscountNotFoundException {
        Map<String, QuantityDiscountRule> productCodeToDiscountMap = new HashMap<String, QuantityDiscountRule>();
        DiscountRepository discountRepository = new DiscountRepository(productCodeToDiscountMap);

        assertThat(discountRepository.doesDiscountExistFor("A"), is(false));
    }

    @Test
    public void shouldIndicateProductHasDiscountIfItIsInTheRepository() throws DiscountNotFoundException {
        Map<String, QuantityDiscountRule> productCodeToDiscountMap = new HashMap<String, QuantityDiscountRule>();
        productCodeToDiscountMap.put("A", mock(QuantityDiscountRule.class));

        DiscountRepository discountRepository = new DiscountRepository(productCodeToDiscountMap);

        assertThat(discountRepository.doesDiscountExistFor("A"), is(true));
    }

}
