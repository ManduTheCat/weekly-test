class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] graph = new int[n + 1][n + 1];
        
        for(int[] arr : results) {
            graph[arr[0]][arr[1]] = 1;
            graph[arr[1]][arr[0]] = -1;
        }
        
        for(int k = 0; k <= n; k++) {
            for(int i = 0; i <= n; i++) {
                for(int j = 0; j <= n; j++) {
                    if(graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                        graph[j][i] = -1;
                    }
                    if(graph[i][k] == -1 && graph[k][j] == -1) {
                        graph[i][j] = -1;
                        graph[j][i] = 1;
                    }
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            int count = 0;
            for(int j = 1; j <= n; j++) {
                if(graph[i][j] != 0) count++;
            }
            if(count == n - 1) answer++;
        }
        
        return answer;
    }
}
