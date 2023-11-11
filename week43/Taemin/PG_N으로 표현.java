import java.util.*;

class Solution {
    /*
        1. DP의 새로운 방식 
            - i, i-1, i-2의 연쇄
            - gap
            - i번째 연산 후 i+1로의 확장의 연속
            
        2. Set 사용 방법
            - void 함수를 이용하여 reference 타입 자료 구조 구성값을 수정
            - index를 사용하지 않으므로 for value 혹은 iterator 활용 필요
            
        3. 문자열 반복 함수를 이용한 숫자 변형
            - String.repeat(i) 와 Integer.parseInt(String) 을 활용
    */
    public int solution(int N, int number) {
        
        if (N == number) return 1;
        
        List<Set<Integer>> setList = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            setList.add(new HashSet<>());   
        }
        
        setList.get(1).add(N);
        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j < i; j++) {
                unionSet(setList.get(i), setList.get(i-j), setList.get(j));
            }
            
            String strN = Integer.toString(N);
            setList.get(i).add(Integer.parseInt(strN.repeat(i)));
            
            for (int check : setList.get(i)) {
                if (check == number) return i;
            }
        }
        
        return -1;
    }
    
    private void unionSet(Set<Integer> union, Set<Integer> a, Set<Integer> b) {
        for (int i1 : a) {
            for (int i2 : b) {
                union.add(i1 + i2);
                union.add(i1 - i2);
                union.add(i1 * i2);
                if (i2 != 0) union.add(i1 / i2);
            }
        }
    }
}
