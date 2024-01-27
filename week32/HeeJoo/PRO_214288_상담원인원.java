/*
 * 멘토 n명, 1 ~ k 종류 상담 유형
 * 각 멘토는 하나의 상담 유형만 담당 가능
 * 1 : 1 상담만 가능, 상담 시간은 참가자가 요청한 시간만큼 소요됨
 
 * 규칙 1) 참가자가 신청한 유형의 담당 멘토 중 현재 상담이 아닌 멘토와 상담 시작
 * 규칙 2) 담당 멘토가 모두 상담 중인 경우, 자신의 차례가 올 때까지 대기. 대기 시간 = 상담 요청 시간 ~ 상담 시작 전 시간
 * 규칙 3) 멘토는 상담이 끝나면 대기 상담 즉시 시작. 선착순
 
 * 참가자의 대기 시간 합이 최소가 되도록 각 상담 유형별 멘토 인원 정하기 ... 각 유형별 최소 인원 1명
 
 * n : 멘토의 수
 * k : 상담 유형 수
 * reqs : 상담 신청 수 / 요청 시간, 소요 시간, 상담 유형 / 요청 시간 오름차순 / 요청 시간 중복 x
 */

import java.util.*;

class Solution {
    static int answer = (int)1e9;
    public int solution(int k, int n, int[][] reqs) {
        int[] mento = new int[k + 1];
        
        perm(mento, n, 1, 0, reqs);
        
        return answer;
    }
    
    // 멘토 나누기
    public static void perm(int[] mento, int n, int index, int sum, int[][] reqs) {
        if(index == mento.length) { // 마지막 유형까지 멘토 배정
            if(sum == n) { // 모든 멘토를 배정한 경우
                simul(mento, reqs);
                return;
            }
            return;
        }
        
        // index번 유형에 i명의 멘토 배정
        for(int i = 1; i <= n - sum; i++) {  
            mento[index] = i;
            perm(mento, n, index + 1, sum + i, reqs);
        }
    }
    
    // 상담 진행
    public static void simul(int[] mento, int[][] reqs) {
        int result = 0;
        
        // 각 유형별 최소 인원 배치
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < mento.length; i++) { // 1 ~ k
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j < mento[i]; j++){
                tmp.add(0);   
            }
            list.add(tmp);
        }

        for(int i = 0; i < reqs.length; i++) {
            List<Integer> now = list.get(reqs[i][2]);
            
            if(result > answer) {
                return;
            }
            
            int index = 0;
            if(now.size() > 1) {
                for(int j = 1; j < now.size(); j++) {
                    if(now.get(index) > now.get(j)) {
                        index = j;
                    }
                }
            }            
            if(now.get(index) <= reqs[i][0]) { // 상담 시작 가능
                now.set(index, reqs[i][0] + reqs[i][1]); // 상담 종료 시간 갱신
            } else { // 상담 시작 불가능 ~ 대기
                result += now.get(index) - reqs[i][0];
                now.set(index, now.get(index) + reqs[i][1]);
            }
        }
        answer = Math.min(answer, result);
    }
}
