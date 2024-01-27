/*
 * 1 ~ n번
 * 1 대 1 경기 진행
 * A > B ~ 항상 A 승리
 * return 정확하게 순위를 매길 수 있는 선수의 수
 
 * 정확한 순위를 알 수 있는 조건 : target의 윗 등수 인원 + 아랫 등수 인원 = n - 1 // 자기자신 제외
 * 낮은 점수 -> 높은 점수 단방향 그래프
 * 연결 여부만 확인하면 됨 ~ 인접행렬 사용해서 O(1) ~ 플로이드 워셜
 */

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        // 낮 -> 높 단방향 그래프
        int[][] map = new int[n+1][n+1];
        for(int i = 0; i < results.length; i++) {
            map[results[i][1]][results[i][0]] = 1;
        }
        
        // 플로이드 워셜을 이용해서 간선 연결
        // 경유 노드 k가 가장 첫 번째로 와야됨
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                if(k == i) continue;
                for(int j = 1; j <= n; j++) {
                    if(j == i || j == k) continue;
                    if(map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            int up = 0;
            int down = 0;
            
            for(int j = 1; j <= n; j++) {
                if(map[i][j] == 1) { // 높
                    up++;
                }
                if(map[j][i] == 1) { // 낮
                    down++;
                }
            }

            if(up + down == n - 1) {
                answer++;
            }
        }
        return answer;
    }
}
