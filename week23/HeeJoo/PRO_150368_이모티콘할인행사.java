/*
 * 1. 가입자를 최대한 늘리기
 * 2. 판매액을 최대한 늘리기
 * n명의 사용자들에게 m개를 할인하여 판매
 * 이모티콘 할인율은 각각 다를 수 있으며, 10, 20, 30, 40 중 하나로 결정
 * 사용자 기준 1. 일정 비율 이상 할인하는 이모티콘 모두 구매
 * 사용자 기준 2. 구매 비용의 합이 일정 가격 이상이 되면 구매 대신 플러스 서비스 가입
 * 입력 1. int[][] users : n명의 구매 기준을 담은 2차원 정수 배열... ex) [할인율, 상한선]
 * 입력 2. int[] emoticons : 이모티콘 m개의 정가를 담은 1차원 정수 배열...
 * return 최대 목적 달성 ~ [플러스 가입 수, 이모티콘 매출액]
 */

/*
 * 1. 할인율 경우의 수 만들기
 * 2-1. 서비스 가입 수 비교 : 최종, 중간 둘 다 ...
 * 2-2. 판매액 비교
 */
import java.util.* ;

class Solution {
    static int ansCount = 0, ansSum = 0;
    static int[] percent = new int[]{10, 20, 30, 40};
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        int[] sales = new int[emoticons.length];
        perm(0, sales, emoticons, users);
        
        answer[0] = ansCount;
        answer[1] = ansSum;
        
        return answer;
    }
    
    // 1. 이모티콘 할인율 경우의 수
    public void perm(int count, int[] sales, int[] emoticons, int[][] users) {
        if(count == emoticons.length) {
            compute(sales, users, emoticons);
            return;
        }
        
        for(int index : percent) {
            sales[count] = index;
            perm(count+1, sales, emoticons, users);
        }
    }
    
    // 2. 판매 결과 비교
    public void compute(int[] sales, int[][] users, int[] emoticons) {
        int count = 0;
        int sum = 0;
        
        for(int i = 0; i < users.length; i++) {
            int perSum = 0; // 현재 선택된 유저의 구매 총액
            for(int j = 0; j < sales.length; j++) {
                // 이모티콘 할인율이 더 높은 경우만 구매                
                if(sales[j] >= users[i][0]) {
                    perSum +=  buy(sales[j], emoticons[j]);
                }
            }
            // 구매 총액이 상한선보다 높으면 플러스 가입
            if(perSum >= users[i][1]) {
                count++;
            }
            // 낮으면 그냥 구매
            else {
                sum += perSum;
            }
        }
        // 결과 비교
        // 1. 플러스 가입자 수 증가
        
        if(count > ansCount) {
            ansCount = count;
            ansSum = sum;
        }
        // 2. 가입자 수 동일, 판매액 증가
        else if(count == ansCount) {
            ansSum = Math.max(ansSum, sum);
        }
    }
    
    // 이모티콘 구입 가격
    public int buy(int emoPer, int emoticon) {
        return emoticon * (100 - emoPer) / 100;
    }
}
