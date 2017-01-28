import java.util.*;

class EditDistance {
    public static int EditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        int[][] dp = new int[lenS + 1][lenT + 1];
        for (int i = 0; i <= lenS; i++)
            dp[i][0] = i;
        for (int j = 0; j <= lenT; j++)
            dp[0][j] = j;
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenT; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = findMin(dp[i - 1][j - 1], dp[i][j - 1] + 1, dp[i - 1][j] + 1);
                else
                    dp[i][j] = findMin(dp[i - 1][j - 1] + 1, dp[i][j - 1] + 1, dp[i - 1][j] + 1);
            }
        }
        return dp[lenS][lenT];
    }
    private static int findMin(int...o) {
        int min = Integer.MAX_VALUE;
        for (int num : o)
            min = Math.min(num, min);
        return min;
    }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
