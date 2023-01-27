package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_5073_삼각형과세변 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int max = Math.max(a, Math.max(b, c));

            if(a == 0 && b == 0 && c == 0) {
                return;
            }



            if(a == b && b == c) {
                System.out.println("Equilateral");
            }else if(max >= a + b || max >= b + c || max >= c + a) {
                System.out.println("Invalid");
            }else if(a == b || b == c || a == c) {
                System.out.println("Isosceles");
            }else if(max < a + b && max < b + c && max < a + c) {
                System.out.println("Scalene");
            }

        }

    }

}
