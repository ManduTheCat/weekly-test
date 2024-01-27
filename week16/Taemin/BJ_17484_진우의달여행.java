import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class BJ_17484_진우의달여행 {
    static class Spaceship {
        int row;
        int col;
        int fuel;
        int direction;

        public Spaceship(int row, int col, int fuel, int direction) {
            this.row = row;
            this.col = col;
            this.fuel = fuel;
            this.direction = direction;
        }
    }
    static int N, M, Minimum;
    static int[][] Space;
    static Queue<Spaceship> Queue;

    static int[] Dr = { 1, 1, 1 };
    static int[] Dc = { -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Minimum = (int)1e9;
        Space = new int[N][M];
        Queue = new LinkedList<>();

        /* Space 정보 저장 */
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                Space[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        /* 첫 위치에서 이동 가능한 경우의 수를 큐에 저장 */
        for (int c = 0; c < M; c++) {
            int row = 0;
            int col = c;
            int fuel = Space[0][c];

            for (int d = 0; d < 3; d++) {
                int nextRow = row + Dr[d];
                int nextCol = col + Dc[d];

                if (nextCol < 0 || nextCol >= M) continue;

                int nextFuel = Space[nextRow][nextCol];

                Queue.add(new Spaceship(nextRow, nextCol, fuel + nextFuel, d));
            }
        }

        while(!Queue.isEmpty()) {
            Spaceship ship = Queue.poll();
            int row = ship.row;
            int col = ship.col;
            int fuel = ship.fuel;
            int direction = ship.direction;

            /* 끝에 도달한 우주선의 소모 연료를 비교하여 갱신한다 */
            if (row + 1 == N) {
                Minimum = Math.min(Minimum, fuel);
                continue;
            }

            /* 이동이 가능한 우주선을 이전 이동방향을 피해서 이동시킨다 */
            for (int d = 0; d < 3; d++) {
                if (d == direction) continue;

                int nextRow = row + Dr[d];
                int nextCol = col + Dc[d];

                if (nextCol < 0 || nextCol >= M) continue;

                int newFuel = fuel + Space[nextRow][nextCol];

                /* 이미 최소 연료를 넘어버리면 우주선을 버린다 */
                if (newFuel >= Minimum) continue;

                Queue.add(new Spaceship(nextRow, nextCol, newFuel, d));
            }
        }

        System.out.println(Minimum);
    }
}
