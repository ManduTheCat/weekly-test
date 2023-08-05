import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> skill = new PriorityQueue();
        int answer = 0;
        
        for (int e: enemy) {
            skill.add(e);
            // skill의 크기가 k보다 크다면, 무적권을 쓸 수 있는 횟수를 초과한 것이다.
            // skill 목록에서 가장 병사 소모가 작은 경우를 빼서, n에서 빼야 한다.
            // n이 0보다 작다면 게임이 종료된다.
            if (skill.size() > k) {
                int poll = skill.poll();
                if ((n -= poll) < 0) break;
            }
            ++answer;
        }
        
        return answer;
    }
}
