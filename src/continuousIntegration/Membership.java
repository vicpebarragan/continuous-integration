package continuousIntegration;

import java.util.*;

class Membership {
    String type;
    double baseCost;
    List<String> additionalFeatures = new ArrayList<>();
    double featuresCost;
    boolean isPremium;

    public Membership(String type, double baseCost, boolean isPremium) {
        this.type = type;
        this.baseCost = baseCost;
        this.isPremium = isPremium;
    }

    public double calculateTotalCost() {
        double total = baseCost + featuresCost;
        if (isPremium) {
            total += total * 0.15; // 15% surcharge for premium
        }
        return total;
    }
}
