import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_20920_영단어암기는괴로워_안수빈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>();
	
		// M글자 이상인 단어 맵에 넣기
		// 이미 존재한다면 value+1
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			if (word.length() < M) continue;
			
			int value = 0;
			if (map.containsKey(word)) value = map.get(word);
			map.put(word, value + 1);
		}
		
		List<String> keySet = new ArrayList<>(map.keySet());
		
		keySet.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// 1. 자주 나오는 단어일수록 앞에 배치
				int comp1 = map.get(o2).compareTo(map.get(o1));
				if (comp1 == 0) {
					// 2. 해당 단어의 길이가 길수록 앞에 배치
					int comp2 = Integer.compare(o2.length(), o1.length());
					if (comp2 == 0) {
						// 3. 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치
						return o1.compareTo(o2);
					}
					return comp2;
				}
				return comp1;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for (String key: keySet) {
			sb.append(key).append("\n");
		}
		
		System.out.print(sb);
	}
	
}
