/*
양과 늑대의 수 <= 17 이므로 완탐
순서가 정해지면 안됨 -> 
부모는 방문했고 자식은 방문안한 노드를 visited에서 찾아서 반복문을 통해 방문
*/
import java.util.*;

class Solution {
    int answer = 0;
    public int solution(int[] info, int[][] edges) {
        boolean[] visited = new boolean[info.length];
        dfs(0,0,0,visited,info,edges);
        return answer;
    }
    
    void dfs(int sheep, int wolf,int node, boolean[] visited,int[] info,int[][] edges){
        visited[node] = true;
        sheep += info[node] ^ 1;
        wolf += info[node];
        answer = Math.max(answer, sheep);
        if(sheep<=wolf) return;
        
        for(int i=0;i<edges.length;i++){
            int from = edges[i][0];
            int to = edges[i][1];
            if(visited[from] && !visited[to]){
                boolean[] visited_clone = visited.clone();
                dfs(sheep,wolf,to,visited_clone,info,edges);
            }
        }
    }
}
