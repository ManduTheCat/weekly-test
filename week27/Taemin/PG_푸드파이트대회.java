import java.util.*;

class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        
        for (int i = 1; i < food.length; i++) {
            int numOfFood = food[i];
            int half = numOfFood / 2;
            
            for (int j = 0; j < half; j++) {
                queue.add(i);
                stack.push(i);
            }
        }
        
        int selected = 0;
        
        while (!queue.isEmpty()) {
            selected = queue.poll();
            sb.append(selected);
        }
        
        sb.append(0);
        
        while (!stack.isEmpty()) {
            selected = stack.pop();
            sb.append(selected);
        }
        
        String answer = sb.toString();
        return answer;
    }
}
