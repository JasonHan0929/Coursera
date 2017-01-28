import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            b[left] = a[left];
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        for (int i = left; i < right; i++)
            b[i] = a[i];
        int j = left;
        int k = ave;
        for (int i = left; i < right; i++) {
            /*if (j >= ave) {
                a[i] = b[k];
                k++;
            }
            else if (k >= right) {
                a[i] = b[j];
                j++;
            }*/
            if (k >= right) {
                a[i] = b[j];
                j++;
            }
            else if (j >= ave) {
                a[i] = b[k];
                k++;
            }
            else if (b[k] < b[j]) {
                a[i] = b[k];
                numberOfInversions += ave - j;
                k++;
            }
            else if (b[k] >= b[j]) {
                a[i] = b[j];
                j++;
            }
        }
        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}

