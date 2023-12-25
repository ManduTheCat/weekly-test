import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = toSecond(play_time);
        int advTime = toSecond(adv_time);

        int[] arr = new int[playTime];

        for (String str : logs) {
            StringTokenizer st = new StringTokenizer(str, "-");
            String startStr = st.nextToken();
            String endStr = st.nextToken();
            int start = toSecond(startStr);
            int end = toSecond(endStr);

            for (int i = start; i < end; i++) {
                arr[i]++;
            }
        }

        long sum = 0;
        long maxSum = 0;
        int maxIdx = 0;

        for (int i = 0; i < advTime; i++) {
            sum += arr[i];
        }

        maxSum = sum;

        for (int i = advTime; i < playTime; i++) {
            sum += arr[i] - arr[i - advTime];
            if (sum > maxSum) {
                maxSum = sum;
                maxIdx = i - advTime + 1;
            }
        }

        return secToString(maxIdx);
    }

    static int toSecond(String str) {
        StringTokenizer st = new StringTokenizer(str, ":");
        int hours = Integer.parseInt(st.nextToken());
        int minutes = Integer.parseInt(st.nextToken());
        int seconds = Integer.parseInt(st.nextToken());
        return hours * 3600 + minutes * 60 + seconds;
    }

    static String secToString(int time) {
        StringBuilder sb = new StringBuilder();
        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time % 60;

        if (hours < 10) sb.append("0");
        sb.append(hours);
        sb.append(":");

        if (minutes < 10) sb.append("0");
        sb.append(minutes);
        sb.append(":");

        if (seconds < 10) sb.append("0");
        sb.append(seconds);

        return sb.toString();
    }
}
