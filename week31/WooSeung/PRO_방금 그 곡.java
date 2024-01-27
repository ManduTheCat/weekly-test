import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxPlayTime = 0; // 재생 시간
        
        m = transform(m); // m의 악보 변환

        for (String musicinfo : musicinfos) {
            String[] info = musicinfo.split(",");
            
            // 시작과 종료 시간 계산
            int startTime = Integer.parseInt(info[0].split(":")[0]) * 60 + Integer.parseInt(info[0].split(":")[1]);
            int endTime = Integer.parseInt(info[1].split(":")[0]) * 60 + Integer.parseInt(info[1].split(":")[1]);
            
            int playTime = endTime - startTime;
            
            // 악보 변환
            String melody = transform(info[3]);
            
            StringBuilder playedMelody = new StringBuilder();

            // 재생된 악보 생성
            for (int i = 0; i < playTime; i++) {
                playedMelody.append(melody.charAt(i % melody.length()));
            }
            
            // 재생된 악보 안에 m이 있는지 확인
            if (playedMelody.toString().contains(m) && playTime > maxPlayTime) {
                maxPlayTime = playTime;
                answer = info[2];
            }
        }
        
        return answer;
    }

    // C#과 같은 음표들을 별도의 단일 문자로 변환하는 메서드
    public String transform(String m) {
        m = m.replace("C#", "c");
        m = m.replace("D#", "d");
        m = m.replace("F#", "f");
        m = m.replace("G#", "g");
        m = m.replace("A#", "a");
        return m;
    }
}
