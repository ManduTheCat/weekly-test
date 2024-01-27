package saturday.sat231209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * 트리 구조 회사
 * 모든 직원은 정확하게 한 명의 직속 상사 존재
 * 자기 자신은 자기 자신의 직접 또는 간접 상사가 아님
 * 모든 직원은 매니저의 직접 또는 간접적인 부하
 * 매니저는 일단 자신의 직속 부하에게 한 번에 한 사람씩 전화
 * 각 부하는 각자의 직속 부하에게 한 번에 한 사람씩 전화
 * 모든 직원이 뉴스를 들을 때 까지 계속 진행 ...
 * 모든 사람은 자신의 직속 부하에게만 전화를 걸 수 있고, 전화는 1분 걸림
 * 모든 직원이 소식을 듣는데 걸리는 최솟값 구하기
 * 매니저는 0, 다른 사원은 1부터 시작
 *
 * 가장 오래 걸리는 쪽부터 우선 전화
 * n번 노드의 서브트리에 뉴스를 모두 전파하는데 시간을 많이 소요한 순서대로 ...
 */
public class BJ_1135_뉴스전하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 직원의 수 N

        String[] arr = br.readLine().split(" ");
        ArrayList<Integer>[] tree = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 1; i < N; i++) {
            tree[Integer.parseInt(arr[i])].add(i);
        }

        int[] dp = new int[N];
        call(0, dp, tree);

        System.out.println(call(0, dp, tree));
    }

    public static int call(int now, int[] dp, ArrayList<Integer>[] tree) {
        int max = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]); // {다음 번호, 소요 시간} ... 소요 시간 내림차순
        for(int next : tree[now]) {
            dp[next] = call(next, dp, tree) + 1;
            pq.offer(new int[]{next, dp[next]});
        }

        int cnt = 0;
        while(!pq.isEmpty()) {
            int[] next = pq.poll();
            max = Math.max(max, next[1] + cnt++);
        }

        return max;
    }
}
