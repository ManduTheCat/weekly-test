/*
 * 최적의 공익광고 위치 선정하기
 * 공익광고 재생시간 고정 : adv_time
 * 시청자들의 누적 재생시간이 가장 큰 부분
 * return 공익광고가 들어갈 가장 빠른 시작 시각
 
 * 슬라이딩 윈도우 or dp
 */

import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int endTime = timeToInteger(play_time); // 전체 재생 시간
        
        int[] table = new int[endTime + 1];
        
        for(String log : logs) {
            String[] times = log.split("-");
            
            int start = timeToInteger(times[0]);
            int end = timeToInteger(times[1]);
            
            table[start]++;
            table[end]--;
        }
        
        for(int i = 1; i <= endTime; i++) {
            table[i] = table[i - 1] + table[i];
        }

//         // 1. DP 풀이
//         long[] dp = new long[endTime + 1];
//         dp[0] = table[0];
//         for(int i = 1; i <= endTime; i++) {
//             dp[i] = dp[i - 1] + table[i];
//         }
        
//         int end = timeToInteger(adv_time);
        
//         long max = dp[end - 1]; // 종료 시점은 포함 xxx
//         int ans = 0;

//         long gap = max;
//         for(int i = 1; i <= endTime - end; i++) {
//             gap = dp[end + i - 1] - dp[i - 1]; // i 시간에 시작하는 누적 재생 시간
            
//             if(max < gap) {
//                 max = gap;
//                 ans = i;
//             }
//         }
        
        // 2. 슬라이딩 윈도우
        long max = 0;
        for(int i = 0; i < endTime; i++) {
            max += table[i];
        }
        
        int end = timeToInteger(adv_time);
        int ans = 0;

        long gap = max;
        for(int i = 1; i <= endTime - end; i++) {
            gap = gap - table[i - 1] + table[end + i - 1];
            
            if(max < gap) {
                max = gap;
                ans = i;
            }
        }

        return integerToTime(ans);
    }

    // String time = "HH:MM:SS"
    public int timeToInteger(String str) {
        int time = 0;
        
        String[] arr = str.split(":");
        
        time += 3600 * Integer.parseInt(arr[0]);
        time += 60 * Integer.parseInt(arr[1]);
        time += Integer.parseInt(arr[2]);
        
        return time;
    }
    
    public String integerToTime(int time) {
        String str = "";
        
        str += String.format("%02d", time / 3600) + ":";
        time %= 3600;
        
        str += String.format("%02d", time / 60) + ":";
        time %= 60;
        
        str += String.format("%02d", time);
        
        return str;
    }
}
