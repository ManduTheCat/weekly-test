import java.util.*;

class Solution {
    public int count;
    
    public int solution(String begin, String target, String[] words) {
        
        // 1. words에 end가 있어야 한다
        boolean find = false;
        for (String word : words) {
            if (word.equals(target)) {
                find = true;
                break;
            }
        }
        
        // words에 end가 없으면 0을 리턴
        if (!find) return 0;
        
        // 2. words 안에 end가 있는 경우
        boolean[] visited = new boolean[words.length];
        count = Integer.MAX_VALUE;
        dfs(0, begin, target, words, visited);
        
        int answer = (count != Integer.MAX_VALUE) ? count : -1;
        return answer;
    }
    
    public void dfs (int depth, 
                     String current, 
                     String target, 
                     String[] words, 
                     boolean[] visited) {
        
        if (current.equals(target)) {
            count = Math.min(count, depth);
            return;
        }
        
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (compareWord(current, words[i])) {
                    visited[i] = true;
                    dfs(depth + 1, words[i], target, words, visited);
                    visited[i] = false;
                }
            }
        }
    }
    
    // 단어를 비교한다
    public boolean compareWord(String current, String target) {
        char[] curArray = current.toCharArray();
        char[] targetArray = target.toCharArray();
        
        int numOfDiff = 0;
        for (int i = 0; i < curArray.length; i++) {
            if (curArray[i] != targetArray[i]) {
                numOfDiff++;
            }
        }
        
        boolean answer = (numOfDiff == 1) ? true : false;
        return answer;
    }
}
