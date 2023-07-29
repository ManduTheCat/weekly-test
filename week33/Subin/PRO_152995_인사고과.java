import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = {scores[0][0], scores[0][1]};
        
        // 두 점수가 모두 낮은 경우 제외
        Arrays.sort(scores, (i1, i2) -> i2[0] - i1[0] == 0 ? i1[1] - i2[1] : i2[0] - i1[0]);
        int max = scores[0][1];
        for (int i = 1; i < scores.length; i++) {
            if (max <= scores[i][1]) {
                max = scores[i][1];
                continue;
            }
            
            if (scores[i][0] == wanho[0] && scores[i][1] == wanho[1]) return -1;
            scores[i][0] = 0;
            scores[i][1] = 0;
        }
        
        // 점수의 합 순으로 내림차순 정렬
        Arrays.sort(scores, (i1, i2) -> (i2[0] + i2[1]) - (i1[0] + i1[1]));
        int answer = 0;
        for (int[] score: scores) {
            ++answer;
            if (score[0] + score[1] == wanho[0] + wanho[1]) break;
        }
        
        return answer;
    }
}
