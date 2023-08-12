/*
 * 일렬로 나열된 N개의 풍선   
 * 모든 풍선에는 서로 다른 숫자
 * 특정 과정을 반복하면서 풍선이 단 1개가 남을 때까지 터트리기
 * 1. 임의의 인접한 두 풍선을 고른 뒤, 두 풍선 중 하나 터트리기
 * 2. 터진 풍선으로 인한 빈 공간이 생겼다면, 풍선 밀착
 * 조건 : 인접한 두 풍선 중 "번호가 더 작은 풍선을 터트리는 행위"는 최대 1번만 가능
 * 최후까지 남는 것이 가능한 풍선들의 개수 구하기
 
 * 첫 번째와 마지막 풍선은 항상 남을 수 있음 : 2
 * 대 X 대 : 양쪽 다 큰 경우, X가 남을 수 있음
 * 대 X 소 : 한쪽만 큰 경우, X가 남을 수 있음
 * 소 X 소 : 양쪽 다 작은 경우, X가 남을 수 없음
 */

class Solution {
    public int solution(int[] a) {
        int answer = 2;
        
        int[] left = new int[a.length]; // 좌 -> 우 방향 최솟값
        int[] right = new int[a.length]; // 좌 <- 우 방향 최솟값
        
        // left 최솟값 갱신
        int minValue = (int)1e9;
        for(int i = 0; i < a.length; i++) {
            if(minValue > a[i]) {
                minValue = a[i];
            }
            
            left[i] = minValue;
        }
        
        // right 최솟값 갱신
        minValue = (int)1e9;
        for(int i = a.length - 1; i >= 0; i--) {
            if(minValue > a[i]) {
                minValue = a[i];
            }
            
            right[i] = minValue;
        }
            
        for(int i = 1; i < a.length - 1; i++) {
            if(left[i] < a[i] && right[i] < a[i]) {
                continue;
            }
            
            answer++;
        }
        return answer;
    }
}
