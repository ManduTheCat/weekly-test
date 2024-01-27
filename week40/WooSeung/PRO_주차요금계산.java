// 00:00 ~ 23:59
// 출차내역이 없으면 23:59분에 출차된 것으로 간주한다.
// 단위 시간으로 나누어 떨어지지 않으면 올림한다.

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        HashMap<String, Integer> fMap = new HashMap<>();
        HashMap<String, Integer> tMap = new HashMap<>();
        for(int i = 0; i < records.length; i++){
            fMap.put(records[i].split(" ")[1], 0);
        }
        for(String str : records) {
            
            String[] arr = str.split(" ");
            StringTokenizer st = new StringTokenizer(arr[0], ":");
            int hour = Integer.parseInt(st.nextToken()) * 60;
            int min = Integer.parseInt(st.nextToken());
            int time = hour + min;

            if(tMap.containsKey(arr[1])) {
                int temp = tMap.get(arr[1]);
                int sum = fMap.get(arr[1]);
                tMap.remove(arr[1]);
                fMap.replace(arr[1], sum + (time - temp));
            }else {
                tMap.put(arr[1], time);
            }
        }
        
        if(!tMap.isEmpty()) {
            for(String str : tMap.keySet()) {
                int entTime = tMap.get(str);
                int sum = fMap.get(str);
                fMap.replace(str, sum + ((23 * 60 + 59) - entTime));
            }
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList(fMap.entrySet());
        Collections.sort(list, (o1, o2) -> {
            return Integer.parseInt(o1.getKey()) > Integer.parseInt(o2.getKey())?1 : 
            Integer.parseInt(o1.getKey()) < Integer.parseInt(o2.getKey())?-1 : 0;
        });
        
        int[] answer = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getValue() > fees[0]){
                answer[i] = fees[1] + (int) Math.ceil((list.get(i).getValue() - fees[0]) / (double)fees[2]) * fees[3];
            }else{
                answer[i] = fees[1];
            }
        }
        
        return answer;
    }
}
