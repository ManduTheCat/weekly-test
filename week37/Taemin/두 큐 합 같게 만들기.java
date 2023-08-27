import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        // 총 합산 구하기
        long total1 = 0L;
        long total2 = 0L;
        for (int elem : queue1) { total1 += elem; }
        for (int elem : queue2) { total2 += elem; }
        long total = total1 + total2;
        
        // 포인터
        int pointer1 = 0;
        int pointer2 = 0;
        
        // 대기큐
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        // 계산
        int answer = 0;
        while (true) {
            if (pointer1 == queue1.length && q1.size() == queue2.length || 
                pointer2 == queue2.length && q2.size() == queue1.length) {
                answer = -1;
                break;
            }
            
            if (total1 == (total / 2)) break;
            answer++;
            
            // 첫 번째 큐의 합이 목표보다 적은 경우 두 번째 큐에서 원소 넘겨받기
            if (total1 < (total / 2)) {
                if (pointer2 < queue2.length) {
                    int elem = queue2[pointer2++];
                    total1 += elem;
                    q1.add(elem);
                }
                else {
                    int elem = q2.poll();
                    total1 += elem;
                    q1.add(elem);
                }
            }
            // 첫 번째 큐의 합이 목표보다 큰 경우 두 번째 큐로 원소 넘기기
            else {
                if (pointer1 < queue1.length) {
                    int elem = queue1[pointer1++];
                    total1 -= elem;
                    q2.add(elem);
                }
                else {
                    int elem = q1.poll();
                    total1 -= elem;
                    q2.add(elem);
                }
            }
        }

        return answer;
    }
}
