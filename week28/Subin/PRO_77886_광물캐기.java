import java.util.Arrays;

class Solution {
    private int answer = (int) 1e9;
    
    public int solution(int[] picks, String[] minerals) {
        int size = 0;
        for (int pick: picks) size += pick;
        dfs(picks, minerals, new int[size], 0);
        
        return answer;
    }
    
    private void dfs(int[] remains, String[] minerals, int[] orders, int cnt) {
        if (cnt == orders.length) {
            getCost(orders, minerals);
            return ;
        }
        
        for (int i = 0; i < 3; i++) {
            if (remains[i] > 0) {
                orders[cnt] = i;
                --remains[i];
                dfs(remains, minerals, orders, cnt + 1);
                ++remains[i];
            }
        }
    }
    
    private void getCost(int[] orders, String[] minerals) {
        int idx = 0;
        int cost = 0;
        
        for (int order: orders) {
            for (int i = 0; i < 5; i++) {
                if (order == 0) {
                    cost += 1;
                } else if (order == 1) {
                    if (minerals[idx].equals("diamond")) cost += 5;
                    else cost += 1;
                } else {
                    if (minerals[idx].equals("diamond")) cost += 25;
                    else if (minerals[idx].equals("iron")) cost += 5;
                    else cost += 1;
                }
                if (++idx >= minerals.length) break;
            }
            if (idx >= minerals.length) break;
        }
        answer = Math.min(answer, cost);
    }
}
