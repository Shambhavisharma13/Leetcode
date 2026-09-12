import java.util.*;

class Solution {

    static class Edge {
        int dest;
        int wt;

        Edge(int dest, int wt) {
            this.dest = dest;
            this.wt = wt;
        }
    }

    static class Pair implements Comparable<Pair> {
        int node;
        int distance;

        Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.distance - p2.distance;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {

        // 1. Create adjacency list
        ArrayList<Edge>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 2. Add edges from times
        for (int i = 0; i < times.length; i++) {

            int u = times[i][0];
            int v = times[i][1];
            int wt = times[i][2];

            graph[u].add(new Edge(v, wt));
        }

        // 3. Distance array
        int[] dist = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        // Source distance = 0
        dist[k] = 0;

        // 4. Priority Queue
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(k, 0));

        // 5. Dijkstra
        while (!pq.isEmpty()) {

            Pair curr = pq.remove();

            int u = curr.node;

            for (Edge e : graph[u]) {

                int v = e.dest;
                int wt = e.wt;

                // Relaxation
                if (dist[u] != Integer.MAX_VALUE
                        && dist[u] + wt < dist[v]) {

                    dist[v] = dist[u] + wt;

                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        // 6. Find maximum distance
        int answer = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }
}