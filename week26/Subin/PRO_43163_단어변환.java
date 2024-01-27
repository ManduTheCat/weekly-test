import java.util.*;

class Solution {
    
    private int answer;
    
    public int solution(String begin, String target, String[] words) {
        dfs(begin, target, words, new boolean[words.length], 0);
        return answer;
    }
    
    private void dfs(String word, String target, String[] words, boolean[] visited, int depth) {
        
        if (word.equals(target)) {
            if (answer == 0) answer = depth;
            else answer = Math.min(answer, depth);
            
            return;
        }
        
        
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && isChangeable(word, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
    
    private boolean isChangeable(String word, String target) {
        int diffCnt = 0;
        
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) ++diffCnt;
        }
        
        return diffCnt == 1;
    }
    
}
