import java.util.*;

class Solution {
    static String[] words = {"A", "E", "I", "O", "U"};
    static List<String> list;
    public int solution(String word) {
        int answer = 0;
        
        list = new ArrayList<>();
        makeList("", 0);
        
        return list.indexOf(word) + 1;
    }
    
    // 모음 사전 만들기
    public void makeList(String post, int depth) {
        if(depth == 5) {
            return;
        }
        
        for(int i = 0; i < words.length; i++) {
            list.add(post + words[i]);
            makeList(post + words[i], depth + 1);
        }
    }
    
    // 경우의 수 점화식
	// public int solution(String word) {
	// 	String str = "AEIOU";
	// 	int[] x = {781,156,31,6,1}; // (이전 증가율 * 5) + 1
	// 	int index,result=word.length();
	// 	for(int i=0;i<word.length();i++){
	// 		index = str.indexOf(word.charAt(i));
	// 		result+=x[i]*index;
	// 	}
	// 	return result;
	// }
    
    // 위 내용 압축
    // public int solution(String word) {
    //     int answer = 0, per = 3905; // 781 * 5 = 3905
    //     // per : 3905 -> 781 -> 156 ... 회차에 따라 감소.
    //     for(String s : word.split("")) answer += "AEIOU".indexOf(s) * (per /= 5) + 1;
    //     return answer;
    // }
}
