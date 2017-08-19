import java.util.*;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
    	long result = from == 0 ? getFibonacciSumNaive(to) : getFibonacciSumNaive(to) - getFibonacciSumNaive(from - 1);
    	if (result < 0)
    		result += 10;
    	return result;
    }

    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;
        n %= 60;
        long previous = 0;
        long current  = 1;
        long sum      = 1;
        long tmp_previous = 0;

        for (long i = 2; i <= n; i++) {
            tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
            sum = (sum +current) % 10;
        }

        return sum;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumNaive(from, to));
    }
}

