/* 
 * 모든 차량이 단속용 카메라를 한 번은 만나도록 !
 * 최소 몇 대의 카메라 설치 ?
 * routes[i][0] : i번째 차량이 고속도로에 진입한 지점
 * routes[i][1] : i번째 차량이 고속도로에서 나간 지점
 */

/*
 * 탈출 오름차순 정렬
 * 다음 진입이 이전 탈출보다 작다면 추가 설치xxx
 */

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int now = -30001;
        for(int[] route: routes) {
            if(now < route[0]) {
                answer++;
                now = route[1];
            }
        }
        
        return answer;
    }
}
