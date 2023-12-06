/*
 * 불량 사용자 아이디 중 일부 문자를 '*'로 가리기
 * 최소 하나 이상의 '*' 문자 사용
 * 제재 아이디 : 불량 사용자 목록에 있는 아이디
 * 당첨에서 제외되어야 할 제재 아이디 목록 경우의 수 구하기
 */

import java.util.*;

class Solution {
    static Set<Integer> set = new HashSet<>(); // 중복 제거용 Set
    public int solution(String[] user_id, String[] banned_id) {

        func(0, 0, user_id, banned_id);

        return set.size();
    }
    
    // flag로 n번째 아이디가 제재되었음을 표시
    public void func(int cnt, int flag, String[] user_id, String[] banned_id) {
        if(cnt == banned_id.length) {
            set.add(flag);
            return;
        }
        
        for(int i = 0; i < user_id.length; i++) {
            if((flag & (1 << i)) == 0) { // i번째 user 미제재 상태
                if(check(banned_id[cnt], user_id[i])) { // 제재 가능하면
                    func(cnt + 1, flag | (1 << i), user_id, banned_id);
                }        
            }   
        }
    }
    
    public boolean check(String ban, String user) {
        if(ban.length() != user.length()) {
            return false;
        }
        
        for(int i = 0; i < ban.length(); i++) {
            char ch = ban.charAt(i);
            if(ch == '*') {
                continue;
            } else {
                if(ch != user.charAt(i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
