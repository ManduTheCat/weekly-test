package saturday.sat230121;

/*
N개의 숫자로 구분된 각각의 마을에 한 명의 학생
N명의 학생이 X(1 <= X <= N)번 마을에 모여서 파티
해당 마을 사이에는 총 M개의 단방향 도로들이 존재
i번째 길을 지나는데 Ti(1 <= Ti <= 100)의 시간 소비
각각의 학생들은 파티 참석 후 다시 자신의 마을로 복귀해야 함 ~ 최단 시간을 원해요 ~
단방향 도로이기 때문에, 오고 가는 길이 다를 수도 있음
N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생 구하기

1. X마을에서 각 마을로 가는 최단 거리 : 일반 다익스트라
2. 각 마을에서 X마을로 가는 최단 거리 : 간선을 반대로 입력한 다익스트라 why ?  A -> B 간선에서 B -> A 간선이 됨
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements  Comparable<Node>{
    int to, weight; // 도착 정점, 가중치
    
    public Node(int to, int weight){
        this.to = to;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight; // 가중치 오름차순 정렬
    }
}
public class BJ_1238_파티 {
    static final int INF = (int)1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 전체 마을 수 = 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수 = 간선의 개수
        int X = Integer.parseInt(st.nextToken()); // 파티 장소 = 시작 정점

        // 정점 인접리스트
        ArrayList<Node>[] graph = new ArrayList[N+1];
        ArrayList<Node>[] graphReverse = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            graphReverse[i] = new ArrayList<>();
        }

        int[] dist = new int[N+1]; // 시작정점(X)과 다른 정점(index) 간의 최단 경로

        // 간선 정보 입력
        // i번째 도로의 시작점, 끝점, 소요 시간 Ti
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            // start -> dest = cost
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(dest, cost));
            graphReverse[dest].add(new Node(start, cost));
        }

        int[] dist1 = Dijkstra(graph, N, X); // X마을 -> 각 마을
        int[] dist2 = Dijkstra(graphReverse, N, X); // 각 마을 -> X마을

        int result = 0;
        for(int i = 1; i <= N; i++){
            result = Math.max(result, dist1[i] + dist2[i]);
        }

        System.out.println(result);
    }

    public static int[] Dijkstra(ArrayList<Node>[] nodeList, int V, int K){
        int[] dist = new int[V+1]; // 시작점K에서 다른 정점(index)까지의 최단 경로
        Arrays.fill(dist, INF); // 경로x로 초기화
        dist[K] = 0; // 자기자신은 항상 0

        PriorityQueue<Node> pq = new PriorityQueue<>(); // 가중치 오름차순
        pq.add(new Node(K, 0)); //  시작점(K)
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            
            int to = now.to;
            int weight = now.weight;
            
            if(dist[to] < weight) continue; // 이미 X -> to가 최단 거리인 경우 pass
            
            for(int i = 0; i < nodeList[to].size(); i++){ // 현재 정점과 연결된 루트 탐색
                int next = nodeList[to].get(i).to; // 연결된 정점
                int nextWeight = nodeList[to].get(i).weight + weight; // 연결된 정점까지의 가중치 = 간선의 가중치 + 현재 가중치
                
                if(dist[next] > nextWeight) { // 새로운 가중치가 더 적다면 최단경로 갱신
                    dist[next] = nextWeight;
                    pq.add(new Node(next, nextWeight)); // 연결된 정점을 다음 탐색 대상으로 선정 ~ 우선순위 큐를 이용하여 최단경로순으로 자동 정렬
                }
            }
        }


        return dist;
    }
}
