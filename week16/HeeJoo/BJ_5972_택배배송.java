package saturday.sat230304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 최소한의 소들을 만나면서 지나가기
 * N개의 헛간, M개의 소의 길, 각각의 길은 C_i마리 소 존재
 * 소들의 길은 두 개 이상의 떨어진 헛간인 A_i와 B_i를 잇는다.
 * 헛간 1에서 헛간 N으로 갈 때, 최소 여물 비용
 */

class Node implements Comparable<Node> {
    int end, val;

    Node(int end, int val) {
        this.end = end;
        this.val = val;
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }

}

public class BJ_5972_택배배송 {
    static final int INF = (int)1e9;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 헛간 개수 ~ 노드
        int m = Integer.parseInt(st.nextToken()); // 소의 길 개수 ~ 엣지

        ArrayList<Node>[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            for(Node node : list[now.end]) {
                if(dist[node.end] > dist[now.end] + node.val) {
                    dist[node.end] = dist[now.end] + node.val;
                    pq.offer(new Node(node.end, dist[node.end]));
                }
            }
        }

        System.out.print(dist[n]);
    }
}
