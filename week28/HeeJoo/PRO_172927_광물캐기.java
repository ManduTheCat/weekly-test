/*
 * 다이아몬드, 철,돌 전용 곡괭이를 각가 0 ~ 5개 소유
 * 광물을 캘 때 피로도 소모. 각 곡괭이로 광물을 캘 때 소모되는 피로도 고정
 * 곡괭이 당 5회 사용 가능
 * 다음 규칙을 지키면서 최소 피로도로 광물 캐기
 * 규칙 1. 사용할 수 있는 곡괭이 중 하나 선택해서 광물 캐기
 * 규칙 2. 한 번 사용하기 시작한 곡괭이는 사용횟수 모두 써야 됨
 * 규칙 3. 광물은 주어진 순서대로만 캘 수 있음
 * 규칙 4. 모든 광물을 캐거나, 곡괭이가 다 없어질 때까지 광물 캐기
 */

import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        
        int[][] fatigue = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        
        int n = minerals.length / 5 + (minerals.length % 5 == 0 ? 0 : 1);
        int sum = 0;
        for(int k : picks) {
            sum += k;
        }
        
        n = Math.min(n, sum);
        int[] seq = new int[n];
        perm(0, n, seq, picks, minerals, fatigue);

        

        return answer;
    }
    
    public void perm(int idx, int n, int[] seq, int[] picks, String[] minerals, int[][] fatigue) {
        if(idx == n) {
            answer = Math.min(answer, mine(seq, minerals, fatigue));
            return;
        }
        
        for(int i = 0; i < 3; i++) {
            if(picks[i] == 0) {
                continue;
            }
            
            seq[idx] = i;
            picks[i]--;
            
            perm(idx + 1, n, seq, picks, minerals, fatigue);
            seq[idx] = -1;
            picks[i]++;
        }
    }
    
    public int mine(int[] seq, String[] minerals, int[][] fatigue) {
        int result = 0;
        
        int maxLength = seq.length * 5 >= minerals.length ? minerals.length : seq.length * 5;
        int idx = 0;
        int pick = 0;
        while(idx < maxLength) {
            pick = seq[idx / 5];
            result += fatigue[pick][getIdx(minerals[idx++])];
        }
        
        return result;
    }
    
    public int getIdx(String str) {
        if("diamond".equals(str)) return 0;
        if("iron".equals(str)) return 1;
        if("stone".equals(str)) return 2;
        
        return -1;
    }
}
