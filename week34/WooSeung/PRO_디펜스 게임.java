// 매 라운드마다 등장하는 적을 n명을 통해서 처리
// 남은 병사의 수보다 현재 라운드의 적의 수가 더 많으면 게임이 종료
// 무적권을 k번 사용가능하며 사용하면 병사의 소모없이 한 라운드의 공격을 막을 수 있다.

import java.util.*;

class Solution {
    static int answer;
    public int solution(int n, int k, int[] enemy) {
        answer = -1;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0; i < enemy.length; i++) {
            pq.add(enemy[i]);
            // 무적권의 수보다 pq에 값이 더 있다면 n을 이용해서 방어
            if(pq.size() > k) n -= pq.poll();
            // n이 0보다 적으면 적을 막을 수 없기 때문에 종료
            if(n < 0) return i;
        }
        // 모든 라운드를 통과한 경우 enemy길이를 반환
        return enemy.length;
    }

}
