package saturday.sat231202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;
import java.util.List;

/*
 * R x C : 각 칸은 비어있거나 미네랄 포함
 * 네 방향 중 하나로 인접한 미네랄이 포함된 두 칸은 같은 클러스터
 * A는 동굴 왼쪽, B는 오른쪽. 번갈아가며 막대기를 던진다.
 * 막대를 던지기 전에, 던질 높이를 정한다. 막대는 땅과 수평을 이루며 날아간다.
 * 막대가 날아가다 미네랄을 만나면, 그 칸에 있는 미네랄 모두 파괴 + 막대 멈춤
 * 미네랄이 파괴된 이후에 남은 클러스터 분리 ... 새롭게 생성된 클러스터가 떠 있는 경우에는 바닥으로 떨어짐
 * 클러스터는 다른 클러스터나 땅을 만나기 전까지 계속 떨어지며, 다른 클러스터 위에 떨어지는 경우 합쳐짐
 * 모든 막대를 던지고 난 이후에 미네랄 모양 구하기
 *
 * 공중에 떠 있거나, 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우 없음 -> 한 타임에 한 개 이하의 클러스터만 존재
 *
 * 1. 던지기 (좌->우(짝), 우->좌(홀))
 * 2. 클러스터 찾기 : 클러스터는 1개 이하니까... 위에서 부터 BFS로 찾기 ~ 바닥까지 못가면 클러스터
 * 3. 클러스터 이동
 * 3-1. 이동 시킬 클러스터 지우기
 * 3-2. 최소 이동 거리 구하기 : 몇 칸을 내려야 최초로 'x'를 만나는지 ?
 * 3-3. x칸 만큼 전체 내리기
 */

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class BJ_2933_미네랄 {
    static int R, C;
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌. 아래방향도 필요한가 ㅇㅅㅇ ?
    public static void main(String[] args) throws Exception {
        String str = "5 6\n" +
                "......\n" +
                "..xx..\n" +
                "..x...\n" +
                "..xx..\n" +
                ".xxxx.\n" +
                "1\n" +
                "3";
//        ......
//        ......
//        ..xx..
//        ..xx..
//        .xxxx.
//        BufferedReader br = new BufferedReader(new StringReader(str));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int t = 0; t < N; t++) {
            int row = R - Integer.parseInt(st.nextToken());

            throwAndDelete(row, t, map); // 1. 막대 던지고 삭제
            findCluster(map); // 2. 클러스터 찾기
        }

        print(map);
    }

    public static void moveCluster(char[][] map, List<Point> cluster) {
        // 3-1. 기존 클러스터 지우기
        for(Point p : cluster) {
            map[p.x][p.y] = '.';
        }

        // 3-2. 최소 이동 거리 구하기
        int dist = getMoveDistance(map, cluster);

        // 3-3. 전체 클러스터 내리기
        for(Point p : cluster) {
            map[p.x + dist][p.y] = 'x';
        }
    }
    public static int getMoveDistance(char[][] map, List<Point> cluster) {
        int result = Integer.MAX_VALUE;

        for(Point p : cluster) {
            int row = p.x;
            int col = p.y;

            int distance = 1;
            while (row + distance < R) {
                if (map[row + distance][col] == 'x') { // 해당 지점에서 처음 x와 닿음. 낙하만 하니까 row + distance
                    break;
                }

                distance++;
            }

            result = Math.min(result, distance - 1); // distance 그대로 주면 겹치니까 -1
        }

        return result;
    }
    public static void findCluster(char[][] map) {
        boolean[][] visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(visited[i][j] || map[i][j] == '.') {
                    continue;
                }

                List<Point> cluster = new ArrayList<>();
                cluster.add(new Point(i, j));

                Queue<Point> queue = new ArrayDeque<>();
                queue.offer(new Point(i, j));
                visited[i][j] = true;

                int height = i;
                while(!queue.isEmpty()) {
                    Point p = queue.poll();

                    for(int k = 0; k < 4; k++) {
                        int dx = p.x + dir[k][0];
                        int dy = p.y + dir[k][1];

                        if(!validateRange(dx, dy) || visited[dx][dy] || map[dx][dy] == '.') {
                            continue;
                        }

                        visited[dx][dy] = true;
                        queue.offer(new Point(dx, dy));
                        cluster.add(new Point(dx, dy));
                    }

                    height = Math.max(height, p.x);
                }

                if(height != R - 1) { // 바닥에 닿으면 낙하 xxx ~ 조건 만족 시 클러스터 찾기 완료
                    moveCluster(map, cluster); // 3. 클러스터 이동
                    return;
                }
            }
        }
    }
    public static void throwAndDelete(int height, int t, char[][] map) {
        int col = 0;
        if(t % 2 == 0) { // 좌 -> 우
            for(int i = 0; i < map[height].length; i++) {
                if(map[height][i] == 'x') {
                    col = i;
                    break;
                }
            }
        } else { // 우 -> 좌
            for(int i = map[height].length - 1; i >= 0; i--) {
                if(map[height][i] == 'x') {
                    col = i;
                    break;
                }
            }
        }

        map[height][col] = '.';
    }

    public static boolean validateRange(int x, int y) {
        if(x < 0 || x >= R || y < 0 || y >= C) {
            return false;
        }

        return true;
    }
    public static void print(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for(char[] arr : map) {
            for(char k : arr) {
                sb.append(k);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
