class Solution {
    
    private int m;
    private int[] selected, answer;
    
    private final int[] discount = {10, 20, 30, 40};
    
    public int[] solution(int[][] users, int[] emoticons) {
        m = emoticons.length;
        answer = new int[2];
        selected = new int[m];
        perm(0, users, emoticons);
        return answer;
    }
    
    // 이모티콘 할인율 순열 생성
    public void perm(int idx, int[][] users, int[] emoticons) {
        if (idx == m) {
            solve(users, emoticons);
            return ;
        }
        
        for (int i = 0; i < 4; i++) {
            selected[idx] = i;
            perm(idx + 1, users, emoticons);
        }
    }
    
    private void solve(int[][] users, int[] emoticons) {
        int cnt = 0; // 서비스 가입자 수
        int sales = 0; // 이모티콘 판매액
        
        for (int[] user: users) {
            boolean flag = false; // 이모티콘 플러스 가입 여부
            int price = 0; // 고객의 이모티콘 구매비용의 합
            for (int i = 0; i < m; i++) {
                // 사용자는 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매한다.
                if (user[0] <= discount[selected[i]]) {
                    price += emoticons[i] * (100 - discount[selected[i]]) / 100;
                    // 가격 이상의 돈을 이모티콘 구매에 사용한다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입한다.
                    if (price >= user[1]) {
                        ++cnt;
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) sales += price;
        }
        
        // 이모티콘 할인 행사의 목표는
        // 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것
        // 2. 이모티콘 판매액을 최대한 늘리는 것
        if (answer[0] < cnt || (answer[0] == cnt && answer[1] < sales)) {
            answer[0] = cnt;
            answer[1] = sales;
        } 
    }
    
}
