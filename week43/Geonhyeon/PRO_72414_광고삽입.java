
/*
시각 -> 초로변환

초-> 시각으로 변환하는 함수(String)
각 영상logs의 '시작시간'에서 구간측정 -> 30만회의 연산으로 가능
*/
import java.util.*;
class Solution {
    long max = Long.MIN_VALUE;
    int[] viewLog = new int[360001];
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int start;
        int end;
        
        //시청자수 누적합
        for(int i=0;i<logs.length;i++){
            String log = logs[i];
            String[] time = log.split("-");
            start = parseToSec(time[0]);
            end = parseToSec(time[1]);
            
            //시작지점과 끝나는지점에 +1(나중에 한번에 계산)
            viewLog[start]++;
            viewLog[end]--;
        }
        int playTime = parseToSec(play_time);
        int adv = parseToSec(adv_time);
        for(int i=1;i<=playTime;i++){
            viewLog[i] += viewLog[i-1];
        }
        
        //구간측정
        long cur = 0;
        for(int i=0;i<adv;i++){
            cur += viewLog[i];
        }
        int startTime=0; //최댓값일때의 시각
        long max = cur;
        for(int i=adv;i<=playTime;i++){
            cur += viewLog[i];
            cur -= viewLog[i-adv];
            if(cur>max){
                startTime = i-adv+1;
                max = cur;
            }
        }
        return parseToTime(startTime);
    }
    
    int parseToSec(String log){
        String[] time = log.split(":");
        return Integer.parseInt(time[0])*3600 
            + Integer.parseInt(time[1])*60 
            + Integer.parseInt(time[2]);
    }
    
    String parseToTime(int time){
        StringBuilder sb = new StringBuilder();
        int hour = time/3600;
        if(hour<10) sb.append("0");
        sb.append(hour);
        sb.append(":");
        time-=hour*3600;
        int min = time/60;
        if(min<10) sb.append("0");
        sb.append(min);
        sb.append(":");
        time -= min*60;
        if(time<10) sb.append("0");
        sb.append(time);
        return sb.toString();
    }
}
