class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
          // 1. Create graph
        ArrayList<Integer>[] graph = new ArrayList[n];

        // 2. Initialize graph
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }

        // 3. Add edges
        for(int[] edge : edges){

            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        // 4. BFS
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.add(source);

        while(!q.isEmpty()){

            int curr = q.remove();

            if(visited[curr]){
                continue;
            }

            visited[curr] = true;

            // Destination found
            if(curr == destination){
                return true;
            }

            // Visit neighbors
            for(int neighbor : graph[curr]){

                if(!visited[neighbor]){
                    q.add(neighbor);
                }
            }
        }

        return false;
    }
}