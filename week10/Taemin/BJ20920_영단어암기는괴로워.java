import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ20920_영단어암기는괴로워 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<ArrayList<String>> words = new ArrayList<ArrayList<String>>();
        for (int i = 0; i <= 10; i++) {

            words.add(new ArrayList<String>());
        }

        HashMap<String, Integer> counts = new HashMap<>();


        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {

            String word = br.readLine();
            if (word.length() < m) continue;

            if(!counts.containsKey(word)) {
                counts.put(word, 1);
            } else {
                counts.replace(word, counts.get(word)+1);
            }

            words.get(word.length()).add(word);
        }

        int max = 0;
        Iterator<String> keys = counts.keySet().iterator();
        while(keys.hasNext()) {

            String key = keys.next();
            int value = counts.get(key);
            max = (max < value) ? value : max;
        }



        for (int i = max; i >= 1; i--) {

            ArrayList<String> array = new ArrayList<>();
            for (String key : counts.keySet()) {
                int count = counts.get(key);
                if (count == i) array.add(key);
            }

            Collections.sort(array, new Comparator<String>(){
                @Override
                public int compare(String s1, String s2) {
                    if (s1.length() == s2.length()) {
                        char[] first = s1.toCharArray();
                        char[] second = s2.toCharArray();

                        for (int j = 0; j < s1.length(); j++) {
                            if (first[j] != second[j]) {
                                return Integer.compare((int)first[j], (int)second[j]);
                            }
                        }
                    }

                    return -Integer.compare(s1.length(), s2.length());
                }
            });

            for (String elem : array) {
                sb.append(elem + "\n");
            }
        }

        System.out.println(sb.toString());
    }
}
