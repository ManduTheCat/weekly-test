class Solution {
    
    public int solution(int[] a) {
        
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        int answer = 0;
        
        // 왼쪽 확인
        int left = a[0];
        for (int i = 0; i < a.length; i++) {
            if (left > a[i]) left = a[i];
            leftMin[i] = left;
        }
        
        // 오른쪽 확인
        int right = a[a.length - 1];
        for (int i = a.length - 1; i >= 0; i--) {
            if (right > a[i]) right = a[i];
            rightMin[i] = right;
        }
        
        // 확인
        for (int i = 0; i < a.length; i++) {
            if (a[i] != leftMin[i] && a[i] != rightMin[i]) continue;
            answer++;
        }
        
        return answer;
    }
}
