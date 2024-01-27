/*
평균보다 큰 수를 평균아래가 되게?
매번 우선순위가 바뀐다 -> 우선순위큐
*/
import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        for(Integer work:works) pq.offer(work);
        while(n>0 && !pq.isEmpty()){
            int work = pq.poll();
            if(work==0) continue;
            pq.offer(work-1);
            n--;
        }
        while(!pq.isEmpty()){
            int work = pq.poll();
            answer += (long)(work*work);
        }
        return answer;
    }
}
