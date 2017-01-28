import java.util.*;

public class LCS3 {

    /*private static int lcs3(int[] a, int[] b, int[] c) {
        return lcs2(lsc2(a, b), lsc2(b, c)).length;
    }
    private static int[] lcs2(int[] a, int[] b) {
        int lenA = a.length, lenB = b.length;
        int[][] dp = new int[lenA + 1][lenB + 1];
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (a[i - 1] == b[j - 1])
                    dp[i][j] = findMax(dp[i - 1][j - 1] + 1, dp[i - 1][j], dp[i][j - 1]);
                else
                    dp[i][j] = findMax(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int[] result = new int[dp[lenA][lenB]];
        int i = lenA, j = lenB, curr = result.length - 1;
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                result[curr] = a[i - 1];
                curr--;
                int[] temp = findIndex(dp[i][j], dp[i][j - 1], dp[i - 1][j], dp[i - 1][j  - 1] + 1, i, j);
                i = temp[0];
                j = temp[1];
            }
            else {
                int[] temp = findIndex(dp[i][j], dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1], i, j);
                i = temp[0];
                j = temp[1];
            }
        }
        return result;
    }*/ //wrong answer


    private static int lcs3(int[] a, int[] b, int[] c) {
        int lenA = a.length, lenB = b.length, lenC = c.length;
        int[][][] dp = new int[lenA + 1][lenB + 1][lenC + 1];
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                for (int k = 1; k <= lenC; k++) {
                    if (a[i - 1] == b[j - 1] && a[i - 1] == c[k - 1])
                        dp[i][j][k] = findMax(dp[i - 1][j - 1][k - 1] + 1, dp[i - 1][j][k], dp[i][j - 1][k], dp[i][j][k - 1]);
                    else
                        dp[i][j][k] = findMax(dp[i - 1][j - 1][k - 1], dp[i - 1][j][k], dp[i][j - 1][k], dp[i][j][k - 1]);
                }
            }
        }
        return dp[lenA][lenB][lenC];
    }


    private static int findMax(int...o) {
        int max = Integer.MIN_VALUE;
        for (int num : o)
            max = Math.max(max, num);
        return max;
    }
    private static int[] findIndex(int ij, int left, int up, int diagonal, int i, int j) {
        if (ij == left)
            return new int[]{i, j - 1};
        else if (ij == up)
            return new int[]{i - 1, j};
        else if (ij == diagonal)
            return new int[]{i - 1, j - 1};
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

