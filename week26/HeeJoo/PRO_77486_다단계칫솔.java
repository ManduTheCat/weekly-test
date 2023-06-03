/*
 * 분배규칙 1. 칫솔 판매 이익 10% -> 추천인
 * 분배규칙 2. 피라미드 이익 10% -> 추천인
 * 원 단위 절사, 1원 미만 분배 x
 
 * enroll : 각 판매원 이름이 담긴 배열
 * referral : 각 판매원의 추천인 배열
 * "-" : 추천인x. 피라미드 최상위
 
 * seller : 판매량 집계 데이터의 판매원 이름 배열
 * amount : 판매량 집계 데이터의 판매 수량 배열

 * 칫솔 개당 이익금은 100원으로 고정
 * return 각 판매원의 이익금
 */

/*
 * 1. 트리 만들기
 * 2. seller 돌기
 */

import java.util.*;

class Solution {
    static int[] answer;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        answer = new int[enroll.length];
        
        // 1. enroll -> index 설정
        HashMap<String, Integer> hm = new HashMap<>();
        for(int i = 0; i < enroll.length; i++) {
            hm.put(enroll[i], i);
        }
        
        // 2. referral -> parent 설정
        int[] parent = new int[enroll.length];
        for(int i = 0; i < referral.length; i++) {
            if("-".equals(referral[i])) {
                parent[i] = -1;
                continue;
            }
            parent[i] = hm.get(referral[i]);
        }
        
        // 3. 칫솔 판매
        for(int i = 0; i < seller.length; i++) {
            int index = hm.get(seller[i]);
            int benefit = amount[i] * 100;
            
            compute(index, benefit, parent);
        }
        
        return answer;
    }
    
    public void compute(int index, int benefit, int[] parent) {
        if(index == -1) return;
        
        // 1. 본인 이익
        if(benefit < 10) {
            answer[index] += benefit;
            return;
        } else {
            // 원 단위 절사이므로 부모에게 상납하고 남은 이익만 챙김
            answer[index] += benefit - (benefit / 10);
            // 2. 부모 이익
            compute(parent[index], benefit / 10, parent);
        }
    }
}
