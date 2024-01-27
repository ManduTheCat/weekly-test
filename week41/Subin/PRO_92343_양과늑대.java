class Solution {
    private int answer;
    
    public int solution(int[] info, int[][] edges) {
        boolean[] visited = new boolean[info.length];
        visited[0] = true;
        dfs(info, edges, visited, 1, 0);
        
        return answer;
    }
    
    private void dfs(int[] info, int[][] edges, boolean[] visited, int s, int w) {
        if (s <= w) return;
        answer = Math.max(answer, s);
        
        for (int[] edge: edges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                visited[edge[1]] = true;
                if (info[edge[1]] == 0) dfs(info, edges, visited, s + 1, w);
                else dfs(info, edges, visited, s, w + 1);
                visited[edge[1]] = false;
            }
        }
    }
}
