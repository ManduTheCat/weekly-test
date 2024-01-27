package 새터데이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 0~9, 연산자(우선순위 동일) ~ 좌>우 계산
 * 괄호 추가 가능 but 중첩 괄호 xxx, 괄호 안에는 연산자 1개만
 * 수식이 주어졌을 때, 괄호를 적절히 추가해 만들 수 있는 식의 결과의 최댓값을 구하는 프로그램
 * 괄호 개수 제한 x, 추가하지 않아도 됨
 */

// 처음 max = 0으로 초기화해서 테스트 ~ 1-3 계산 오류 ~ 조건에 따라 max를 Integer.MIN_VALUE로 초기화
public class BJ_16637_괄호추가하기_문희주 {
	static int n, max = Integer.MIN_VALUE; // 조건 : 결과는 -2^31 보다 큼
	static String s;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // 수식의 길이 N(홀수)
		
		s = br.readLine(); // 수식
		
		DFS(1, s.charAt(0) - '0');
		System.out.println(max);
	}
	
	// index : 계산 위치
	// result : 지금까지 계산 결과
	public static void DFS(int index, int result) {
		if(index >= n - 1) {
			max = Math.max(max, result);
			
			return;
		}
		
		// 괄호를 추가할 수 있는 경우
		// index부터 최소 3개 존재해야 함
		// r 0  1 2 3  4 5
		// D o (D o D) o D
		if(index < n - 3) {
			DFS(index + 4, calc(result, s.charAt(index), calc(s.charAt(index+1)-'0', s.charAt(index+2), s.charAt(index+3)-'0'))); // 뒤 먼저 계산 ~ result로 calc(현재, 괄호)를 넘겨줌
		}
		DFS(index + 2, calc(result, s.charAt(index), s.charAt(index + 1)-'0')); // 앞부터 계산
	}
	
	public static int calc(int x, char y, int z) {
		if(y == '+') return x+z;
		if(y == '-') return x-z;
		if(y == '*') return x*z;
		
		return 0;
	}
}
