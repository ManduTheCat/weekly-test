import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_5972_택배배송 {

    static class Node implements Comparable<Node> {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
//            return Integer.compare(other.cost, this.cost);
        }
    }

    static int N, M;

    static boolean[] Visited;

    static int[] Distance;

    static ArrayList<ArrayList<Node>> JointList;

    static PriorityQueue<Node> PQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Visited = new boolean[N + 1];

        Distance = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            Distance[i] = Integer.MAX_VALUE;
        }

        JointList = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i <= N; i++) {
            JointList.add(new ArrayList<>());
        }


        /* 인접 리스트 생성 */
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            JointList.get(from).add(new Node(to, cost));
            JointList.get(to).add(new Node(from ,cost));
        }

        /* 다익스트라 알고리즘 */
        PQ = new PriorityQueue<>();
        PQ.add(new Node(1, 0));
        Distance[1] = 0;

        while (!PQ.isEmpty()) {
            Node cur = PQ.poll();

            if (Visited[cur.node]) continue;
            Visited[cur.node] = true;

            for (int i = 0; i < JointList.get(cur.node).size(); i++) {
                Node next = JointList.get(cur.node).get(i);
                int nextNode = next.node;
                int nextCost = next.cost;

                if (Distance[nextNode] > Distance[cur.node] + nextCost) {
                    Distance[nextNode] = Distance[cur.node] + nextCost;
                    PQ.add(new Node(nextNode, Distance[nextNode]));
                }
            }
        }


/* 시간초과 */
//        for (int i = 1; i <= N; i++) {
//            // 최소 이동 노드 찾기
//            int nodeValue = (int)1e9;
//            int nodeIdx = 0;
//            for (int j = 1; j <= N; j++) {
//                if (!Visited[j] && nodeValue > Minimum[j]) {
//                    nodeValue = Minimum[j];
//                    nodeIdx = j;
//                }
//            }
//
//            Visited[nodeIdx] = true;
//
//            // 현재 위치에서 거리 갱신
//            for (int j = 0; j < JointList.get(nodeIdx).size(); j++) {
//                Node node = JointList.get(nodeIdx).get(j);
//                int to = node.node;
//                int cost = node.cost;
//
//                if (!Visited[to] && Minimum[to] > Minimum[nodeIdx] + cost) {
//                    Minimum[to] = Minimum[nodeIdx] + cost;
//                }
//            }
//
//            // 목표 노드이면 종료
//            if (Visited[N]) break;
//        }

        System.out.println(Distance[N]);
    }
}
