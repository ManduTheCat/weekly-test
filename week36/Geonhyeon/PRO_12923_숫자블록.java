/*
시간초과가 자꾸 나는거 보니 이분탐색? -> 아니였고
*/
class Solution {
    
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end-begin+1)];
        int idx = 0;
        
        for(long i = begin;i<=end;i++){
            answer[idx++] = find(i);
        }
        return answer;
    }
    
    
    static int find(long num){
        if(num == 1) return 0;
        int n = 2;
        int div = 1;
        while(n <= Math.sqrt(num)){
            if(num%n == 0  ) {
                if(num/n<=10000000) return (int)num/n;
                div = n;
            }
            n++;
        }
        return div;
    }
    
    
}
