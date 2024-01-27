import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> primary = new Stack<>();
        Stack<Integer> second = new Stack<>();
        
        int box = 1;
        for (int o: order) {
            
            if (!primary.empty() && primary.peek() == o) {
                primary.pop();
                answer++;
                continue;
            } else if (!second.empty() && second.peek() == o) {
                second.pop();
                answer++;
                continue;
            } 
            
            while (box < o) {
                second.push(box++);
            }
            primary.push(box++);
            
            if (!primary.empty() && primary.peek() == o) {
                primary.pop();
                answer++;
            } else if (!second.empty() && second.peek() == o) {
                second.pop();
                answer++;
            } else {
                break;
            }
        }
        
        return answer;
    }
}
