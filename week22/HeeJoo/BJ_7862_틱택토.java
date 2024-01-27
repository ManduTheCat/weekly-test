package saturday.sat230429;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

/*
 * 3x3 격자판, 두 명이 번갈아가면서 말 놓기
 * 첫 번째 사람이 X, 두 번재 사람이 O를 놓음
 * 어느 때든지 한 사람이 가로, 세로, 대각선 방향으로 3칸을 이으면 성공 ~ 게임 종료
 * 게임판이 가득차도 게임 종료
 * 게임판의 상태가 주어졌을 때, 해당 상태가 틱택토 게임에서 발생할 수 있는 "최종 상태"인지 구하기
 *
 * X == O + 1 or O
 *
 * 1. X, O 개수가 규칙을 벗어나는 경우
 * 2. 성공 후 계속 진행된 경우
 * 3. 성공하지 못했는데 꽉 차지 않은 경우
 */
public class BJ_7682_틱택토 {
    public static void main(String[] args) throws Exception {
        String str = "XXXOO.XXX\n" + // i
                "XOXOXOXOX\n" + // v
                "OXOXOXOXO\n" + // i
                "XXOOOXXOX\n" + // v
                "XO.OX...X\n" + // v
                ".XXX.XOOO\n" + // i
                "X.OO..X..\n" + // i
                "OOXXXOOXO\n" + // i
                "end";

        BufferedReader br = new BufferedReader(new StringReader(str));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String line = "";

        while(!"end".equals(line = br.readLine())) {
            char[] array = line.toCharArray();

            int first = getCount(array, 'X');
            int second = getCount(array, 'O');

            // 1. 말의 개수가 규칙을 벗어난 경우
            if(first < second || first > second + 1 || first <= 2) {
                sb.append("invalid\n");
                continue;
            }

            // 2. 성공했는데 계속 진행된 경우
            boolean flagX = false;
            boolean flagO = false;

            // 2-1. X 성공
            if(check(array, 'X')) {
                flagX = true;

                if(first != second + 1) {
                    sb.append("invalid\n");
                    continue;
                }
            }

            // 2-2. O 성공
            if(check(array, 'O')) {
                flagO = true;

                if(first != second) {
                    sb.append("invalid\n");
                    continue;
                }
            }

            // 둘 다 성공한 경우
            if(flagX && flagO) {
                sb.append("invalid\n");
                continue;
            }

            // 3. 실패했는데 끝까지 진행하지 않은 경우
            if(!flagX && !flagO) {
                if(first + second != 9 || first != second + 1) {
                    sb.append("invalid\n");
                    continue;
                }
            }

            sb.append("valid\n");
        }

        System.out.println(sb);
    }

    public static int getCount(char[] array, char c) {
        int result = 0;

        for(char now : array) {
            if(now == c) {
                result++;
            }
        }

        return result;
    }

    public static boolean check(char[] array, char c) {
        if(checkRow(array, c)) return true;
        if(checkCol(array, c)) return true;
        if(checkDiag(array, c)) return true;

        return false;
    }
    // 1. 가로
    public static boolean checkRow(char[] array, char c) {
        for(int i = 0; i < 3; i++) {
            if(array[i * 3] != c) continue;
            if(array[i * 3] == array[i * 3 + 1] && array[i * 3] == array[i * 3 + 2]) {
                return true;
            }
        }

        return false;
    }

    // 2. 세로
    public static boolean checkCol(char[] array, char c) {
        for(int i = 0; i < 3; i++) {
            if(array[i] != c) continue;
            if(array[i] == array[i + 3] && array[i] == array[i + 6]) {
                return true;
            }
        }

        return false;
    }

    // 3. 대각
    public static boolean checkDiag(char[] array, char c) {
        if(array[0] == c && array[0] == array[4] && array[0] == array[8]) return true;
        if(array[2] == c && array[2] == array[4] && array[2] == array[6]) return true;

        return false;
    }
}
