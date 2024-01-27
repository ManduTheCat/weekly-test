package saturday.sat230624;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 자연수 N과 정수 K가 주어졌을 때, 이항 계수 (N K)를 1,000,000,007로 나눈 나머지를 구하는 프로그램
 * 이항 계수 = n! / k!(n-k)!
 *
 * 모듈러 연산 기본 성질에서 곱셈 분배 법칙은 성립하나, 나눗셈(분수) 연산은 성립 불가
 * 이항 계수의 분수 형식을 곱셈 꼴로 만들기 ~ 역원 (a / x = T = a * x^-1)
 *
 * 페르마의 소정리 : 정수 a, 소수 p일 때, a가 p로 나누어지지 않는 정수라면 a^(p-1) = 1 (mod p) / a^p = a (mod p)
 * nCr % p = (n!/r!(n-r)!) % p
 * n! = A, r!(n-r)! = B
 * 페르마의 소정리 적용 ~ B^(p-1) = 1 (mod p) ~ B^(p-2) = B^-1 (mod p)
 * (A * B^-1) % p = (A * B^p-2) % p
 */
public class BJ_11401_이항계수3 {
    static final long MOD = (long)1e9 + 7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        long N = Integer.parseInt(input[0]);
        long K = Integer.parseInt(input[1]);

        long numer = facto(N); // 분자 N!
        long denom = facto(K) * facto(N - K) % MOD; // 분모 (K! * (N-K)!) % MOD
        System.out.println(numer * pow(denom, MOD - 2) % MOD); // 분자 * 분모의 역 ~ (A * B^p-2) % p
    }

    public static long facto(long n) {
        if(n == 0L || n == 1L) {
            return 1L;
        } else {
            return n * facto(n - 1L) % MOD;
        }
    }

    // 분할 정복을 이용하여 제곱 구하기
    public static long pow(long base, long expo) { // 밑, 지수
        // 지수가 1인 경우, base^1 = base
        if(expo == 1) {
            return base % MOD;
        }

        // 분할 ... 지수의 절반에 해당하는 base^(expo/2)
        long temp = pow(base, expo / 2);

        // 현재 지수가 홀수인 경우
        // base^(expo/2) * base^(expo/2) * base
        // ex) A^9 = A^4 * A^4 * A
        if(expo % 2 == 1) {
            return (temp * temp % MOD) * base % MOD;
        }

        return temp * temp % MOD;
    }
}
