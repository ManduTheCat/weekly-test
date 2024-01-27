// https://ws-pace.tistory.com/83

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_20437_문자열게임2 {
	
	static int T, K, max, min;
	static String W;
	static ArrayList<Integer>[] alpha;
	
	static final int ALPHABET = 26;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			
			init();
			
			for (int i = 0; i < W.length(); i++) {
				alpha[W.charAt(i) - 'a'].add(i);
			}

			for (int i = 0; i < ALPHABET; i++) {
				if (alpha[i].size() >= K) {
					twoPointer(i, alpha[i]);
				}
			}
			
			if (max == 0) sb.append("-1\n");
			else sb.append(min).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void init() {			
		alpha = new ArrayList[ALPHABET];
		for (int i = 0; i < ALPHABET; i++) {
			alpha[i] = new ArrayList<>();
		}
		
		max = 0;
		min = Integer.MAX_VALUE;
	}

	private static void twoPointer(int i, ArrayList<Integer> list) {
		int left = 0;
		int right = 0;
		
		while (right < list.size()) {
			if (right - left + 1 == K) {
				max = Math.max(max, list.get(right) - list.get(left) + 1);
				min = Math.min(min, list.get(right) - list.get(left) + 1);
			}
			
			++right;
			while (right - left + 1 > K) ++left;
		}
	}
	
}
