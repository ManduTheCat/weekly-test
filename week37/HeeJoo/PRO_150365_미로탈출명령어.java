/*
 * n * m 격자 미로 ~ (x, y) -> (r, c)
 * 조건 1. 바깥으로 나갈 수 없음
 * 조건 2. 총 이동거리 k. 같은 격자 중복 방문 가능
 * 조건 3. 문자열이 사전 순으로 가장 빠른 경로로 탈출
 * 좌(l), 우(r), 상(u), 하(d)
 * 탈출 불가 시 return "impossible"
 
 * 조건부 탈출이 가능한 지 확인 : k와 distance 비교, 홀수/짝수
 */

class Solution {
    static String answer = "z";
    static final String IMP = "impossible";
    static String[] cmd = {"d", "l", "r", "u"}; // 사전순으로 탐색
    static int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    static boolean flag = false;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        int dist = distance(x, y, r, c);
        
        // 최소 이동거리 부족 or 잉여 이동거리 탈출 불가능
        if(dist > k || (dist - k) % 2 != 0) {
            return IMP;
        }
        
        func("", n, m, x, y, r, c, k);
        
        if(answer == "z") return "impossible";
        
        return answer;
    }
    
    public void func(String str, int n, int m, int x, int y, int r, int c, int k) {
        if(flag) return; // 가장 빠른 경로 완성
        
        if(distance(x, y, r, c) > k) { // 중간 탈출 여부
            return;
        }
        
        if(k == 0) { // 이동거리 소모 완료
            if(x == r && y == c) { // 목표 도착
                answer = str;
                flag = true;
            }
            
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            
            if(!check(n, m, dx, dy)) {
                continue;
            }
            
            func(str.concat(cmd[i]), n, m, dx, dy, r, c, k - 1);
        }
    }
    
    public boolean check(int n, int m, int x, int y) {
        if(x < 1 || x > n) return false;
        if(y < 1 || y > m) return false;
        
        return true;
    }
    
    public int distance(int x, int y, int a, int b) {
        return Math.abs(x - a) + Math.abs(y - b);
    }
}
