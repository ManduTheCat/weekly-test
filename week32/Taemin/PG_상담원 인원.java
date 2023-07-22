import java.util.*;

class Solution {
    
    public int answer;
    
    public int solution(int k, int n, int[][] reqs) {
        
        // 답 초기화
        answer = Integer.MAX_VALUE;
        
        // 멘토 배치 테이블
        int[] table = new int[k];
        Arrays.fill(table, 1);
        
        // 멘토 배치 수열
        comb(k, n, 0, 0, new int[k], reqs);
        
        return answer;
    }
    
    public void cal(int k, int n, int[] nums, int[][] reqs) {
        
        int wait = 0;
        
        int[][] consult = new int[k][];
        for (int i = 0; i < k; i++) {
            consult[i] = new int[nums[i]];
        }
        
        for (int[] req : reqs) {
            int type = req[2] - 1;
            
            int minIdx = 0;
            for (int i = 0; i < nums[type]; i++) {
                
                if (consult[type][i] <= req[0]) {
                    consult[type][i] = req[0] + req[1];
                    break;
                }
                
                if (consult[type][i] < consult[type][minIdx]) minIdx = i;
                if (i == nums[type] - 1) {
                    wait += consult[type][minIdx] - req[0];
                    consult[type][minIdx] += req[1];
                }
            }
        }
        
        answer = Math.min(answer, wait);
    }
    
    public void comb(int k, int n, int idx, int curSum, int[] nums, int[][] reqs) {
        if (idx == k) {
            if (curSum == n) {
                cal(k, n, nums, reqs);
            }
            
            return;
        }
        
        
        for (int i = 1; i <= n - curSum; i++) {
            nums[idx] = i;
            comb(k, n, idx + 1, curSum + i, nums, reqs);
        }
    }
}
