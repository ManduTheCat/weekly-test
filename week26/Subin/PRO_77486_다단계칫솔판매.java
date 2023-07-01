import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        Map<String, Integer> enrollIdx = new HashMap<>();
        Map<String, String> referralMap = new HashMap<>();
        
        // 가입자 이름과 인덱스 매핑
        for (int i = 0; i < enroll.length; i++) {
            enrollIdx.put(enroll[i], i);
        }
        
        // 가입자 이름과 추천인 매핑
        for (int i = 0; i < enroll.length; i++) {
            referralMap.put(enroll[i], referral[i]);
        }
        
        // 판매 기록 돌면서 반영
        int[] result = new int[enroll.length];
        for (int i = 0; i < seller.length; i++) {
            
            int earn = amount[i] * 100;
            String next = seller[i];
            
            while (!next.equals("-") && earn > 0) {
                int idx = enrollIdx.get(next);
                result[idx] += earn - (int)(earn * 0.1);
                
                next = referralMap.get(next);
                earn *= 0.1;
                
            }
        }
        
        return result;
    }
}
