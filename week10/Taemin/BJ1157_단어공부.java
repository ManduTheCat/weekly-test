import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1157_단어공부 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        int[] counts = new int[26];

        for (char letter : word) {

            int letterToInt = (int) letter;
            letterToInt = (letterToInt >= 97)
                            ? letterToInt - 97 : letterToInt - 65;

            counts[letterToInt]++;
        }

        int maxCount = -1;
        int maxLetter = -1;

        for (int i = 0; i < counts.length; i++) {

            if (counts[i] > maxCount && counts[i] > 0) {
                maxCount = counts[i];
                maxLetter = i;
            }
            else if (counts[i] == maxCount) {
                maxLetter = -1;
            }
        }

        if (maxLetter == -1) {
            System.out.println("?");
        } else {
            System.out.println((char)(maxLetter + 65));
        }
    }
}
