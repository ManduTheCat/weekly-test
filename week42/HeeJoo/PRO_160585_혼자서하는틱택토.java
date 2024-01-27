/*
 * 3x3
 * 선공(O), 후공(X)
 * 가로, 세로, 대각선으로 3개가 같은 표시가 만들어지면 승리
 * 9칸이 모두 찼지만 승리하지못하면 무승부
 * 선공, 후공 번갈아가며 진행
 * 실수 1. 선공 때 "X" 또는 후공 때 "O" 입력
 * 실수 2. 승리 조건을 만족했지만 계속 진행
 * return 1(가능한 게임 상황) or 0(불가능한 게임 상황)

 * 1. 선공과 후공 개수 비교
 * 2. 가로 - 세로 - 대각 승리조건 확인
 * 선공 승리면 gap = 1 / 후공 승리면 gap = 0 / 무승부면 gap = 0
 */

class Solution {
    public int solution(String[] board) {
        
        // 1. 선공, 후공 개수 비교
        int[] count = countCheck(board);
        int gap = count[0] - count[1];
        if(gap < 0 || gap > 1) { // 0, 1만 가능
            return 0;
        }
        
        // 2. 승리조건 만족 확인
        boolean first = winCheck(board, 'O');
        boolean second = winCheck(board, 'X');
        if(gap == 1 && second) { // 선공 승리인데 불가능한 경우
            return 0;
        }
        else if(gap == 0 && first) { // 후공 승리인데 불가능한 경우
             return 0;
        }
        
        return 1;
    }
    
    public int[] countCheck(String[] board) {
        // 선공(first), 후공(second)
        int first = 0, second = 0;
        
        for(String str : board) {
            for(int i = 0; i < str.length(); i++) {
                char target = str.charAt(i);
                if(target == 'O') {
                    first++;
                } 
                else if(target == 'X') {
                    second++;
                }
            }
        }      
        
        return new int[]{first, second};
    }
    
    public boolean winCheck(String[] board, char c) {
        int length = board.length;
        boolean check = true;
        // 1. 가로
        for(String str : board) {
            check = true;
            for(int i = 0; i < length; i++) {
                if(str.charAt(i) != c) {
                    check = false;
                    break;
                }
            }
            
            if(check) {
                return true;
            }
        }
        
        // 2. 세로
        for(int i = 0; i < length; i++) {
            check = true;
            for(int j = 0; j < length; j++) {
                if(board[j].charAt(i) != c) {
                    check = false;
                    break;
                }
            }
            
            if(check) {
                return true;
            }
        }
        
        // 3. 대각
        check = true;
        for(int i = 0; i < length; i++) {        
            if(board[i].charAt(i) != c) {
                check = false;
                break;
            }
        }
        
        if(check) {
            return true;
        }
        
        check = true;
        for(int i = 0; i < length; i++) {       
            if(board[i].charAt(length - 1 - i) != c) {
                check = false;
                break;
            }
        }

        return check;
    }
}
