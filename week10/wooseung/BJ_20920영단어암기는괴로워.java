package baekjoon;

import java.io.*;
import java.util.*;

public class BJ_20920영단어암기는괴로워 {

    static class Word {
        String str;
        int count;

        public Word(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {

        // 자주 나오는 단어일 수록 앞으로
        // 나오는 횟수가 같으면 단어의 길이가 길수록 앞에
        // 길이도 같으면 앞에 알파벳 사전순

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Word> map = new HashMap<>();

        // M이상인 단어만
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            if(str.length() < M) {
                continue;
            }
            if(map.get(str) == null) {
                map.put(str, new Word(str, 1));
            }else {
                map.get(str).count++;
            }

        }

        List<Word> list = new ArrayList<>();
        for(String key : map.keySet()) {
            Word word = map.get(key);
            list.add(word);
        }
        list.sort(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if(o1.count == o2.count) { // 빈도수가 같으면
                    if(o1.str.length() == o2.str.length()) {   // 단어의 길이도 같으면 알파벳 사전순
                        return o1.str.compareTo(o2.str);
                    }
                    return o2.str.length() - o1.str.length();   // 길이가 긴게 앞으로
                }else {
                    return o2.count - o1.count;    // 빈도수가 높은게 앞으로
                }
            }
        });
        for(Word word : list) {
            bw.write(word.str+"\n");    // sout 사용 시 시간초과 ㅅㅂ
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
