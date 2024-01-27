import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int r;
    int c;
    public Location(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class BJ4179_불 {
    static int R, C, Time;
    static char[][] Map;
    static Queue<Location> Jihoon;
    static Queue<Location> Fires;

    static int[] Dx = { -1, 0, 1, 0 };
    static int[] Dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Time = -1;
        Map = new char[R+1][C+1];
        Jihoon = new LinkedList<>();
        Fires = new LinkedList<>();

        for (int r = 1; r <= R; r++) {
            String line = br.readLine();
            for (int c = 1; c <= C; c++) {
                char type = line.charAt(c-1);
                Map[r][c] = type;

                if (type == 'J') {
                    Jihoon.add(new Location(r, c));
                }

                if (type == 'F') {
                    Fires.add(new Location(r, c));
                }
            }
        }

        BFS();

        if (Time == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(Time);
        }
    }

    public static void BFS() {
        int time = 0;

        while (true) {
            time++;
            int numOfLoc = Jihoon.size();

            if (numOfLoc == 0) {
                Time = -1;
                return;
            }

            for (int i = 0; i < numOfLoc; i++) {
                Location index = Jihoon.poll();
                int r = index.r;
                int c = index.c;

                if (Map[r][c] == 'F') continue;

                for (int d = 0; d < 4; d++) {
                    int dr = r + Dx[d];
                    int dc = c + Dy[d];

                    if (dr < 1 || dc < 1 || dr > R || dc > C) {
                        Time = time;
                        return;
                    }

                    if (Map[dr][dc] == 'F' || Map[dr][dc] == 'J' || Map[dr][dc] == '#') continue;

                    if (Map[dr][dc] == '.') {
                        Map[dr][dc] = 'J';
                        Jihoon.add(new Location(dr, dc));
                        continue;
                    }
                }
            }

            int numOfFire = Fires.size();

            for (int i = 0; i < numOfFire; i++) {
                Location fire = Fires.poll();
                int r = fire.r;
                int c = fire.c;

                for (int d = 0; d < 4; d++) {
                    int dr = r + Dx[d];
                    int dc = c + Dy[d];

                    if (dr < 1 || dc < 1 || dr > R || dc > C) {
                        continue;
                    }

                    if (Map[dr][dc] == 'F' || Map[dr][dc] == '#') continue;

                    if (Map[dr][dc] == '.' || Map[dr][dc] == 'J') {
                        Map[dr][dc] = 'F';
                        Fires.add(new Location(dr, dc));
                        continue;
                    }
                }
            }
        }
    }
}
