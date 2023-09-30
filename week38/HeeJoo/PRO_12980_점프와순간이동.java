import java.util.*;

/*
 * 한 번에 K 칸 앞으로 점프 or (현재까지 온 거리) * 2 순간이동
 * K칸 점프 = K만큼의 건전지 소모
 * 최소 점프 이동으로 N만큼 떨어져 있는 장소로 가기
 
 * 현재까지 온 거리 = 순간이동한 거리 / 2
 * 짝수면 텔레포트, 홀수면 점프 +1
 */

public class Solution { 
    public int solution(int n) {
        int ans = 0;
        
        // 신박한 풀이 https://taesan94.tistory.com/142
        // n을 2진수화 했을 때 1의 개수
        return Integer.bitCount(n);
        
//         while(n != 0) {
//             if(n % 2 == 0) {
//                 n /= 2;
//             } else {
//                 n--;
//                 ans++;
//             }
//         }

//         return ans;
    }

}

// 이건 효율성 구데기 ㅠㅠ
// public class Solution { 
//     static int ans = (int)1e9;
//     static int[] arr;
//     public int solution(int n) {
//         arr = new int[n + 1];

//         Arrays.fill(arr, ans);
                
//         func(n, 1, 1);
//         System.out.println(arr[n]);

//         return arr[n];
//     }

//     public static void func(int n, int now, int count) {
        
//         if(now > n || arr[now] < count) {
//             return;
//         }
            
//         arr[now] = count;

//         func(n, now + 1, count + 1);
//         func(n, now * 2, count);
//     }
// }
