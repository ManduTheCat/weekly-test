class Solution {
    public int solution(String[] board) {
        if (check(board)) return 1;
        return 0;
    }
    
    /* 
        o 승리 - o가 하나 더 많고, x는 이기면 안됨
        x 승리 - o와 x가 동일하고, o는 이기면 안됨
        비김 - o와 x가 동일하거나, o가 하나 더 많고, 둘 다 이기면 안됨
    */ 
    private boolean check(String[] board) {
        int oCnt = 0, xCnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') ++oCnt;
                if (board[i].charAt(j) == 'X') ++xCnt;
            }
        }

        // O, X 개수 확인
        if (oCnt != xCnt && oCnt != xCnt + 1) return false;
        
        // O, X 승리 여부 판단
        boolean isOWin = checkWin(board, 'O');
        boolean isXWin = checkWin(board, 'X');
        
        if (!isOWin && !isXWin) return true; // 둘 중 아무도 이기지 못했다면 유효함
        if (oCnt == xCnt && !isOWin && isXWin) return true; // o와 x의 수가 같고, x만 이겼다면 유효함
        if (oCnt == xCnt + 1 && isOWin && !isXWin) return true; // o가 x보다 하나 많고, o만 이겼다면 유효함
        
        return false;
    }
    
    private boolean checkWin(String[] board, char flag) {
        // 가로 세로 확인
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(i) != flag) continue;
            if (checkWin(board[i].charAt(0), board[i].charAt(1), board[i].charAt(2))) return true;
            if (checkWin(board[0].charAt(i), board[1].charAt(i), board[2].charAt(i))) return true;
        }
        
        // 대각선 확인
        if (board[1].charAt(1) == flag) {
            if (checkWin(board[0].charAt(0), board[1].charAt(1), board[2].charAt(2))) return true;
            if (checkWin(board[2].charAt(0), board[1].charAt(1), board[0].charAt(2))) return true;
        }

        return false;
    }
    
    private boolean checkWin(char a, char b, char c) {
        if (a != b) return false;
        if (b != c) return false;
        if (c != a) return false;
        
        return true;
    }
}
