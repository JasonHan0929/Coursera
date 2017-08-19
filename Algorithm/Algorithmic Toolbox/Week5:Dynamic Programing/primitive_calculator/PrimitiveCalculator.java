import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1; i <= n; i++)
            dp[i] = i;
        List<Integer> result = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (i * 3 <= n)
                dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
            if (i * 2 <= n)
                dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
            if (i + 1 <= n)
                dp[i + 1] = Math.min(dp[i] + 1, dp[i + 1]);
        }
        int cur = n;
        while (cur > 0) {
            result.add(0, cur);
            if (cur % 3 == 0 && dp[cur] == dp[cur / 3] + 1)
                cur = cur / 3;
            else if (cur % 2 == 0 && dp[cur] == dp[cur / 2] + 1)
                cur = cur / 2;
            else if (dp[cur] == dp[cur - 1] + 1)
                cur = cur - 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

