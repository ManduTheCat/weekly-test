import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int answer = enemy.length;
        
        for (int i = 0; i < enemy.length; i++) {
            
            if (n >= enemy[i]) {
                // 병사를 소모해서 지나간 경우 저장한다
                n -= enemy[i];
                pq.add(enemy[i]);
            }
            else {
                // 적군이 더 많은 경우 무적권이 없으면 게임 종료
                if (k <= 0) {
                    answer = i;
                    break;
                }
                
                // 이전에 병사를 소모해서 이동했던 경우와 현재 마주한 적군을 비교하기
                if (!pq.isEmpty()) {
                    if (pq.peek() > enemy[i]) {
                        n += pq.poll();
                        i--;
                    }
                }
                
                // 무적권을 사용
                k--;
            }
        }
        
        return answer;
    }
}
