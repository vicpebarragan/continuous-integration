package continuousIntegration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GymMembershipManagementSystem {
	private static final double GROUP_DISCOUNT = 0.10;
    private static final double DISCOUNT_200 = 20;
    private static final double DISCOUNT_400 = 50;

    private static List<Membership> availableMemberships;
    private static Map<String, Double> additionalFeaturesMap;

    public static void main(String[] args) {
        initializeMemberships();
        initializeAdditionalFeatures();
        displayMemberships();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select a membership type: ");
        String type = scanner.nextLine();
        Membership selectedMembership = selectMembership(type);

        if (selectedMembership == null) {
            System.out.println("Invalid membership type selected. Exiting...");
            System.exit(-1);
        }

        displayAdditionalFeatures();
        System.out.print("Select additional features (comma-separated): ");
        String featuresInput = scanner.nextLine();
        addFeatures(selectedMembership, featuresInput);

        applyDiscounts(selectedMembership);
        confirmAndDisplayCost(selectedMembership);
    }

    private static void initializeMemberships() {
        availableMemberships = new ArrayList<>();
        availableMemberships.add(new Membership("Basic", 100, false));
        availableMemberships.add(new Membership("Premium", 200, true));
        availableMemberships.add(new Membership("Family", 150, false));
    }

    private static void initializeAdditionalFeatures() {
        additionalFeaturesMap = new HashMap<>();
        additionalFeaturesMap.put("Personal Training", 30.0);
        additionalFeaturesMap.put("Group Classes", 20.0);
        additionalFeaturesMap.put("Spa Access", 40.0);
    }

    private static void displayMemberships() {
        System.out.println("Available Memberships:");
        for (Membership m : availableMemberships) {
            System.out.println(m.type + " - $" + m.baseCost + (m.isPremium ? " (Premium)" : ""));
        }
    }

    private static Membership selectMembership(String type) {
        for (Membership m : availableMemberships) {
            if (m.type.equalsIgnoreCase(type)) {
                return m;
            }
        }
        return null;
    }

    private static void displayAdditionalFeatures() {
        System.out.println("Available Additional Features:");
        for (Map.Entry<String, Double> entry : additionalFeaturesMap.entrySet()) {
            System.out.println(entry.getKey() + " - $" + entry.getValue());
        }
    }

    private static void addFeatures(Membership membership, String featuresInput) {
        String[] features = featuresInput.split(",");
        for (String feature : features) {
            feature = feature.trim();
            if (additionalFeaturesMap.containsKey(feature)) {
                membership.additionalFeatures.add(feature);
                membership.featuresCost += additionalFeaturesMap.get(feature);
            } else {
                System.out.println("Feature " + feature + " is not available.");
            }
        }
    }

    private static void applyDiscounts(Membership membership) {
        double totalCost = membership.calculateTotalCost();

        // Special discounts
        if (totalCost > 400) {
            totalCost -= DISCOUNT_400;
        } else if (totalCost > 200) {
            totalCost -= DISCOUNT_200;
        }

        membership.featuresCost = totalCost - membership.baseCost;
    }

    private static void confirmAndDisplayCost(Membership membership) {
        System.out.println("Membership Summary:");
        System.out.println("Type: " + membership.type);
        System.out.println("Base Cost: $" + membership.baseCost);
        System.out.println("Additional Features: " + membership.additionalFeatures);
        System.out.println("Total Cost: $" + membership.calculateTotalCost());
    }
}
