class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int length = sequence.length;
        // 투 포인터로 가리키는 인덱스 사이의 값을 모두 더 한 값을 저장하는 변수
        int sum = 0;
        // 조건을 만족하는 투 포인터의 위치를 가지는 변수
        int start = 0, end = length - 1;
        
        for(int s = 0, e = 0; s < length; s++) {
            // 배열의 범위를 벗어나지 않아야한다.
            // end 포인터의 위치를 옮기면서 sum에 값을 더해준다.
            // sum에 값이 k보다 크면 종료
            while(e < length && sum < k) {
                sum += sequence[e++];
            }
            // 만약 k와 sum의 값이 같으며 기존에 길이보다 현재 길이가 작으면 값 갱신
            if(sum == k && end - start + 1 >= e - s + 1) {
                start = s;
                end = e - 1;
            }
            // start 포인터 이동
            // 이동 전 start 배열에 있는 값을 sum에서 차감
            sum -= sequence[s];
            
        }           
        int[] answer = {start, end};
        return answer;
    }
    
}
