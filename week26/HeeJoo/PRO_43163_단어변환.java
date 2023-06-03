/*
 * BEGIN -> TARGET 가장 짧은 변환 과정
 * 1. 한 번에 한 개의 알파벳만
 * 2. WORDS에 있는 단어로
 
 * 1. WORDS 안에 TARGET이 있는지
 * 2. 히스토리에 없는 글자 중 조건 만족하는 글자로 바꾸기
 */

import java.util.*;

class Solution {
    static int answer = (int)1e9;
    public int solution(String begin, String target, String[] words) {
        
        // 1. words, target 비교
        List<String> words_list = Arrays.asList(words);
        
        if(!words_list.contains(target)) {
//             System.out.println("없음 ㅇㅅ\"ㅇ");
            return 0;
        }
        
        // 2. DFS
        boolean[] history = new boolean[words.length];
        dfs(0, begin, target, words, history);
        
        return answer;
    }
    
    public void dfs(int count, String begin, String target, String[] words, boolean[] history) {
        if(count > answer) {
            return;
        }
        
        if(target.equals(begin)) {
            answer = Math.min(answer, count);
            return;
        }
        
        for(int i = 0; i < words.length; i++) {
            if(history[i]) {
                continue;
            }
            
            int length = begin.length();
            for(int j = 0; j < length; j++) {
                String left = words[i].substring(0, j);
                String right = words[i].substring(j+1, length);
                
                if(begin.charAt(j) == words[i].charAt(j)) continue;
                if(!left.equals(begin.substring(0, j)) || !right.equals(begin.substring(j+1, length))) continue;
                history[i] = true;
                dfs(count+1, words[i], target, words, history);
                history[i] = false;
            }
        }
    }
}
