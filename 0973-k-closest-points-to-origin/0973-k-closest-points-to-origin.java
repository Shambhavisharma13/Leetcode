import java.util.*;

class Solution {

    static class Point implements Comparable<Point> {

        int x;
        int y;
        int distSq;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.distSq = x * x + y * y;
        }

        @Override
        public int compareTo(Point p2) {
            return Integer.compare(this.distSq, p2.distSq);
        }
    }

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<Point> pq = new PriorityQueue<>();

        // Add all points to PriorityQueue
        for (int i = 0; i < points.length; i++) {

            int x = points[i][0];
            int y = points[i][1];

            pq.add(new Point(x, y));
        }

        // Store answer
        int[][] result = new int[k][2];

        // Get k closest points
        for (int i = 0; i < k; i++) {

            Point p = pq.remove();

            result[i][0] = p.x;
            result[i][1] = p.y;
        }

        return result;
    }
}