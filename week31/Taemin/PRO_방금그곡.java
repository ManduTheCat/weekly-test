class Solution {
    public String solution(String m, String[] musicinfos) {
        // 제목
        String answer = "";
        
        // 재생 시간
        int maxTime = -1;
        
        // 멜로디 변환
        m = renewMusic(m);
        
        for (String musicinfo : musicinfos) {
            // 재생 시간 계산
            int time = calTime(musicinfo.split(",")[0], musicinfo.split(",")[1]);
            
            // 멜로디 변환
            String renewed = renewMusic(musicinfo.split(",")[3]);
            
            // 재생 시간 동안 들린 멜로디 저장
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < time; i++) {
                builder.append(renewed.charAt(i % renewed.length()));
            }
            renewed = builder.toString();
            
            // 재생된 멜로디 안에 기억한 멜로디가 있는지 확인 + 최장 시간 여부 확인
            if (renewed.contains(m) && time > maxTime) {
                answer = musicinfo.split(",")[2];
                maxTime = time;
            }
        }
        
        // 조건에 일치하는 음악이 없는 경우
        answer = (maxTime == -1) ? "(None)" : answer;
        
        return answer;
    }
    
    public int calTime(String startTime, String endTime) {
        int hour = Integer.parseInt(endTime.split(":")[0]) - 
                   Integer.parseInt(startTime.split(":")[0]);
        int min = Integer.parseInt(endTime.split(":")[1]) - 
                  Integer.parseInt(startTime.split(":")[1]);
                                                      
        return hour * 60 + min;         
    }
    
    public String renewMusic(String music) {
        music = music.replaceAll("A#", "H");
        music = music.replaceAll("C#", "I");
        music = music.replaceAll("D#", "J");
        music = music.replaceAll("F#", "K");
        String newMusic = music.replaceAll("G#", "L");
        
        return newMusic;
    }
}
