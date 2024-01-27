class Solution {
    
    static int answer;
    
    public int solution(int[] info, int[][] edges) {
        answer = 0;
        
        dfs(0, 0, 0, info, edges, new boolean[info.length]);
        return answer;
    }
    
    static void dfs(int idx, int sheepCnt, int wolfCnt, int[] info, int[][] edges, boolean[] visited) {
        
        visited[idx] = true;
        
        if(info[idx] == 0) {
            sheepCnt++;
            if(answer < sheepCnt) answer = sheepCnt;
        }else wolfCnt++;
  
        
        if(wolfCnt >= sheepCnt) {
            return;
        }
        
        for(int[] edge : edges) {
            if(visited[edge[0]] && !visited[edge[1]]) {
                boolean[] nextVisited = visited.clone();
                dfs(edge[1], sheepCnt, wolfCnt, info, edges, nextVisited);
            }
        }
        
    }
    
}
