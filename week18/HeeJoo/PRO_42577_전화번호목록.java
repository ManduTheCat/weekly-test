package saturday.sat230318;

import java.util.Arrays;
import java.util.HashMap;

/*
 * 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인
 * ex) 119,, 11 9552 4421 -> 119는 접두어
 * phone_book : 전화번호부
 * 어떤 번호가 다른 번호의 접두어인 경우 false, 그렇지 않으면 true return
 */
/*
 * solution과 solution2의 시간 효율 차이 매우 많이 남 ...
 * 데이터가 많아질수록 급격한 차이 증가(7.64ms / 161.24ms)
 * 데이터가 적어도 10배 이상 차이
 * solution2는 효율성 테스트에서 4개 중 2개 실패
 */
public class PRO_42577_전화번호목록 {
    public static void main(String[] args) {
        String[] phone_book = new String[]{"119", "97674223", "1195524421"};

        System.out.println(solution(phone_book));
    }

    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        HashMap<String, Integer> hm = new HashMap();
        int index = 0;
        for (String s : phone_book) {
            hm.put(s, index++);
        }

        for (String phone : phone_book) {
            for (int idx = 1; idx < phone.length(); idx++) {
                if(hm.containsKey(phone.substring(0, idx))) return false;
            }
        }


        return answer;
    }

    public static boolean solution2(String[] phone_book) {
        Arrays.sort(phone_book, (a, b) -> a.length() - b.length());
        for(int i = 0; i < phone_book.length - 1; i++) {
            int size = phone_book[i].length();
            for(int j = i + 1; j < phone_book.length; j++) {
                if(phone_book[j].substring(0, size).equals(phone_book[i])) return false;
            }
        }
        return true;
    }


}
