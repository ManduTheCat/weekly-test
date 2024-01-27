package saturday.sat231202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;


/*
 * 1 ~ N번 소
 * USADO : 두 동영상이 서로 얼마나 가까운 지를 측정하는 단위
 * N -1개의 동영상 쌍 존재 = 어떤 동영상에서 다른 동영상으로 가는 경로 반드시 하나 존재
 * 동영상(N - 1) = 정점 / USADO = 간선
 * 알고리즘 : USADO가 K 이상인 모든 동영상 추천 (어라라 ...? 저격인가 ...?)
 * 임의의 두 쌍 사이의 동영상의 USADO를 "그 경로의 모든 연결들의 USADO 중 최솟값"으로 결정
 * 주어진 예시에서 min(1, 3) = 2 // (1, 2) = 3, (2, 3) = 2 -> 1-2-3 연결 중 (2, 3)의 연결이 2로 가장 작음
 * 어떤 값 K에 대한 추천 동영상 개수 구하기
 *
 * 질의 입력 : k_i v_i = K = k_i일 때, v_i 동영상을 보고있는 소들에게 몇 개의 동영상이 추천되는지 ?
 */

class Node{
    int next, weight;

    Node(int next, int weight) {
        this.next = next;
        this.weight = weight;
    }
}
public class BJ_15591_MooTube {
    public static void main(String[] args) throws Exception {
//        String str = "4 3\n" +
//                "1 2 3\n" +
//                "2 3 2\n" +
//                "2 4 4\n" +
//                "1 2\n" +
//                "4 1\n" +
//                "3 1"; // 3 0 2
//        BufferedReader br = new BufferedReader(new StringReader(str));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // N : 동영상 수 N
        int Q = Integer.parseInt(st.nextToken()); // Q : 테스트 케이스 수

        ArrayList<Node>[] graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int k  = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[N + 1];
            visited[v] = true;

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(v);

            // BFS : USADO >= k만 카운트
            while(!queue.isEmpty()) {
                int cur = queue.poll();

                for(Node node : graph[cur]) {
                    if(visited[node.next]) continue;
                    if(node.weight < k) continue; // USADO가 k미만인 경우 연결 안 함으로써 그 이후도 확인xxx ~ 어차피 연결해도 루트 최소 USADO

                    visited[node.next] = true;
                    queue.offer(node.next);
                    cnt++;
                }
            }

            sb.append(cnt + "\n");
            cnt = 0;
        }

        System.out.println(sb);
    }
}
