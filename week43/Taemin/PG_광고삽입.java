import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int playTime = strToInt(play_time);
        int advTime = strToInt(adv_time);
        int[] table = new int[playTime];
        
        for (String log : logs) {
            int start = strToInt(log.split("-")[0]);
            int end = strToInt(log.split("-")[1]);

            for (int i = start; i < end; i++) {
                /* 시작 지점 기준으로 경과 처리 (inclusive, exclusive) */
                table[i]++;
            }
        }
        
        int maxIdx = 0;
        long totalTime = 0L;
        for (int i = 0; i < advTime; i++) {
            totalTime += table[i];
        }
        long maxTotalTime = totalTime;
        
        for (int i = advTime; i < playTime; i++) {
            /* 슬라이딩 윈도우 처리 */
            totalTime += (table[i] - table[i-advTime]);
            
            if (totalTime > maxTotalTime) {
                maxTotalTime = totalTime;
                maxIdx = i - advTime + 1;
            }
        }
  
        return intToStr(maxIdx);
    }
    
    private int strToInt(String time) {        
        int hour = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);
        int sec = Integer.parseInt(time.split(":")[2]);
        
        return hour * 3600 + min * 60 + sec;
    }
    
    private String intToStr(int time) {
        StringBuilder builder = new StringBuilder();
        int hour = time / 3600;
        time %= 3600;
        int min = time / 60;
        time %= 60;
        
        if (hour < 10) builder.append("0");
        builder.append(hour).append(":");
    
        if (min < 10) builder.append("0");
        builder.append(min).append(":");
        
        if (time < 10) builder.append("0");
        builder.append(time);
        
        return builder.toString();
    }
}
