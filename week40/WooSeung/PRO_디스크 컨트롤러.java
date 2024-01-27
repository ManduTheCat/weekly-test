import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int idx = 0;
        int cnt = 0;
        int sum = 0;
        int time = 0;
        
        while(cnt < jobs.length) {
            while(idx < jobs.length && jobs[idx][0] <= time) {
                pq.add(jobs[idx++]);
            }
            if(pq.isEmpty()) {
                time = jobs[idx][0];
            }else {
                int[] cur = pq.poll();
                sum += cur[1] + time - cur[0];
                time += cur[1];
                cnt++;
            }
            
        }
        
        
        return sum / jobs.length;
    }
}
