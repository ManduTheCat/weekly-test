import java.util.*;

class Solution {
    public int solution(String[] board) {
        // 나올 수 없는 상황
        // (1) X의 수가 O의 수 보다 많은 경우
        // (2) X와 O의 개수 차이가 2이상인 경우
        // (3) O가 정답이 나온 경우임에도 X의 수가 O의 수와 동일한 경우
        // (4) X가 정답이 나온 경우임에도 O의 수가 X의 수보다 큰 경우
        
        int countO = 0;
        int countX = 0;
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row].charAt(col) == 'O') {
                    countO++;
                } else if (board[row].charAt(col) == 'X') {
                    countX++;
                }
            }
        }
        
        // (1) 
        if (countX > countO) return 0;
        
        // (2)
        if (Math.abs(countO - countX) > 1) return 0;
        
        // (3), (4)
        boolean checkO = false;
        boolean checkX = false;
        
        // 세로 성공
        for (int col = 0; col < 3; col++) {
            char target = board[0].charAt(col);
            if (target == '.') continue;
            
            if(board[1].charAt(col) == target && board[2].charAt(col) == target) {
                if (target == 'O') checkO = true;
                else checkX = true;
            }
        }
        
        // 가로, 대각선 성공
        for (int row = 0; row < 3; row++) {
            char target = board[row].charAt(0);
            if (target == '.') continue;
            
            if (row == 0) {    
                if (board[0].charAt(1) == target && board[0].charAt(2) == target) {
                    if (target == 'O') checkO = true;
                    else checkX = true;
                }
                
                if (board[1].charAt(1) == target && board[2].charAt(2) == target) {
                    if (target == 'O') checkO = true;
                    else checkX = true;
                }
            } 
            else if (row == 1) {
                if (board[1].charAt(1) == target && board[1].charAt(2) == target) {
                    if (target == 'O') checkO = true;
                    else checkX = true;
                }
            }
            else {
                if (board[2].charAt(1) == target && board[2].charAt(2) == target) {
                    if (target == 'O') checkO = true;
                    else checkX = true;
                }
                
                if (board[1].charAt(1) == target && board[0].charAt(2) == target) {
                    if (target == 'O') checkO = true;
                    else checkX = true;
                }
            }
        }
        
        if (checkO && checkX) return 0;
        
        if (checkO && countO <= countX) return 0;
        
        if (checkX && countO > countX) return 0;
        
        
        
        return 1;
    }
}
