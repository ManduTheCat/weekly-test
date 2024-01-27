import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for (int i = 0; i < s.length; i++) {
            char[] array = s[i].toCharArray();
            Stack<Character> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            int count = 0;
            
            for (int j = 0; j < array.length; j++) {
                char z = array[j];
                
                if (stack.size() >= 2) {
                    char y = stack.pop();
                    char x = stack.pop();
                    
                    if (x == '1' && y == '1' && z == '0') {
                        count++;
                    } else {
                        stack.push(x);
                        stack.push(y);
                        stack.push(z);
                    }
                    
                } else {
                    stack.push(z);
                }
            }
            
            if (count == 0) {
                answer[i] = String.valueOf(array);
            } else {
                Stack<Character> reverse = new Stack<Character>();
                boolean flag = false;
                
                while (!stack.isEmpty()) {
                    Character ch = stack.pop();
                    if (!flag && ch == '0') flag = true;
                    reverse.push(ch);
                }
                
                while(!reverse.isEmpty()) {
                    sb.append(reverse.pop());
                }
                
                String remain = sb.toString();
                sb.delete(0, sb.length());
                
                if (!flag) {
                    for (int j = 0; j < count; j++) {
                        sb.append("110");
                    }
                    sb.append(remain);
                    answer[i] = sb.toString();
                } else {
                    char[] remainArray = remain.toCharArray();
                    int index = 0;
                    
                    for (int j = 0; j < remainArray.length; j++) {
                        if (remainArray[j] == '0') {
                            index = j;
                        }    
                    }
                    
                    for (int j = 0; j < remainArray.length; j++) {
                        if (j == index) {
                            sb.append(remainArray[j]);
                            
                            for (int k = 0; k < count; k++) {
                                sb.append("110");
                            }
                        } else {
                            sb.append(remainArray[j]);
                        }
                    }
                    answer[i] = sb.toString();
                }
            }
        }
        
        return answer;
    }
}
