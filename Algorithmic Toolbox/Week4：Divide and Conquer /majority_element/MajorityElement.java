import java.util.*;
import java.io.*;

public class MajorityElement {
    //private static int getMajorityElement(int[] a, int left, int right) {
        //return getMajorityElement(a);
    //}
    private static int getMajorityElement(int[] a) {
        int count = 0;
        int target = 0;
        for (int i = 1; i < a.length; i++) {
            if (count == 0) {
                target = a[i];
                count++;
            }
            else if (target == a[i])
                count++;
            else if (target != a[i])
                count--;
        }
        count = 0;
        for (int num : a) {
            if (num == target)
                count++;
        }
        return count > a.length / 2 ? 1 : 0;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        /*if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }*/
        System.out.println(getMajorityElement(a));
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

