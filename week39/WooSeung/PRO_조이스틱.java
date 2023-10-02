class Solution {
    public int solution(String name) {
        int answer = 0;
        
        int len = name.length();
        int move = (int) 1e9;
    
        for(int i = 0; i < len; i++) {
            System.out.println(Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1));
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            int index = i + 1;
            
            while(index < len && name.charAt(index) == 'A') index++;
            move = Math.min(move, Math.min(i * 2 + len - index, (len - index) * 2 + i));
            // move = Math.min(move, i * 2 + len - index);
            // move = Math.min(move, (len - index) * 2 + i);
        }
        
        return answer + move;
    }
}
