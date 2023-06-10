/*
 * N : 지점의 개수
 * S : 출발지점
 * A, B : 각각의 도착 지점
 * 두 사람이 모두 귀가하는데 소요되는 예상 최저 택시요금 계산
 */

/*
 * 1. 그래프 그리기
 * 2. 다익스트라로 S, A, B를 기준으로 모든 점에 대해 최단 경로 각각 구하기
 * 3. Math.min(S -> (A, B), (S -> i) + (i -> A) + (i -> B))
 */

import java.util.*;

class Solution {
    static final int INF = (int)1e9;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] graph = new int[n+1][n+1];
        
        for(int[] arr : graph) {
            Arrays.fill(arr, INF);
        }
        
        for(int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }
        
        // S : 합승 비용
        int[] dist = dijkstra(s, n, graph);
        int answer = dist[a] + dist[b];
        
        // A, B : 합승 이후 비용
        
        // answer : S -> i + (i -> A) + (i -> B)의 최소값
        for(int i = 1; i <= n; i++) {
            // 갈 수 없는 경우
            if(dist[i] == INF || aDist[i] == INF || bDist[i] == INF) {
                continue;
            }
            
            answer = Math.min(answer, dist[i] + aDist[i] + bDist[i]);
        int[] aDist = dijkstra(a, n, graph);
        int[] bDist = dijkstra(b, n, graph);
        }
        
        return answer;
    }
    
    public int[] dijkstra(int start, int n, int[][] graph) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        pq.add(new int[]{start, 0});
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if(dist[now[0]] < now[1]) {
                continue;
            }
            
            dist[now[0]] = now[1];
            for(int i = 1; i <= n; i++) {
                int cost = now[1] + graph[now[0]][i];
                if(cost < dist[i]) {
                    pq.add(new int[]{i, cost});
                }
            }
        }
        
        // System.out.println("dist : " + Arrays.toString(dist));
        
        return dist;
    }
}
