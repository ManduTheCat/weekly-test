import java.util.*;

class Solution {
    public int solution(int[] order) {        
        int answer = 0;
        Deque<Integer> conveyer = new ArrayDeque<Integer>();
        Stack<Integer> sub = new Stack<Integer>();
        
        for (int i = 1; i <= order.length; i++) {
            conveyer.add(i);
        }
        
        for (int i = 0; i < order.length; i++) {
            int box = order[i];
            
            if (!sub.isEmpty()) {
                if (sub.peek() == box) {                    
                    sub.pop();
                    answer++;
                    continue;
                }
            }
            
            if (!conveyer.isEmpty()) {
                boolean found = false;
                int size = conveyer.size();
                
                for (int j = 0; j < size; j++) {
                    int target = conveyer.poll();
                    
                    if (target == box) {
                        found = true;
                        answer++;   
                        break;
                        
                    } else {
                        sub.push(target);
                    }
                }
                
                if (!found) break;
            }
        }
                
        
        return answer;
    }
}
