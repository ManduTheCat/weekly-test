// https://school.programmers.co.kr/questions/33344
class Solution {
    
    private final String vowels = "AEIOU";
    
    public int solution(String word) {
        // 가중치 구하기 (w[0] = 5^0, w[1] = 5^0 + 5^1, ...)
        int[] w = new int[5];
        w[0] = 1;
        for (int i = 1; i < 5; i++) {
            w[i] = w[i - 1] + (int) Math.pow(5, i);
        }
        
        int answer = 0;
        for (int i = 0; i < word.length(); i++) {
            answer += 1 + vowels.indexOf(word.charAt(i)) * w[4 - i];
        }
        
        return answer;
    }
}
