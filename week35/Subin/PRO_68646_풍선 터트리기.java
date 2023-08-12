class Solution {
    public int solution(int[] a) {
        // 가장 작은 숫자 풍선의 인덱스 탐색
        int minIdx = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[minIdx]) minIdx = i;
        }
        
        // 왼쪽에서 가장 작은 풍선까지 오름차순으로 풍선 구하기
        // 오름차순으로 존재하는 풍선은 왼쪽 풍선은 자신이 처리 가능하고, 오른쪽 풍선은 minIdx가 처리 가능하기 때문에 살아남을 수 있음
        int answer = 1;
        int min = a[0];
        for (int i = 0; i < minIdx; i++) {
            if (min >= a[i]) {
                min = a[i];
                ++answer;
            }
        }
        
        // 오른쪽에서 가장 작은 풍선까지 내림차순으로 풍선 구하기
        // 내림차순으로 존재하는 풍선은 오른쪽 풍선은 자신이 처리 가능하고, 왼쪽 풍선은 minIdx가 처리 가능하기 때문에 살아남을 수 있음
        min = a[a.length - 1];
        for (int i = a.length - 1; i > minIdx; i--) {
            if (min >= a[i]) {
                min = a[i];
                ++answer;
            }
            
        }
        
        return answer;
    }
}
