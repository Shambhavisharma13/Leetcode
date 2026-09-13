import java.util.*;

class Solution {

    static class Pair implements Comparable<Pair> {

        int point;
        int cost;

        Pair(int point, int cost) {
            this.point = point;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    public int minCostConnectPoints(int[][] points) {

        int n = points.length;

        // Keep track of visited points
        boolean[] vis = new boolean[n];

        // PriorityQueue stores (point, cost)
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // Start from point 0 with cost 0
        pq.add(new Pair(0, 0));

        int finalCost = 0;
        int count = 0;

        while (!pq.isEmpty() && count < n) {

            // Take the point with minimum cost
            Pair curr = pq.remove();

            // If already visited, skip
            if (vis[curr.point]) {
                continue;
            }

            // Mark point as visited
            vis[curr.point] = true;

            // Add its connection cost
            finalCost += curr.cost;
            count++;

            // Check all other points
            for (int j = 0; j < n; j++) {

                if (!vis[j]) {

                    // Current point coordinates
                    int x1 = points[curr.point][0];
                    int y1 = points[curr.point][1];

                    // Other point coordinates
                    int x2 = points[j][0];
                    int y2 = points[j][1];

                    // Manhattan distance
                    int distance =
                            Math.abs(x1 - x2) +
                            Math.abs(y1 - y2);

                    // Add to PriorityQueue
                    pq.add(new Pair(j, distance));
                }
            }
        }

        return finalCost;
    }
}