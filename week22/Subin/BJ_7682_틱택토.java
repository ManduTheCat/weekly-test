import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private final static int X_WIN = 0;
    private final static int O_WIN = 1;
    private final static int X_WIN_OR_DRAW = 2;
    private final static int ETC = 3;

    private static int checkCase(String board) {
        int oCnt = 0, xCnt = 0;

        for (int i = 0; i < board.length(); ++i) {
            if (board.charAt(i) == 'O') ++oCnt;
            else if (board.charAt(i) == 'X') ++xCnt;
        }

        if (xCnt == oCnt + 1 && xCnt + oCnt == 9) return X_WIN_OR_DRAW;
        else if (xCnt == oCnt + 1) return X_WIN;
        else if (xCnt == oCnt) return O_WIN;
        else return ETC;
    }

    private static boolean checkRow(char p, char a, char b, char c) {
        if (p != a) return false;
        if (a != b) return false;
        if (b != c) return false;
        return true;
     }

    private static boolean checkWin(String board, char p) {
        // 가로 확인
        if (checkRow(p, board.charAt(0), board.charAt(1), board.charAt(2))) return true;
        if (checkRow(p, board.charAt(3), board.charAt(4), board.charAt(5))) return true;
        if (checkRow(p, board.charAt(6), board.charAt(7), board.charAt(8))) return true;

        // 세로 확인
        if (checkRow(p, board.charAt(0), board.charAt(3), board.charAt(6))) return true;
        if (checkRow(p, board.charAt(1), board.charAt(4), board.charAt(7))) return true;
        if (checkRow(p, board.charAt(2), board.charAt(5), board.charAt(8))) return true;

        // 대각선 확인
        if (checkRow(p, board.charAt(0), board.charAt(4), board.charAt(8))) return true;
        if (checkRow(p, board.charAt(2), board.charAt(4), board.charAt(6))) return true;

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        StringBuilder sb = new StringBuilder();

        while (!(input = br.readLine()).equals("end")) {
            int c = checkCase(input);
            boolean valid = false;

            if (c == X_WIN_OR_DRAW) {
                if (!checkWin(input, 'O')) valid = true;
            } else if (c == X_WIN) {
                if (checkWin(input, 'X') && !checkWin(input, 'O')) valid = true;
            } else if (c == O_WIN) {
                if (checkWin(input, 'O') && !checkWin(input, 'X')) valid = true;
            }

            if (valid) sb.append("valid\n");
            else sb.append("invalid\n");
        }
        
        System.out.print(sb);
    }

}
