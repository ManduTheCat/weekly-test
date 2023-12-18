class Solution {
    public int solution(String word) {
        int answer = 0;
        int[] accum = { 780, 155, 30, 5 };
        
        for (int i = 0; i < word.length(); i++) {
            int number = 0;
            switch(word.charAt(i)){
                case 'A':
                    number = 1;
                    break;
                case 'E':
                    number = 2;
                    break;
                case 'I':
                    number = 3;
                    break;
                case 'O':
                    number = 4;
                    break;
                case 'U':
                    number = 5;
                    break;
                default:
                    break;
            }
            
            if (i == 4) {
                answer += number;
            } else {
                answer += accum[i] * (number - 1) + number;
            }   
        }
        
        return answer;
    }
}
