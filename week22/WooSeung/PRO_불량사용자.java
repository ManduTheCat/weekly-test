import java.util.*;

class Solution {

    static HashSet<String> hs = new HashSet<>();    
    
    public int solution(String[] user_id, String[] banned_id) {
        
        boolean[] visited = new boolean[user_id.length];
    
        dfs(user_id, banned_id, visited, 0);
        
        return hs.size();
    }
    
    static void dfs(String[] user_id, String[] banned_id, boolean[] visited, int ban_idx) {
        
        if(ban_idx == banned_id.length) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < visited.length; i++) {
                if(visited[i]) {
                    sb.append(user_id[i]);
                }
            }
            hs.add(sb.toString());
            return;
        }
        
        for(int i = 0; i < user_id.length; i++) {
            if(visited[i]) {
                continue;
            }
            
            boolean flag = true;
            
            if(user_id[i].length() != banned_id[ban_idx].length()) {
                continue;
            }
            
            for(int j = 0; j < banned_id[ban_idx].length(); j++) {
                
                if(banned_id[ban_idx].charAt(j) == '*') {
                    continue;
                }
                
                if(banned_id[ban_idx].charAt(j) != user_id[i].charAt(j)) {
                    flag = false;
                    break;
                }
                
            }
            
            if(flag) {
                visited[i] = true;
                dfs(user_id, banned_id, visited, ban_idx + 1);
                visited[i] = false;
            }
            
        }
        
    }
    
}
