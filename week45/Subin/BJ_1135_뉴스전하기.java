import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://kibbomi.tistory.com/232
public class Main {

    private static int n;
    private static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) continue;
            adjList[p].add(i);
        }

        System.out.println(dfs(0) - 1);
    }

    private static int dfs(int cur) {
        List<Integer> list = new ArrayList<>();

        int ret = 0;
        int w = adjList[cur].size() - 1;

        // 자식 노드를 방문하면서 해당 노드의 후손을 전부 방문할 때 걸리는 시간 구하기
        for (int next : adjList[cur]) {
            list.add(dfs(next));
        }

        // 오름차순 정렬 -> 오래 걸리는 노드를 먼저 방문해야 함
        Collections.sort(list);

        for (int l : list) {
            ret = Math.max(ret, l + w--); // 가장 시간이 적게 걸리는 노드를 가장 마지막에 방문
        }
        return ret + 1;
    }

}
