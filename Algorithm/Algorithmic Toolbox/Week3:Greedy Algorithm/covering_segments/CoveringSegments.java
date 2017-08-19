import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        List<Integer> list = new LinkedList<>();
        Arrays.sort(segments);
        int point = segments[0].end;
        list.add(point);
        for(Segment segment : segments) {
            if (point > segment.end || point < segment.start) {
                point = segment.end;
                list.add(point);
            }
        }
        int[] points = new int[list.size()];
        for (int i = 0; i < points.length; i++) {
            points[i] = list.get(i);
        }
        return points;
    }

    private static class Segment implements Comparable<Segment>{
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override public int compareTo(Segment other) {
            return this.end - other.end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
