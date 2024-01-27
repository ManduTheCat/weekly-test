package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Town implements Comparable<Town> {
    int end;
    int weight;

    Town(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Town o) {
        return weight - o.weight;
    }
}

public class BJ_1238_파티 {
    static final int INF = 987654321;
    static ArrayList<ArrayList<Town>> list, reverseList;
    static int N, X;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        reverseList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        // 다익스트라를 위한 인접리스트 구현
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(start).add(new Town(end, weight));
            reverseList.get(end).add(new Town(start, weight));
        }

        int[] dist1 = dijkstra(list); // 출발점에서 목적지까지 가는데 최단거리
        int[] dist2 = dijkstra(reverseList); // 목적지에서 출발점까지 가는데 최단거리

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // 다익스트라 알고리즘
    public static int[] dijkstra(ArrayList<ArrayList<Town>> a) {
        PriorityQueue<Town> pq = new PriorityQueue<>();
        pq.offer(new Town(X, 0));

        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Town curTown = pq.poll();
            int cur = curTown.end;

            if (!check[cur]) {
                check[cur] = true;

                for (Town town : a.get(cur)) {
                    if (!check[town.end] && dist[town.end] > dist[cur] + town.weight) {
                        dist[town.end] = dist[cur] + town.weight;
                        pq.add(new Town(town.end, dist[town.end]));
                    }
                }
            }
        }
        return dist;
    }

}
