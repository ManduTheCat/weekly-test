package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_7682_틱택토 {

    static char[][] arr;
    static int cntX, cntO;
    static int[] dr = {0, 1, 0, -1, 1, 1, -1, -1};
    static int[] dc = {1, 0, -1, 0, -1, 1, -1, 1};
    static int xRowBingo, oRowBingo, xColBingo, oColBingo, xLeftBingo, xRightBingo, oLeftBingo, oRightBingo;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {

            String str = br.readLine();
            if(str.equals("end")) {
                break;
            }
            int idx = 0;
            cntX = 0;
            cntO = 0;

            arr = new char[3][3];

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {

                    arr[i][j] = str.charAt(idx);

                    if(str.charAt(idx) == 'X') {
                        cntX++;
                    }else if(str.charAt(idx) == 'O') {
                        cntO++;
                    }

                    idx++;
                }
            }

            xRowBingo = 0;
            xColBingo = 0;
            oRowBingo = 0;
            oColBingo = 0;
            xLeftBingo = 0;
            xRightBingo = 0;
            oLeftBingo = 0;
            oRightBingo = 0;

            if(cntX == cntO + 1 || cntX == cntO) {

                checkRowCol();
                checkDiagonal();

                if(xRowBingo >= 2 || oRowBingo >= 2 || xColBingo >= 2 || oColBingo >= 2) {
                    System.out.println("invalid");
                    continue;
                }

                if(cntX == cntO) {
                    if((xRowBingo > 0 || xColBingo > 0 || xLeftBingo > 0 || oLeftBingo < 0) ) {
                        System.out.println("invalid");
                        continue;
                    }
                }

                else if(cntX > cntO) {
                     if(oRowBingo > 0 || oColBingo > 0 || oLeftBingo > 0 || oRightBingo > 0) {
                        System.out.println("invalid");
                        continue;
                    }
                }

                if(cntX + cntO == 9) {
                    System.out.println("valid");
                    continue;
                }
                if(xRowBingo == 0 && oRowBingo ==0 && xColBingo == 0 &&
                        oColBingo == 0 && xLeftBingo == 0 && xRightBingo == 0 && oLeftBingo == 0 && oRightBingo == 0) {
                    System.out.println("invalid");
                    continue;
                }
                System.out.println("valid");

            }else {
                System.out.println("invalid");
            }

        }

    }

    static void checkRowCol() {

        for(int i = 0; i < 3; i++) {

            int rCntX = 0;
            int rCntO = 0;
            int cCntX = 0;
            int cCntO = 0;

            for(int j = 0; j < 3; j++) {
                // 가로 검사
                if(arr[i][j] == 'X') {
                    rCntX++;
                }else if(arr[i][j] == 'O') {
                    rCntO++;
                }
                // 세로 검사
                if(arr[j][i] == 'X') {
                    cCntX++;
                }else if(arr[j][i] == 'O') {
                    cCntO++;
                }

            }
            if(rCntX == 3) {
                xRowBingo++;
            }else if(rCntO == 3) {
                oRowBingo++;
            }
            if(cCntX == 3) {
                xColBingo++;
            }else if(cCntO == 3) {
                oColBingo++;
            }
        }

    }

    static void checkDiagonal() {

        int leftCntX = 0;
        int leftCntO = 0;
        int rightCntX = 0;
        int rightCntO = 0;
        int temp = 2;

        for(int i = 0; i < 3; i++) {
            // 왼쪽 대각선
            if(arr[i][i] == 'X') {
                leftCntX++;
            }else if(arr[i][i] == 'O') {
                leftCntO++;
            }
            // 오른쪽 대각선
            if(arr[i][temp] == 'X') {
                rightCntX++;
            }else if(arr[i][temp] == 'O') {
                rightCntO++;
            }
            temp--;
        }

        if(leftCntX == 3) {
            xLeftBingo++;
        }else if(leftCntO == 3) {
            oLeftBingo++;
        }

        if(rightCntX == 3) {
            xRightBingo++;
        }else if(rightCntO == 3) {
            oRightBingo++;
        }

    }

}
