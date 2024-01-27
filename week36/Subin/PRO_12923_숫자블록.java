class Solution {
    public int[] solution(long begin, long end) {
        int range = (int) (end - begin + 1);
        int[] answer = new int[range];
        
        for (int i = 0; i < range; i++) {
            answer[i] = getBlock(i + begin);
        }
        return answer;
    }
    
    // 1인 경우 0
    // 소수인 경우 1
    // 소수가 아닌 경우 약수 중 자신을 제외한 최댓값 (이지만.. 10000000 이하여야 함)
    private int getBlock(long n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if ((int) (n / i) <= 10_000_000) return (int) (n / i);             
            } 
        }
        
        // 10000000 이하 조건 때문에 추가된 코드
        // 제곱근까지 조건을 만족하는 타일이 없다면, 제곱근 짝꿍들 살펴야함
        for (int i = (int) Math.sqrt(n); i >= 2; i--) {
            if (n % i == 0) {
                if ((int) (n / (n / i)) <= 10_000_000) return (int) (n / (n / i)); 
            } 
        }
        
        return n == 1 ? 0 : 1;
    }
}
