import java.util.*;

class Solution {
    private int answer = (int) 1e9;
    
    public int solution(int k, int n, int[][] reqs) {
        comb(k, n, 0, 0, new int[k], reqs);
        return answer;
    }
    
    private void check(int k, int[] num, int[][] reqs) {
        int[][] consult = new int[k][];
        int wait = 0;
        
        for (int i = 0; i < k; i++) {
            consult[i] = new int[num[i]];
        }
        
        for (int[] req: reqs) {
            int type = req[2] - 1;
            
            int minIdx = 0;
            // 상담원 배치
            for (int i = 0; i < num[type]; i++) {
                if (consult[type][i] < req[0]) { // 바로 상담 가능한 경우
                    consult[type][i] = req[0] + req[1];
                    break;
                }

                // 기다려야 하는 경우
                if (consult[type][i] < consult[type][minIdx]) minIdx = i;
                if (i == num[type] - 1) {
                    wait += consult[type][minIdx] - req[0]; // 기다린 시간 더하기
                    consult[type][minIdx] += req[1];
                }
            }
            
        }
        // 기다린 시간 최솟값 갱신
        answer = Math.min(answer, wait);
    }
    
    private void comb(int k, int n, int idx, int curSum, int[] num, int[][] reqs) {
        if (idx == k) {
            if (curSum == n) {
                check(k, num, reqs);
            }
            return;
        }

        for (int i = 1; i <= n - curSum; i++) {
            num[idx] = i;
            comb(k, n, idx + 1, curSum + i, num, reqs);
        }
    }
}

