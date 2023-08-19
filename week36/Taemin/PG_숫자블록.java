import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin + 1)];
        long number = begin;
        int pointer = 0;
        
        while (number <= end) {    
            
            // 소수 판독 (소수 인 경우 -1 반환, 1인 경우 0을 반환, 소수가 아닌 경우 가장 큰 몫을 반환)
            int check = isPrime(number);
            
            // 소수인 경우 1을 저장
            if (check == -1) {
                answer[pointer++] = 1;
                number++;
                continue;
            }
            // 소수가 아닌 경우 나눌 수 있는 가장 큰 수 저장
            else {
                answer[pointer++] = check;
                number++;
                continue;
            }
        }
        
        return answer;
    }
    
    // 소수 판독
    public int isPrime(long number) {
        // 1인 경우 0을 반환
        if (number == 1) return 0;
        
        int result = -1;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                result = Math.max(result, i);

                int share = (int)(number / i);
                
                if (share <= 10_000_000) {
                    result = Math.max(result, share);                    
                }
            }
        }
        
        return result;
    }
}
