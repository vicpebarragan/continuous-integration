package tests;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import continuousIntegration.Membership;

class MembershipTest {

    @Test
    void testCalculateTotalCostBasic() {
        Membership basic = new Membership("Basic", 100, false);
        assertEquals(100, basic.calculateTotalCost(), "Basic membership cost should be 100.");
    }

    @Test
    void testCalculateTotalCostWithFeatures() {
        Membership premium = new Membership("Premium", 200, false);
        premium.additionalFeatures.add("Personal Training");
        premium.featuresCost = 30.0;
        assertEquals(230, premium.calculateTotalCost(), "Premium membership with feature should be 230.");
    }

    @Test
    void testCalculateTotalCostWithPremiumSurcharge() {
        Membership premium = new Membership("Premium", 200, true);
        assertEquals(230, premium.calculateTotalCost(), "Premium membership cost with surcharge should be 230.");
    }

    @Test
    void testApplyGroupDiscount() {
        Membership family = new Membership("Family", 150, false);
        family.featuresCost = 50;
        double totalCost = family.calculateTotalCost();
        double discountedCost = totalCost * (1 - 0.10); // applying 10% discount for group memberships
        assertEquals(180, discountedCost, 0.01, "Family membership cost with group discount should be 180.");
    }

    @Test
    void testApplySpecialOfferDiscount() {
        Membership basic = new Membership("Basic", 300, false);
        basic.featuresCost = 150;
        double totalCost = basic.calculateTotalCost();

        // Apply special offer discounts
        if (totalCost > 400) {
            totalCost -= 50;
        } else if (totalCost > 200) {
            totalCost -= 20;
        }

        assertEquals(430, totalCost, "Total cost with special discount applied should be 430.");
    }
}

