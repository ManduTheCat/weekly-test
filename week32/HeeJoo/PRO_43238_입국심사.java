/*
 * n명이 입국심사 대기
 * 심사관마다 심사 소요 시간 다름
 * 처음에 모든 심사대는 비어있음. 한 명씩만 심사 가능.
 * 대기 순서대로 비어 있는 심사대로 가서 심사 가능 + 더 빨리 끝나는 곳을 기다렸다가 심사 가능
 
 * n : 심사 대기 인원
 * times : 심사관별 심사 소요 시간
 
 * return 모든 사람이 심사를 받는데 걸리는 시간의 최솟값
 */

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long high = 0;
        for(int t : times) {
            high = Math.max(high, t);
        }
        
        long low = 1;
        high *= n; // 최대 소요 시간
        
        while(low < high) {
            long mid = (low + high) / 2;
            long cnt = 0;
            
            // mid 시간 동안 각 심사대에서 심사할 수 있는 인원 수 구하기
            for(int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
            }
            
            if(cnt < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        
        return low;
    }
}
