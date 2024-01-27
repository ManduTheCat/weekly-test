import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        
        // 가장 시간이 긴 값을 찾기 위한 정렬
        Arrays.sort(times);
        
        // 짧은 시간
        long left = 0;
        
        // 긴 시간
        long right = (long) times[times.length - 1] * n;
        
        // N명을 충족하는 시간 중 가장 짧은 값을 찾기
        // left와 right가 같아지면 종료
        // right는 N명 이상을 심사하는 값을 보장
        while (left < right) {
            
            // 심사 시간 선택
            long mid = (left + right) / 2;
            
            // 이분 탐색으로 선정된 시간을 기준으로 몇 명을 심사할 수 있는지 확인
            long total = 0;
            for (int i = 0; i < times.length; i++) {
                total += (mid / times[i]);
            }
            
            // 목표한 N명이거나 그 이상인 경우 Right를 해당 값인 MID로 이동
            if (total >= n) {
                right = mid;
                
            // 목표한 N명이 충족되지 않은 경우 MID보다 1큰 값으로 이동
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
}
