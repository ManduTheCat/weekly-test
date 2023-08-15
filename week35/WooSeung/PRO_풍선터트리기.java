class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        
        int min = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] < min) min = a[i];
    
            leftMin[i] = min;
        }
        
        min = a[a.length - 1];
        for(int i = a.length - 1; i >= 0; i--) {
            if(a[i] < min) min = a[i];
            
            rightMin[i] = min;
        }
        
        for(int i = 0; i < a.length; i++) {
            if(a[i] > leftMin[i] && a[i] > rightMin[i]) continue;
            answer++;
        }
        
        return answer;
    }
}
