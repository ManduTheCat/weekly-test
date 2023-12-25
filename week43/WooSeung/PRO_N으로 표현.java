
import java.util.*; 

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        
        Set<Integer>[] set = new HashSet[9];
        int num = 0;

        for(int i = 1; i < 9; i++) {
            num = (num * 10) + N;
            set[i] = new HashSet<>();
            set[i].add(num);
        }
        for(int i = 1; i < 9; i++) {
            for(int j = 1; j < i; j++) {
                for(Integer a : set[j]) {
                    for(Integer b : set[i - j]) {
                        set[i].add(a + b);
                        set[i].add(a - b);
                        set[i].add(a * b);

                        if(b != 0) {
                            set[i].add(a / b);
                        }  
                        if(a != 0) {
                            set[i].add(b / a);
                        }
                    }
                }    
            }
            if(set[i].contains(number)){
                    return i;
            }
        }
    
        return answer;
    }
}
