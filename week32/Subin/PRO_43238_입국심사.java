import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        return binarySearch(n, times);
    }
    
    private long binarySearch(int n, int[] times) {
        long l = 0;
        long r = (long) n * times[times.length - 1];
        long res = 0;
        
        while (l <= r) {
            long mid = (l + r) / 2;
            if (check(n, times, mid)) {
                r = mid - 1;
                res = mid;
            } else {
                l = mid + 1;
            }
        }
        
        return res;
    }
    
    private boolean check(int n, int[] times, long ans) {
        long cnt = 0;
        for (int time: times) {
            cnt += ans / time;
        }
        return cnt >= n;
    }
}
