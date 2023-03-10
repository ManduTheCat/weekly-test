import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_5972_택배배송 {

    static int N, M;
    static Node[] nodes;

    static class Node implements Comparable<Node> {
        int to;
        int weight;
        Node next;

        Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new Node[N];
        for (int i = 0; i < M; i++) {
             st = new StringTokenizer(br.readLine());

             int from = Integer.parseInt(st.nextToken()) - 1;
             int to = Integer.parseInt(st.nextToken()) - 1;
             int weight = Integer.parseInt(st.nextToken());

            nodes[from] = new Node(to, weight, nodes[from]);
            nodes[to] = new Node(from, weight, nodes[to]);
        }

        System.out.println(dijkstra(0));
    }

    private static int dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.add(new Node(start, 0, null));

        int[] dp = new int[N];
        Arrays.fill(dp, (int) 1e9);
        dp[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dp[cur.to] < cur.weight) continue;

            for (Node next = nodes[cur.to]; next != null; next = next.next) {
                if (dp[next.to] > dp[cur.to] + next.weight) { // 최소 경로 갱신
                    dp[next.to] = dp[cur.to] + next.weight;
                    pq.add(next);
                }
            }
        }

        return dp[N - 1];
    }

}
