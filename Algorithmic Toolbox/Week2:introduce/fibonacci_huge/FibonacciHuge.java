import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;
        long previous = 0;
        long current  = 1;
        LinkedList<Long> list = new LinkedList<>(Arrays.asList(previous, current));

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % m;
            list.add(current);
            if (previous == 0 && current == 1) {
                list.removeLast();
                list.removeLast();
                break;
            }
        }

        return list.get((int)(n % list.size()));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
    }
}

