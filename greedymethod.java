import java.util.*;

public class FractionalKnapsack {

    static class Item {
        double value;
        double weight;
        double ratio;

        public Item(double value, double weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = value / weight;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        System.out.print("Enter the knapsack capacity: ");
        double capacity = scanner.nextDouble();

        Item[] items = new Item[n];

        System.out.println("Enter the value and weight for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " value: ");
            double value = scanner.nextDouble();

            System.out.print("Item " + (i + 1) + " weight: ");
            double weight = scanner.nextDouble();

            items[i] = new Item(value, weight);
        }

        double[] result = fractionalKnapsack(items, capacity);

        System.out.println("\nMaximum value: " + result[0]);
        System.out.println("\nSelected items (value, weight, fraction):");

        for (int i = 0; i < n; i++) {
            if (result[i + 1] > 0) {
                Item item = items[i];
                System.out.printf("  Value: %.2f, Weight: %.2f, Fraction taken: %.2f\n",
                        item.value, item.weight, result[i + 1]);
                System.out.printf("  Contribution: %.2f\n", item.value * result[i + 1]);
            }
        }

        scanner.close();
    }

    public static double[] fractionalKnapsack(Item[] items, double capacity) {
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return Double.compare(item2.ratio, item1.ratio);
            }
        });

        double totalValue = 0;
        double remainingCapacity = capacity;

        double[] result = new double[items.length + 1];

        Map<Item, Integer> originalIndices = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            originalIndices.put(items[i], i);
        }

        for (Item item : items) {
            if (remainingCapacity == 0) {
                break;
            }

            if (item.weight <= remainingCapacity) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
                result[originalIndices.get(item) + 1] = 1.0;
            } else {
                double fraction = remainingCapacity / item.weight;
                totalValue += item.value * fraction;
                remainingCapacity = 0;
                result[originalIndices.get(item) + 1] = fraction;
            }
        }

        result[0] = totalValue;
        return result;
    }
}
