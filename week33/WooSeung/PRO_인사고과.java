// 만약 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 그 사원은 인센티브를 받지 못합니다.
// 그렇지 않은 사원들에 대해서는 두 점수의 합이 높은 순으로 석차를 내어 석차에 따라 인센티브가 차등 지급됩니다.
// 두 점수의 합이 동일한 사원들은 동석차이며, 동석차의 수만큼 다음 석차는 건너 뜁니다.
// scores[0]은 완호의 점수입니다.

import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        
        int[] wanho = {scores[0][0], scores[0][1]};
        int wanhoSum = scores[0][0] + scores[0][1];
        
        // 근무 태도 점수를 내림차순 정렬하는데 이때 값이 같으면 동료 평가 점수를 오름차순으로 정렬
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        
        int maxPeerScore = 0;
        
        for(int[] score : scores) {
            // 현재 동료 평가 점수가 최대 동료 평가 점수보다 작다는 것은 
            // 근무 태도 점수도 낮고 동료 평가 점수도 낮은 경우이기 때문에 제외 대상
            if(score[1] < maxPeerScore) {
                // 해당 사원이 완호인 경우
                if(score[0] == wanho[0] && score[1] == wanho[1]) return -1;
                
            }else {
                // 최대 동료 평가 점수 갱신
                maxPeerScore = Math.max(maxPeerScore, score[1]);
                // 완호의 점수 합 보다 현 평가 점수가 크면 완호의 석차는 밀려남
                if(score[0] + score[1] > wanhoSum) answer++;
            }
            
        }
        
        return answer;
    }
}
