import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

class Solution {
    public ArrayList<Integer> Array = new ArrayList<>();
    
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        for (int[] route : routes) {
            if (!isChecked(route)) {
                Array.add(route[1]);
            }
        }
        
        return Array.size();
    }
    
    public boolean isChecked(int[] route) {
        boolean result = false;
        Iterator<Integer> iter = Array.iterator();
        
        while (iter.hasNext()) {
            int camera = iter.next();
            if (route[0] <= camera && camera <= route[1]) {
                result = true;
                break;
            }
        }
        
        return result;
    }
}
