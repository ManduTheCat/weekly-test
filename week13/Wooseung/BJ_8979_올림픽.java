package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_8979_올림픽 {

    static class Country implements Comparable<Country> {

        int id;
        int gold;
        int silver;
        int bronze;

        public Country(int id, int gold, int silver, int bronze) {
            this.id = id;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country o) {
            if(this.gold == o.gold) {
                if(this.silver == o.silver) {
                    return o.bronze - this.bronze;
                }else {
                    return o.silver - this.silver;
                }
            }else {
                return o.gold - this.gold;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Country> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int id = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver= Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            list.add(new Country(id, gold, silver, bronze));
        }

        Collections.sort(list);

        int rate = 1;
        int cnt = 1;

        if(list.get(0).id  == K) {
            System.out.println(1);
            return;
        }

        for(int i = 1; i < N; i++) {
            Country prev = list.get(i - 1);
            Country cur = list.get(i);

            if(prev.gold != cur.gold || prev.silver  != cur.silver || prev.bronze != cur.bronze) {
                rate += cnt;
                cnt = 1;
            }else {
                cnt++;
            }

            if(cur.id == K) {
                System.out.println(rate);
                break;
            }

        }
    }

}
