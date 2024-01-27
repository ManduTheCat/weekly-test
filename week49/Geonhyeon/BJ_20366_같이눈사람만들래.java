package study_2024_01_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 배열 정렬
 * 최소 4개 0 ,1 , n-2, n-1 인덱스시작. Aleft,Bleft,Bright,Aright.
 * A가 더 크다면? Bright-- or Aleft++
 * B가 더 크다면? Bleft++ or Aright--
 *
 * 두번째 방법
 * 600개니까
 * 1. 모든 눈사람 조합 -> 600*600 = 3600
 * 2. 정렬
 * 3. 자신의 바로뒤에있는 것과의 차이 구하기(연산 최대 600번)
 */
public class BJ_20366_같이눈사람만들래 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] snow = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(snow);
        int min = Integer.MAX_VALUE;
        Snowman[] snowman = new Snowman[calc(n)];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                snowman[idx++] = new Snowman(i, j, snow[i] + snow[j]); // snowman = 눈덩이 두 개.
            }
        }

        Arrays.sort(snowman, (a, b) -> (a.size - b.size));
        for (int i = 0; i < idx-1; i++) {
            Snowman snowmanA = snowman[i];
            Snowman snowmanB = snowman[i+1];
                if (!snowmanA.isSameSnow(snowmanB)) {
                    min = Math.min(min, Math.abs(snowmanB.size - snowmanA.size));
                }
            }
        System.out.println(min);
    }


    private static int calc(int n){
        int sum = 0;
        for(int i=n;i>1;i--){
            sum+=(i-1);
        }
        return sum;
    }
}

class Snowman {
    int a;
    int b;
    int size;

    public Snowman(int a, int b, int size) {
        this.a = a;
        this.b = b;
        this.size = size;
    }

    public boolean isSameSnow(Snowman tmp) {
        if (this.a == tmp.a || this.a == tmp.b || this.b == tmp.a || this.b == tmp.b) return true;
        return false;
    }
}
