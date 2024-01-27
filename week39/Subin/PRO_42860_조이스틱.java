class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        
        int move = len - 1;
        for (int i = 0; i < len; i++) {
            // 상하 이동
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
        
            // 좌우 이동 (여기서 최적화!)
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') ++next;
            
            // 순서대로 가는 것과 뒤로 돌아가는 것 중 짧은 것 선택
            move = Math.min(move, i * 2 + (len - next));
            // 처음부터 역방향으로 가는 경우에서 돌아갈 수도 있음
            move = Math.min(move, (len - next) * 2 + i);  
        }
        
        return answer + move;
    }
}
