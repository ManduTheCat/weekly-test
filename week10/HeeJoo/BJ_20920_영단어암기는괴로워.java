package saturday.sat230107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
 * 순서 1. 자주 나오는 단어일수록 앞에 배치
 * 순서 2. 해당 단어의 길이가 길수록 앞에 배치
 * 순서 3. 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치
 * 길이가 M 이상인 단어들만 출력하기 ~
 */


public class BJ_20920_영단어암기는괴로워 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 단어 개수 N
        int m = Integer.parseInt(st.nextToken()); // 단어 길이 M

        Map<String, Integer> map = new TreeMap<>();
        for(int i = 0; i < n; i++) {
            String key = br.readLine();

            if(key.length() < m) { // 길이 M 이상
                continue;
            }

            if(map.containsKey(key)) {
                map.replace(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        List<Map.Entry<String, Integer>> entries =
                map.entrySet().stream()
                        .sorted(new Comparator<Map.Entry<String, Integer>>() {
                            @Override
                            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                                if(o1.getValue() < o2.getValue()) { // 순서 1. 자주 나오는 단어 ~ value 기준 내림차순
                                    return 1;
                                } else if(o1.getValue() == o2.getValue()){
                                    if(o1.getKey().length() < o2.getKey().length()) { // 순서 2. 단어 길이가 긴 단어 ~ key 길이 기준 내림차순
                                        return 1;
                                    } else if(o1.getKey().length() == o2.getKey().length()) {
                                        if(o1.getKey().compareTo(o2.getKey()) > 0) { // 순서 3. 알파벳 사전 순 ~ key 기준 오름차순
                                            return 1;
                                        }
                                    }
                                }

                                return -1;
                            }
                        })
                        .collect(Collectors.toList());

        for(Map.Entry<String, Integer> entry : entries) {
            sb.append(entry.getKey() + "\n");
        }

        System.out.println(sb);
    }
}
