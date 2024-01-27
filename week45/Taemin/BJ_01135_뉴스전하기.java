package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_01135_뉴스전하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer>[] childList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            childList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) continue;

            childList[parent].add(i);
        }

        int[] depth = new int[n];
        int result = dfs(childList, depth, 0);
        System.out.println(result);
    }

    private static int dfs(List<Integer>[] children, int[] depth, int parent) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            /* 깊이를 기준으로 내림차순 정렬 */
            @Override
            public int compare(Integer i1, Integer i2) {
                return Integer.compare(i2, i1);
            }
        });

        for (int child : children[parent]) {
            /* 자식 트리의 깊이를 DFS를 통해 계산한다 */
            depth[child] = 1 + dfs(children, depth, child);
            pq.add(depth[child]);
        }

        int max = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            /* 서브 트리 중 깊이가 가장 깊은 트리를 먼저 선택하여 이동한다
            *  한 곳을 선택할 때마다 1초가 요구되므로 (깊이 + 선택하는데 걸린 시간)을 계산
            *  해당 부모 트리에서 자식 트리를 모두 방문하기 위해 소모되는 시간을 저장한다
            *  사실상 depth 배열은 동작에 요구되는 소요 시간을 저장하는 배열이라고 보면 된다
            * */
            int d = pq.poll();
            max = Math.max(max, d + count);
            count++;
        }

        return max;
    }
}
