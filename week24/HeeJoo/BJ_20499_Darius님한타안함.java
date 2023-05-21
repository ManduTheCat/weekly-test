package saturday.sat230521;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * K + A < D | D == 0 : hasu
 * else : gosu
 */
public class BJ_20499_Darius님한타안함 {
    public static void main(String[] args) throws IOException {
        // K/D/A
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String kda = br.readLine();
        String[] string_arr = kda.split("/");

        int k = Integer.parseInt(string_arr[0]);
        int d = Integer.parseInt(string_arr[1]);
        int a = Integer.parseInt(string_arr[2]);

        if(k + a < d || d == 0) {
            System.out.println("hasu");
        } else {
            System.out.println("gosu");
        }
    }
}
