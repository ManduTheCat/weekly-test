package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_15591_MooTube {

    static int N, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        List<int[]>[] list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            list[p].add(new int[]{q, r});
            list[q].add(new int[]{p, r});
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            Queue<Integer> queue = new LinkedList<>();
            queue.add(v);

            boolean[] visited = new boolean[N+1];
            visited[v] = true;

            int count = 0;
            while (!queue.isEmpty()) {
                int root = queue.poll();
                List<int[]> l = list[root];

                for (int[] elem : l) {
                    if (visited[elem[0]]) continue;
                    if (elem[1] < k) continue;
                    visited[elem[0]] = true;
                    queue.add(elem[0]);
                    count++;
                }
            }

            builder.append(count).append("\n");
        }

        System.out.print(builder.toString());
    }
}
