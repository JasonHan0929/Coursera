import java.util.*;

public class GCD {
  private static int gcd_naive(int a, int b) {
    int max = Math.max(a, b);
    int min = Math.min(a, b);
    if (min == 0)
      return max;

    return gcd_naive(max % min, min);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcd_naive(a, b));
  }
}
