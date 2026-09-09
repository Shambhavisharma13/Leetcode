class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Create graph
        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // Create directed edges
        for (int i = 0; i < prerequisites.length; i++) {

            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];

            graph[prerequisite].add(course);
        }

        // Visited and recursion stack
        boolean[] vis = new boolean[numCourses];
        boolean[] stack = new boolean[numCourses];

        // Check every component
        for (int i = 0; i < numCourses; i++) {

            if (!vis[i]) {

                if (isCycle(graph, i, vis, stack)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isCycle(
        ArrayList<Integer>[] graph,
        int curr,
        boolean[] vis,
        boolean[] stack) {

        vis[curr] = true;
        stack[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {

            int next = graph[curr].get(i);

            // Cycle found
            if (stack[next]) {
                return true;
            }

            // Visit unvisited node
            if (!vis[next]) {

                if (isCycle(graph, next, vis, stack)) {
                    return true;
                }
            }
        }

        // Remove from current DFS path
        stack[curr] = false;

        return false;
    }
}