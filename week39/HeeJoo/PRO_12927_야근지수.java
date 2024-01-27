/*
 * 피로도 : 남은 일의 작업량^2 총 합
 * N시간 동안 일해서 피로도 최소화하기
 * 1시간 당 작업량 1 처리 가능
 
 * 1 4 9 16 25 -> 원소가 클수록 제곱수 급격히 증가 ~ 원소를 균등하게 감소
 */

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        // 우선순위 큐를 이용해서 큰 숫자부터 감소
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int i : works) {
            pq.offer(i);
        }
        
        while(n-- > 0) {
            pq.offer(pq.poll() - 1);
        }
        
        while(!pq.isEmpty()) {
            if(pq.peek() < 0) {
                break;
            }
            
            int i = pq.poll();
            answer += i * i;
        }
        
        return answer;
    }
}
