import java.util.*;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double result = 0;
        Map<Double,Integer> map = new TreeMap<>(new Comparator<Double>(){
            public int compare(Double a, Double b) {
                return b > a ? 1 : b == a ? 0 : -1;
            }
        });
        for (int i = 0; i < values.length; i++)
             map.put((double)values[i] / weights[i], weights[i]);
        int pick;
        for (Double value : map.keySet()) {
            pick = Math.min(capacity, map.get(value));
            result += pick * value;
            capacity -= pick;
            if (capacity == 0)
                break;
        }
        return result;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
