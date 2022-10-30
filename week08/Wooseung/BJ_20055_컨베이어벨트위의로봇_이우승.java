import java.io.*;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어위의로봇_이우승 {

    public static int N;
    public static int K;
    public static int[] belt;
    public static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < belt.length; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(simulation(0) + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int simulation(int cnt) {
        while (checkDurability()) {
            // 1. 벨트 이동
            int temp = belt[belt.length - 1]; 
            for (int i = belt.length - 1; i > 0; i--) {
                belt[i] = belt[i - 1];
            }
            belt[0] = temp;

            for (int i = robot.length - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;

            // 2. 로봇 이동
            robot[N - 1] = false;
            for (int i = N - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && belt[i] >= 1) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    belt[i]--;
                }
            }

            // 3. 올라가는 위치에 로봇이 있다면 올리기
            if (belt[0] > 0) {     
                robot[0] = true;
                belt[0]--;
            }

            cnt++;
        }

        return cnt;
    }
    
    // 내구도 체크
    public static boolean checkDurability() {
        int cnt = 0;

        for (int i = 0; i < belt.length; i++) {
            if (belt[i] == 0) { // 내구도가 0이면 cnt 증가
                cnt++;
            }
            if (cnt >= K) { // 내구도 0이 K개 이상이면 종료
                return false;
            }
        }
        return true;
    }

}
