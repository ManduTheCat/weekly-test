import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://velog.io/@jyleedev/%EB%B0%B1%EC%A4%80-15591-MooTube
public class BJ_15591_ MooTube {

    private static List<Node>[] usado;

    private static class Node {
        int idx;
        int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        usado = new List[N];
        for (int i = 0; i < N; i++) {
            usado[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()) - 1;
            int q = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken());

            usado[p].add(new Node(q, r));
            usado[q].add(new Node(p, r));
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;

            sb.append(findMinPath(k, v)).append("\n");
        }

        System.out.print(sb);
    }

    private static int findMinPath(int k, int v) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(v);

        boolean[] visited = new boolean[usado.length];
        visited[v] = true;

        int count = 0;

        while (!pq.isEmpty()) {
            int cur = pq.poll();

            for (Node next : usado[cur]) {
                if (!visited[next.idx] && k <= next.weight) {
                    pq.add(next.idx);
                    visited[next.idx] = true;
                    ++count;
                }
            }
        }

        return count;
    }

}
