class Solution {
    public int answer = 0;
    
    public int solution(int[] numbers, int target) {
        boolean[] visited = new boolean[numbers.length];
        DFS(visited, numbers, target, 0);
    
        return answer;
    }
    
    public void DFS(boolean[] visited, 
                    int[] numbers, 
                    int target, 
                    int depth) {
        
        if (depth == numbers.length) {
            int total = 0;
            
            for (int i = 0; i < numbers.length; i++) {
                if (visited[i]) {
                    total += numbers[i];
                } else {
                    total -= numbers[i];
                }
            }
            
            if (total == target) answer++;
            return;
        }
        
        visited[depth] = false;
        DFS(visited, numbers, target, depth + 1);
        visited[depth] = true;
        DFS(visited, numbers, target, depth + 1);
    }
}
