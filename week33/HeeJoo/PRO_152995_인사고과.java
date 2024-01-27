/*
 * 각 사원마다 "근무 태도 점수"와 "동료 평가 점수" 기록
 * 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 인센티브 x
 * 두 점수의 합이 높은 순으로 석차를 내어 석차에 따라 인센티브 차등 지급
 * 두 점수의 합이 동일한 사원들은 동석차 ~ 동석차의 수만큼 다음 석차는 건너 뜀
 * 완호의 석차 구하기 ~!
 
 * 점수합 별 그룹핑
 * 자기보다 높은 그룹과 비교해서 인센티브 대상자인지 확인
 */

import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        
        List<List<int[]>> list = new ArrayList<>();
        for(int i = 0; i <= 200000; i++) {
            list.add(new ArrayList<int[]>());
        }
        
        int max = 0; // 최고점
        int target = scores[0][0] + scores[0][1]; // 원호
        for(int[] score : scores) {
            list.get(score[0] + score[1]).add(score);
            
            max = Math.max(max, score[0] + score[1]);
        }
        
        // 1. 현재 index보다 높은 차수(최소 차이 2)와 비교해서 인센여부 확인
        // 2. 완호 index 되면 앞에 갯수 구하기
        int index = max - 1;
        while(index > target) {
            List<int[]> now = list.get(index); // 대상 리스트
            
            for(int k = max; k > index + 1; k--) {
                List<int[]> tmp = list.get(k); // 비교 리스트
                
                for(int i = now.size() - 1; i >= 0; i--) {
                    for(int j = 0; j < tmp.size(); j++) {
                        if(now.get(i)[0] < tmp.get(j)[0] && now.get(i)[1] < tmp.get(j)[1]) {
                            now.remove(i);
                            break;
                        }
                    }
                }
            }
            index--;
        }
        
        // 완호가 인센티브를 받을 수 있는지 확인
        boolean flag = true;
        for(int k = max; k > target + 1; k--) {
            List<int[]> tmp = list.get(k); // 비교 리스트

            for(int j = 0; j < tmp.size(); j++) {
                if(scores[0][0] < tmp.get(j)[0] && scores[0][1] < tmp.get(j)[1]) {
                    flag = false;
                    break;
                }
            }
        }
        
        if(!flag) {
            answer = -1;
        } else {
            index = max;
            while(index > target) {
                answer += list.get(index--).size();
            }
        }
        
        // for(List<int[]> test : list) {
        //     if(test.isEmpty()) continue;
        //     print(test);
        // }
        
        return answer;
    }
    
    public void print(List<int[]> list) {
        for(int[] arr : list) {
            System.out.println(arr[0] + ", " + arr[1]);
        }
        System.out.println();
    }
}

/* 
 * 이렇게 풀어야 한다 ..ㅠㅠ
 * 완호 앞에 있는 사람의 수만 구하면 됨
 */
// import java.util.Arrays;

// class Solution {
//     public int solution(int[][] scores) {

//         int[] wanHo = scores[0];
//         // 근무태도점수 내림차순 / 동료평가점수 오름차순 정렬
//         Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

//         int answer = 1, maxScore = 0, wanHoSum = wanHo[0] + wanHo[1];

//         for (int[] score : scores) {
//             // 내 앞에 동료평가점수가 나보다 높은사람이 한명이라도 있으면 탈락
//             // 근무태도 동점자의 경우 동료평가 오름차순 하였으므로 고려하지 않아도 됨
//             if (score[1] < maxScore) {
//                 // 탈락대상
//                 if (score.equals(wanHo))
//                     return -1;
//             } else {
//                 // 인센대상
//                 maxScore = Math.max(maxScore, score[1]);
//                 if (score[0] + score[1] > wanHoSum)
//                     answer++;
//             }
//         }
//         return answer;
//     }
// }
