import java.util.*;

public class PlacingParentheses {
   private static long getMaximValue(String exp) {
        int n = exp.length();
        long[][] min = new long[n][n];
        for (long[] array : min)
            Arrays.fill(array, Long.MAX_VALUE);
        long[][] max = new long[n][n];
        for (long[] array : max)
            Arrays.fill(array, Long.MIN_VALUE);
        for (int i = 0; i < n; i ++) {
            if (Character.isDigit(exp.charAt(i))) {
                min[i][i] = exp.charAt(i) - '0';
                max[i][i] = exp.charAt(i) - '0';
            }
        }
        for (int i = 2; i <= n - 1; i += 2) {
            for (int j = 0; j < n - i; j += 2) {
                for (int s = j; s <= j + i; s++) {
                    if (!Character.isDigit(exp.charAt(s))) {
                        long tempMin = findMin(
                                eval(min[j][s - 1], min[s + 1][j + i], exp.charAt(s)),
                                eval(min[j][s - 1], max[s + 1][j + i], exp.charAt(s)),
                                eval(max[j][s - 1], min[s + 1][j + i], exp.charAt(s)),
                                eval(max[j][s - 1], max[s + 1][j + i], exp.charAt(s)));
                        long tempMax = findMax(
                                eval(min[j][s - 1], min[s + 1][j + i], exp.charAt(s)),
                                eval(min[j][s - 1], max[s + 1][j + i], exp.charAt(s)),
                                eval(max[j][s - 1], min[s + 1][j + i], exp.charAt(s)),
                                eval(max[j][s - 1], max[s + 1][j + i], exp.charAt(s)));
                        min[j][j + i] = Math.min(tempMin, min[j][j + i]);
                        max[j][j + i] = Math.max(tempMax, max[j][j + i]);
                    }
                }
            }
        }
        return max[0][n - 1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    private static long findMin(long...o) {
        long min = Integer.MAX_VALUE;
        for (long num : o)
            min = Math.min(num, min);
        return min;
    }
    private static long findMax(long...o) {
        long max = Integer.MIN_VALUE;
        for (long num : o)
            max = Math.max(num, max);
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

