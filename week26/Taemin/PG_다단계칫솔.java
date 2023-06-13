import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        // root 부터 시작
        int[] profits = new int[enroll.length + 1];
        int[] parents = new int[enroll.length + 1];
        
        for (int i = 1; i < parents.length; i++) {
            parents[i] = findIndex(enroll, referral[i - 1]);
        }
        
        for (int i = 0; i < seller.length; i++) {
            String name = seller[i];
            int profit = 100 * amount[i];
            int index = findIndex(enroll, name);
            
            // System.out.println("이익: " + profit);
            // System.out.println("인덱스: " + index);

            while (true) {

                
                // parents 찾기
                int parent = parents[index];
                // System.out.println("현재 노드: " + index + ", 부모노드: " + parent);
                
                // parents 끝에 도달한 경우
                if (parent == index) {
                    profits[index] += profit;
                    
                    // System.out.println("1");
                    // print(profits);
                    break;
                }
                
                // 수익 10%가 1원 단위 이하인 경우
                if (profit < 10) {
                    profits[index] += profit;
                    
                    // System.out.println("2");
                    // print(profits);
                    break;
                }
                
                int margin = profit / 10;
                profits[index] += (profit - margin);
                profit = margin;
                index = parent;
                
                // System.out.println("0");
                // print(profits);
            }
            
        }
        
        int[] result = Arrays.copyOfRange(profits, 1, profits.length);
        return result;
    }
    
    public int findIndex(String[] enroll, String name) {
        int result = 0;
        
        if (name.equals("-")) return result;
        
        for (int i = 0; i < enroll.length; i++) {
            if (enroll[i].equals(name)) {
                result = i + 1;
                break;
            }
        }
        
        return result;
    }
    
    public void print(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }
}
