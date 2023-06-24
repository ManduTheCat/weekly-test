import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int idx = 0;
        Stack<Integer> sub = new Stack<>();
        for(int i=1;i<=order.length;i++){
            if(order[idx] > i){
                sub.push(i);
                continue;
            }
            if(order[idx] == i){
                answer++;
                idx++;
            }
            while(!sub.isEmpty() && order[idx] == sub.peek()){
                sub.pop();
                answer++;
                idx++;
            }
        }
        return answer;
    }
}
