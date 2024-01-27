/*
 * n개의 집에 택배 배달 ㅇㅅㅇ
 * i번째 집은 창고에서 거리 i만큼 떨어져있음
 * i와 j번째 집은 j-1만큼 떨어져있음
 * 택배 상자 최대 수용 개수 : cap
 * 물류창고(출발) -> 집(배달, 수거) -> 물류창고(도착)
 * 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리 구하기
 * 각 집에 배달 및 수거할 때, 원하는 개수만큼 배달 및 수거 가능
 
 * 먼 곳부터 택배 배달, 돌아갈 때 수거
 * 그리디 알고리즘 : 한 타임에 최대 이동 거리 구하기
 * 출발할 때 cap만큼 챙김(남는건 안챙겼다고 생각하면 됨. 하지만 부족하게 챙기면 다시 물류창고로 와야되니 거리 손해) ~ deliveries[i]보다 많이 챙겨가도 남은건 앞집에 주면 됨 ... 즉 트럭에 담긴 물류 개수의 변화는 중요하지 않음
 * 수거도 마찬가지 ... 여유되는만큼 돌아갈 때 수거하면 됨
 */

import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ans = 0;
        
        int delCap = 0, pickCap = 0;
        for(int idx = n - 1; idx >= 0; idx--) { // idx번째 집 방문
        
            delCap += deliveries[idx]; // idx번째 택배 배달
            pickCap += pickups[idx]; // idx번째 택배 수거
            
            while(delCap > 0 || pickCap > 0) {
                // delCap > 0 : idx번째 집에 배달해야 될 택배가 남아있는 상태
                // pickCap > 0 : idx번째 집에 수거해야 될 택배가 남아있는 상태
                // 둘 중 하나라도 양수이면 idx번째 집에 방문해야 하므로 while()
                // 음수인 경우는 해당 타임에 여유가 있다는 뜻으로, 앞집에서 택배 배달/수거하는 것으로 대체하면 됨
                delCap -= cap; 
                pickCap -= cap;
                
                ans += (idx + 1) * 2; // idx번째 집까지 이동거리 = idx + 1 ~ 왕복이니까 * 2
            }
        }
        
        return ans;
    }

}
