import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        // 리스트에 저장
        ArrayList<Integer> results = new ArrayList<>();
        
        // 배수 카운트
        int[] dp = new int[e+1];
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                dp[j]++;
            }
        }
        
        
        // 약수의 수를 내림차순으로 정렬 (동일한 약수의 수라면 작은 수가 먼저)
        ArrayList<Integer> ranks = new ArrayList<>();
        for (int i = 1; i <= e; i++) {
            ranks.add(i);
        }
        
        Collections.sort(ranks, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                
                if (dp[o1] == dp[o2]) {
                    return Integer.compare(o1, o2);
                }
                
                return Integer.compare(dp[o2], dp[o1]);
            }
        });
        
        // 범위 안에 있는 수 중에서 가장 먼저 나온 수를 답으로 제시
        for (int start : starts) {
            for (int rank : ranks) {
                if (rank >= start) {
                    results.add(rank);
                    break;
                }
            }
        }
        
        return results.stream().mapToInt(i->i).toArray();
    }
}
