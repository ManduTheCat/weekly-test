//  10,000,000까지의 숫자가 적힌 블록들을 이용한다.

class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin + 1)]; 
        
        for(long i  = begin; i <= end; i++) {
            int temp = func(i);
            int idx = (int)(i - begin);
            answer[idx] = temp;
        }
        
        return answer;
    }
    
    static int func(long num) {
        
        if(num == 1) return 0;
        
        int temp = 1;
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                temp = Math.max(temp, i);
                if(num / i <= 10000000) {
                    temp = Math.max(temp, (int) (num / i));
                }
            }
        }
        return temp;
    }
}
