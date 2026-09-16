import java.util.*;

class Solution {

    // Edge class
    static class Edge {
        int src;
        int dest;
        int wt;
        int idx;

        Edge(int src, int dest, int wt, int idx) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
            this.idx = idx;
        }
    }

    // DSU class
    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        boolean union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);

            // Cycle
            if (parentA == parentB) {
                return false;
            }

            if (rank[parentA] == rank[parentB]) {
                parent[parentB] = parentA;
                rank[parentA]++;
            }
            else if (rank[parentA] < rank[parentB]) {
                parent[parentA] = parentB;
            }
            else {
                parent[parentB] = parentA;
            }

            return true;
        }
    }

    // Kruskal
    // skip = edge that we don't want to use
    // force = edge that we must use first
    public int kruskal(int n, List<Edge> edges, int skip, int force) {

        DSU dsu = new DSU(n);

        int cost = 0;
        int count = 0;

        // Force an edge first
        if (force != -1) {

            Edge e = edges.get(force);

            if (dsu.union(e.src, e.dest)) {
                cost += e.wt;
                count++;
            }
        }

        // Normal Kruskal
        for (int i = 0; i < edges.size(); i++) {

            // Skip this edge
            if (i == skip) {
                continue;
            }

            Edge e = edges.get(i);

            if (dsu.union(e.src, e.dest)) {

                cost += e.wt;
                count++;

                // MST contains n-1 edges
                if (count == n - 1) {
                    break;
                }
            }
        }

        // If we couldn't connect all vertices
        if (count != n - 1) {
            return Integer.MAX_VALUE;
        }

        return cost;
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(
            int n, int[][] edges) {

        List<Edge> list = new ArrayList<>();

        // Store original index
        for (int i = 0; i < edges.length; i++) {

            list.add(new Edge(
                    edges[i][0],
                    edges[i][1],
                    edges[i][2],
                    i
            ));
        }

        // Sort edges according to weight
        Collections.sort(list, (a, b) -> a.wt - b.wt);

        // Original MST cost
        int originalCost = kruskal(n, list, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();

        // Check every edge
        for (int i = 0; i < list.size(); i++) {

            // 1. Remove this edge
            int costWithoutEdge = kruskal(n, list, i, -1);

            // If MST becomes more expensive
            if (costWithoutEdge > originalCost) {

                critical.add(list.get(i).idx);

            } else {

                // 2. Force this edge
                int costWithEdge = kruskal(n, list, -1, i);

                // If it can still produce an MST
                if (costWithEdge == originalCost) {
                    pseudoCritical.add(list.get(i).idx);
                }
            }
        }

        List<List<Integer>> answer = new ArrayList<>();

        answer.add(critical);
        answer.add(pseudoCritical);

        return answer;
    }
}