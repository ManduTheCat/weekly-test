class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int pSec = timeToSec(play_time);
        int aSec = timeToSec(adv_time);
        
        // 영상의 초당 시청자 수 계산
        int[] times = new int[pSec + 1];
        for (String log : logs) {
            String[] split = log.split("-");
            int start = timeToSec(split[0]);
            int end = timeToSec(split[1]);

            ++times[start];
            --times[end];
        }
        
        for (int i = 1; i <= pSec; i++) {
            times[i] = times[i - 1] + times[i];
        }
        
        // 누적합
        long[] dp = new long[pSec + 1];
        dp[0] = times[0];
        for (int i = 1; i <= pSec; i++) {
            dp[i] = dp[i - 1] + times[i];
        }
        
        // 구간의 최대 합 구하기
        int maxIdx = 0;
        long maxToalTime = dp[aSec - 1];
        for (int i = 1; i <= pSec - aSec; i++) {
            long totalTime = dp[i + aSec - 1] - dp[i - 1];
             if (totalTime > maxToalTime) {
                maxToalTime = totalTime;
                maxIdx = i;
            }
        }
        
        // 초를 문자열로 변환
        String answer = secToTime(maxIdx);
        return answer;
    }
    
    public int timeToSec(String timeStr) {
        String[] split = timeStr.split(":");
        int sec = 0;
        int base = 3600;
        
        for (int i = 0; i < 3; i++) {
            sec += Integer.parseInt(split[i]) * base;
            base /= 60;
        }
        return sec;
    }
    
    public String secToTime(int sec) {
        int h = sec / 3600;
        int m = sec % 3600 / 60;
        int s = sec % 3600 % 60;
        
        String time = "";
        if (h < 10) time += "0";
        time += String.valueOf(h);
        time += ":";
        
        if (m < 10) time += "0";
        time += String.valueOf(m);
        time += ":";
        
        if (s < 10) time += "0";
        time += String.valueOf(s);
        
        return time;
    }
}
