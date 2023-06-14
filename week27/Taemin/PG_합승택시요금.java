import java.util.*;

class Solution {
    
    int N;
    ArrayList<ArrayList<int[]>> JointList;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        JointList = new ArrayList<ArrayList<int[]>>();
        for (int i = 0; i <= n; i++) {
            JointList.add(new ArrayList<int[]>());
        }
        
        for (int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int charge = fares[i][2];
            
            JointList.get(from).add(new int[]{to, charge});
            JointList.get(to).add(new int[]{from, charge});
        }
        
        int[] distance = dijkstra(s);
        int answer = distance[a] + distance[b];
        
        for (int i = 1; i <= n; i++) {
            int[] stopover = dijkstra(i);
            
            answer = Math.min(answer, distance[i] + stopover[a] + stopover[b]);
        }
        
        return answer;
    }

    public int[] dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[N + 1];
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        distance[start] = 0;
        pq.add(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int curDestination = edge[0];
            int curCharge = edge[1];
            
            // 이미 방문한 배열
            if (visited[curDestination]) continue;
            
            visited[curDestination] = true;
            ArrayList<int[]> list = JointList.get(curDestination);
            
            for (int i = 0; i < list.size(); i++) {
                int[] nextEdge = list.get(i);
                int nextDestination = nextEdge[0];
                int nextCharge = nextEdge[1];
                
                if (!visited[nextDestination]) {
                    if (distance[nextDestination] > curCharge + nextCharge) {
                        distance[nextDestination] = curCharge + nextCharge;
                        pq.add(new int[]{nextDestination, distance[nextDestination]});
                    }
                }
            }
        }
        
        return distance;
    }
}
