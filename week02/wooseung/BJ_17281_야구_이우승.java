import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P17281 {

    static int ans, n;
    static int[][] arr;
    static boolean[] selected;
    static int[] batting;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][10];
        selected = new boolean[10];
        batting = new int[10];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 1; j <= 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        selected[4] = true;
        batting[4] = 1;
        ans = Integer.MIN_VALUE;

        perm(2);
        System.out.println(ans);

    }

    static void perm(int cnt) { // 타순 순열 생성

        if(cnt == 10) {
            playBall();
            return;
        }
        for(int i = 1; i <= 9; i++) {
            if(selected[i]){
                continue;
            }
            selected[i] = true;
            batting[i] = cnt;
            perm(cnt + 1);
            selected[i] = false;
        }

    }

    static void playBall() {

        int score = 0;
        int startPlayer = 1;
        boolean[] base;

        for(int i = 1; i <= n ; i++){

            int outCnt = 0;
            base = new boolean[4];

            outer : while(true){
                for (int j = startPlayer; j <= 9; j++) {

                    int hitter = arr[i][batting[j]];

                    switch (hitter) {
                        case 0:
                            outCnt++;
                            break;
                        case 1:
                            for (int k = 3; k >= 1; k--) {   // 3루는 홈으로 2루는 3루로 1루는 2루로
                                if (base[k]) {
                                    if (k == 3) {
                                        score++;
                                        base[k] = false;
                                        continue;
                                    }
                                    base[k] = false;
                                    base[k + 1] = true;
                                }
                            }
                            base[1] = true; // 타자는 1루로
                            break;
                        case 2:
                            for (int k = 3; k >= 1; k--) {   // 3루는 홈으로 2루는 홈으로 1루는 3루로
                                if (base[k]) {
                                    if (k == 1) {
                                        base[k] = false;
                                        base[k + 2] = true;
                                    } else {
                                        base[k] = false;
                                        score++;
                                    }
                                }
                            }
                            base[2] = true; // 타자는 2루로
                            break;
                        case 3:
                            for (int k = 3; k >= 1; k--) {   // 3루는 홈으로 2루는 홈으로 1루는 홈으로
                                if (base[k]) {
                                    base[k] = false;
                                    score++;
                                }
                            }
                            base[3] = true; // 타자는 3루로
                            break;
                        case 4:
                            for (int k = 3; k >= 1; k--) {   // 모든 선수 홈으로
                                if (base[k]) {
                                    base[k] = false;
                                    score++;
                                }
                            }
                            score++;
                            break;
                    }
                    if (outCnt == 3) {  // 아웃카운트가 3이 되면 이닝 종료
                        startPlayer = j + 1;    // 다음 이닝에 칠 타자 설정
                        if (startPlayer == 10) {    // 처음쳐야하는 타자가 10번째라면 1로 변경
                            startPlayer = 1;
                        }
                        break outer;    
                    }
                }
                startPlayer = 1;    // 1이닝에 아웃카운트3개가 안되면 1로 초기화
            }

        }
        ans = Math.max(ans, score);
    }

}
