/*
 * 비내림차순 정렬 수열 ~ 오름차순 ?
 * 조건 만족 부분 수열 찾기
 * 조건 1. 임의의 두 인덱스의 원소와 그 사이의 원소 모두 포함하는 부분 수열
 * 조건 2. 부분 수열의 합은 k
 * 조건 3. 합이 k인 부분 수열이 여러 개면 길이가 가장 짧은 수열
 * 조건 4. 가장 짧은 수열이 여러 개인 경우 앞쪽에 나오는 수열
 */

/*
 * 1. 현재 부분 수열 합 구하기 ....
 * 2. k보다 작으면 right+1, 크면 left+1
 * 3. 수열 길이, 즉 인덱스 차이가 짧으면 갱신
 * 4. 같은 경우 갱신 xxx
 */

class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0, right = 0; // 임의의 두 원소 인덱스
        int sum = sequence[left]; // 1. 현재 부분 수열의 합
        int start = 0, end = sequence.length; // 최종 답안
        
        while(left <= right) {
            if(sum == k) { // 조건 2
                if(right - left < end - start) { // 3. 수열 길이가 짧은 경우만 갱신
                    start = left;
                    end = right;
                }
                sum -= sequence[left++];
            } else if(sum < k && right < sequence.length - 1) { // 2-1. 합이 k보다 작은 경우 ... right + 1
                sum += sequence[++right];
            } else { // 2-2. 합이 k보다 큰 경우 ... left + 1
                sum -= sequence[left++];
            }
        }
        
        int[] answer = {start, end};
        return answer;
    }
}
