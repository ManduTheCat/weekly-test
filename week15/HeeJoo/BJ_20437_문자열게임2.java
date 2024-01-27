package saturday.sat230225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1. 알파벳 소문자로 이루어진 문자열 W
2. 양의 정수 K
3. 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
4. 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
T회 진행
 */
public class BJ_20437_문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] array;
        int T = Integer.parseInt(br.readLine()); // 게임의 수
        for(int t = 0; t < T; t++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            List<List<Integer>> list = new ArrayList<>();
            for(int i = 0; i < 26; i++) list.add(new ArrayList<>());
            for (int i = 0; i < W.length(); i++) {
                char c = W.charAt(i);
                list.get(c - 'a').add(i);
            }

            List<Integer> indexList = new ArrayList<>();
            for(int i = 0; i < 26; i++){
                if(list.get(i).size() >= K) indexList.add(i);
            }

            if(indexList.isEmpty()) sb.append("-1\n");
            else{
                int min = (int)1e9;
                int max = -1;
                int size = 0;
                for (Integer integer : indexList) {
                    List<Integer> now = list.get(integer);
                    if(now.size() == K) {
                        size = now.get(K-1) - now.get(0);
                        min = Math.min(min, size);
                        max = Math.max(max, size);
                    } else {
                        for(int i = 0; i <= now.size() - K; i++){
                            size = now.get(i+K-1) - now.get(i);
                            min = Math.min(min, size);
                            max = Math.max(max, size);
                        }
                    }
                }
                sb.append((min + 1) + " " + (max + 1) + "\n");
            }
        }
        System.out.println(sb);

    }
}
