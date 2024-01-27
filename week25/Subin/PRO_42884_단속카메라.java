import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                int comp = Integer.compare(i1[1], i2[1]);
                if (comp == 0) return Integer.compare(i1[0], i2[0]);
                return comp;
            }
        });
        
        int answer = 0;
        int prev = -30001;
        for (int[] route: routes) {
            if (prev < route[0]) {
                ++answer;
                prev = route[1];
            }
        }
        
        return answer;
    }
}
