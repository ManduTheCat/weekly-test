class Solution {
    public int[] solution(int[] sequence, int k) {
        
        int resultStart = 0;
        int resultEnd = 0;
        
        int pointStart = 0;
        int pointEnd = 0;
        
        int len = sequence.length;
        long total = sequence[0];
        boolean flag = true;
        
        while (true) {
            
            if (total == k) {

                // 첫 입력 결과에 대해서 처리
                if (flag) {
                    resultStart = pointStart;
                    resultEnd = pointEnd;
                    flag = false;
                }
                else {
                    // 비교
                    // (1) 길이
                    if (pointEnd - pointStart < resultEnd - resultStart) {
                        resultStart = pointStart;
                        resultEnd = pointEnd;
                    }
                    // (2) 시작 인덱스
                    else if (pointEnd - pointStart == resultEnd - resultStart) {
                        if (pointStart < resultStart) {
                            resultStart = pointStart;
                            resultEnd = pointEnd;
                        }
                    }
                }
                
                if (pointEnd + 1 >= len) break;
                total += sequence[++pointEnd];
                
            }
            else if (total < k) {
                // 합계가 목표치보다 작은 경우
                if (pointEnd + 1 >= len) break;
                
                total += sequence[++pointEnd];

            }
            else {
                // 합계가 목표치보다 큰 경우
                if (pointStart + 1 >= len || pointStart + 1 > pointEnd) break;
                
                total -= sequence[pointStart++];

            }
        }
        
        
        int[] answer = new int[]{resultStart, resultEnd};
        return answer;
    }
}
