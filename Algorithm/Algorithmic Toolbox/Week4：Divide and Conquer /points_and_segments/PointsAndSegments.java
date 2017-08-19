import java.util.*;

public class PointsAndSegments {

    /*private static class Pair implements Comparable<Pair>{
        public int value;
        public int index;
        public int count;
        private Pair(int value, int index, int count) {
            this.value = value;
            this.index = index;
            this.count = count;
        }
        @Override public int compareTo(Pair other) {
            return this.value > other.value ? 1 : this.value == other.value ? 0 : -1;
        }
    }

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int len = points.length;
        int[] cnt = new int[len];
        Pair[] assit = new Pair[len];
        for (int i = 0; i < len; i++)
            assit[i] = new Pair(points[i], i, 0);
        Arrays.sort(assit);
        int first, last;
        //int compensate = 0;
        for (int i = 0; i < starts.length; i++) {
            first = search(assit, starts[i], false);
            last = search(assit, ends[i], true);
            if (first <= last) {
                //if (first == -1)
                    //compensate += 1;
               // else
                assit[first].count += 1;
                if (last < len - 1)
                    assit[last + 1].count -= 1;
            }
        }
        //assit[0].count += compensate;
        cnt[0] = assit[0].count;
        for (int i = 1; i < len; i++) {
            assit[i].count += assit[i - 1].count;
            cnt[assit[i].index] = assit[i].count;
        }
        return cnt;
    }

    private static int search(Pair[] array, int key, boolean isRight) {
        int left = 0;
        int right = array.length - 1;
        int mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (array[mid].value < key)
                left = mid + 1;
            else if (array[mid].value == key)
                return mid;
            else
                right = mid - 1;
        }
        return isRight ? right : left;
    }*/

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        Arrays.sort(starts);
        Arrays.sort(ends);
        int[] result = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            int count1 = 0;
            for (int j = 0; j < starts.length; j++) {
                if (points[i] >= starts[j])
                    count1++;
                else
                    break;
            }
            int count2 = 0;
            for (int j = 0; j < ends.length; j++) {
                if (points[i] > ends[j])
                    count2++;
                else
                    break;
            }
            result[i] = count1 - count2;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

