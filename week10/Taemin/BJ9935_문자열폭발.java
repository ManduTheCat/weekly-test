import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9935_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String word = br.readLine();
        String bomb = br.readLine();

        for (int i = 0; i < word.length(); i++) {

            char letter = word.charAt(i);
            sb.append(letter);

            if (sb.length() >= bomb.length()) {

                boolean flag = true;
                for (int j = 0; j < bomb.length(); j++) {
                    char sbLetter = sb.charAt(sb.length() - bomb.length() + j);
                    char bombLetter = bomb.charAt(j);
                    if (sbLetter != bombLetter) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    sb.delete(sb.length() - bomb.length(), sb.length());
                }
            }
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb.toString());
        }
    }
}
