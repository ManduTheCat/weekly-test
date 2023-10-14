import java.util.*;

class Solution {
    
    public int answer;
    public ArrayList<Integer>[] graph;
    
    public int solution(int[] info, int[][] edges) {
        graph = new ArrayList[info.length];
        
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            
            if (graph[parent] == null) {
                graph[parent] = new ArrayList<>();
            }
            
            graph[parent].add(child);
        }
        
        List<Integer> next = new ArrayList<>();
        next.add(0);
        dfs(info, next, 0, 0, 0);
        
        return answer;
    }
    
    public void dfs(int[] info, List<Integer> next, int node, int sheep, int wolf) {
        
        sheep += info[node] ^ 1;
        wolf += info[node];
        
        if (sheep <= wolf) return;
        answer = Math.max(answer, sheep);
        
        List<Integer> copy = new ArrayList<>();
        copy.addAll(next);
        if (graph[node] != null) {
            copy.addAll(graph[node]);
        }
        copy.remove(Integer.valueOf(node));
        
        for (int nextNode : copy) {
            dfs(info, copy, nextNode, sheep, wolf);
        }
    }
}
