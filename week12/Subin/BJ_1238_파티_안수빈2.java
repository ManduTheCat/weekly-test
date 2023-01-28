
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
class BJ_1238_파티_안수빈2 {
 
    static int N, M, X;
    static int[] dist, revDist;
    static List<List<Node>> list, revList;

    static final int INF = (int) 1e9;
    
    static class Node implements Comparable<Node> {
        int index;
        int distance;
 
        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
 
        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        dist = new int[N+1];
        revDist = new int[N+1];
        list = new ArrayList<List<Node>>();
        revList = new ArrayList<List<Node>>();
 
        // 각 거리 초기화
        init();
 
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
 
            list.get(v1).add(new Node(v2, dist));
            revList.get(v2).add(new Node(v1, dist));
        }
 
        dijkstra(list, dist, X);
        dijkstra(revList, revDist, X);
 
        // answer
        int max = -1;
        for(int i=1; i<=N; i++)
            max = Math.max(max, dist[i] + revDist[i]);
        System.out.println(max);
    }
 
    static void init() {
        Arrays.fill(dist, INF);
        Arrays.fill(revDist, INF);
 
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<Node>());
            revList.add(new ArrayList<Node>());
        }
    }
 
    static void dijkstra(List<List<Node>> list, int[] distance, int start) {
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
 
        distance[start] = 0;
        pq.add(new Node(start, 0));
 
        while(!pq.isEmpty()) {
            int idx = pq.poll().index;
 
            // 방문한 곳은 또 방문할 필요 없음
            if(visited[idx]) continue;
            visited[idx] = true;
 
            for(Node node : list.get(idx)) {
                // node.index 까지의 거리는 (시작점->idx 거리 + idx->node.index 거리) 중 더 작은 것
                if(distance[node.index] > distance[idx] + node.distance) {
                    distance[node.index] = distance[idx] + node.distance;
                    pq.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }
}