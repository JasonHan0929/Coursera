import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }

        public double dist(Point o) {
            return Math.sqrt((x-o.x) * (x-o.x) + (y - o. y)* (y - o. y));
        }

    }

    static double minimalDistance(int[] x, int y[]) {
        Point[] pointsY = new Point[x.length];
        Point[] pointsX = new Point[x.length];
        for (int i = 0; i < x.length ; i++) {
            pointsY[i] = new Point(x[i], y[i]);
            pointsX[i] = pointsY[i];
        }
        Arrays.sort(pointsY);
        Arrays.sort(pointsX, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x == o2.x ? Long.signum(o1.y - o2.y) : Long.signum(o1.x - o2.x);
            }
        });
        return mergeSort(pointsX, pointsY, 0, x.length - 1);
    }

    static double mergeSort(Point[] pointsX, Point[] pointsY, int left, int right) {
        if (right - left <= 2) {
            double tempMin = Double.POSITIVE_INFINITY; 
            for (int i = left; i <= right; i++ ) {
                for (int j = i + 1; j<= right; j++) {
                    tempMin = tempMin <= pointsX[i].dist(pointsX[j]) ? tempMin : pointsX[i].dist(pointsX[j]);
                }
            }
            return tempMin;
        }
        int mid = left + ((right - left) >> 1);
        Point[] pointsYL = new Point[mid - left + 1];
        Point[] pointsYR = new Point[right - mid];
        int l = 0;
        int r = 0;
        for (int i = 0; i < pointsY.length; i++) {
            if (pointsY[i].x <= pointsX[mid].x)
                pointsYL[l++] = pointsY[i];
            else
                pointsYR[r++] = pointsY[i];
        }
        double min = Math.min(mergeSort(pointsX, pointsYL, left, mid), mergeSort(pointsX, pointsYR,mid + 1, right));
        List<Point> strip = new ArrayList<>();
        for (int i = 0; i < pointsY.length; i++) {
            if (Math.abs(pointsY[i].x - pointsX[mid].x) < min)
                strip.add(pointsY[i]);
        }
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && strip.get(j).y - strip.get(i).y < min; j++) {
                double temp = strip.get(i).dist(strip.get(j));
                min = temp < min ? temp : min;
            }
        }
        /*List<Point> stripLeft = new ArrayList<>();
        List<Point> stripRight = new ArrayList<>();
        for (int i = 0; i < pointsY.length; i++) {
            long temp = pointsY[i].x;
            if (temp <= upper && temp >= lower && Math.abs(temp - average) <= min)
                if (temp > average)
                    stripRight.add(pointsY[i]);
                else
                    stripLeft.add(pointsY[i]);
        }
        for (Point pointLeft : stripLeft) {
            for (Point pointRight : stripRight) {
                if (pointLeft.y < pointRight.y ) {
                    if (pointRight.y - pointLeft.y < min) {
                        double temp = pointRight.dist(pointLeft);
                        min = temp < min ? temp : min;
                    }
                    else
                        break;
                }
                else {
                    if (pointLeft.y - pointRight.y < min) {
                        double temp = pointRight.dist(pointLeft);
                        min = temp < min ? temp : min;
                    }
                    else
                        continue;
                }
            }
        }*/
        /*List<Point> strip = new ArrayList<>();
        for (int i = 0; i < pointsY.length; i++) {
            long temp = pointsY[i].x;
            if (temp <= upper && temp >= lower && Math.abs(temp - average) <= min)
                strip.add(pointsY[i]);
        }
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                if (strip.get(j).y - strip.get(i).y <= min) {
                    double temp = strip.get(i).dist(strip.get(j));
                    min = temp < min ? temp : min;
                }
                else
                    break;
            }
        }*/
        /*Set<Point> hash = new HashSet<>();
        for (int i = left; i <= right; i++)
            hash.add(pointsX[i]);
        List<Point> strip = new ArrayList<>();
        for (int i = 0; i < pointsY.length; i++) {
            long temp = pointsY[i].x;
            if (hash.contains(pointsY[i]) && Math.abs(temp - average) <= min)
                strip.add(pointsY[i]);
        }
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                if (strip.get(j).y - strip.get(i).y <= min) {
                    double temp = strip.get(i).dist(strip.get(j));
                    min = temp < min ? temp : min;
                }
                else
                    break;
            }
        }*/
        return min;
    }


    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
