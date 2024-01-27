import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 이항계수
        // 모듈러 연산의 기본 성질에서 나누기는 분배 법칙을 사용할 수 없다
        // (a + b)^n 꼴의 다항식을 전개할때 a^r * b^(n-r) 의 계수를 의미한다
        // (n r) = n! / r! * (n-r)!
        // (n r) = n! * (r!*(n-r)!)^-1


        // 페르마의 소정리
        // p가 소수이면, 모든 정수 a에 대해 a^p = a % p
        // p가 소수이고 a가 p의 배수가 아니면 a^(p-1) = 1
        // a * a^(p-2) = 1
        // a * x = 1
        // a의 역원 x는 a^(p-2)
        // a^-1 = a^(p-2)

        // (n r) = n! * (r!*(n-r!))^-1
        // (n r) = n! * (r!*(n-r!))^(1_000_000_007 - 2)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long mod = 1_000_000_007L;

        long numerator = factorial(n, mod) % mod;
        long denominator = factorial(k, mod) * factorial(n-k, mod) % mod;
        denominator = pow(denominator, mod - 2, mod);

        System.out.println(numerator * denominator % mod);
    }

    public static long pow(long denominator, long expo, long mod) {
        if (expo == 1) {
            return denominator % mod;
        }

        long half = pow(denominator, expo / 2, mod);
        if (expo % 2 == 1) {
            return half * half % mod * denominator % mod;
        }

        return half * half % mod;
    }

    public static long factorial(long n, long mod) {
        if (n == 0 || n == 1) return 1;

        return n * factorial(n-1, mod) % mod;
    }
}
