import java.util.*;

class Period implements Comparable<Period> {
    
    int diamond;
    int iron;
    int stone;
    
    public Period(int diamond, int iron, int stone) {
        this.diamond = diamond;
        this.iron = iron;
        this.stone = stone;
    }
    
    @Override
    public int compareTo(Period other) {
        int thisTotal = this.diamond * 25 + this.iron * 5 + this.stone * 1;
        int otherTotal = other.diamond * 25 + other.iron * 5 + other.stone * 1;
        
        // 내림차순
        return otherTotal - thisTotal;
    }
    
}

class Solution {
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        ArrayList<Period> periods = new ArrayList<>();
        int numOfPicks = picks[0] + picks[1] + picks[2];
        int check = 0;
        
        for (int i = 0; i < minerals.length; i += 5) {
            if (check >= numOfPicks) break;
            
            int countDia = 0;
            int countIron = 0;
            int countStone = 0;
            
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                
                switch(minerals[j]) {
                    case "diamond":
                        countDia++;
                        break;
                    case "iron":
                        countIron++;
                        break;
                    case "stone":
                        countStone++;
                        break;
                    default:
                        break;
                }
            }
            
            periods.add(new Period(countDia, countIron, countStone));
            check++;
        }
        
        Collections.sort(periods);
        
        for (int i = 0; i < periods.size(); i++) {
            Period period = periods.get(i);
            int diamond = period.diamond;
            int iron = period.iron;
            int stone = period.stone;
            
            // 곡괭이 찾기
            int pick = findPick(picks);
            
            if (pick == 0) {
                answer += diamond + iron + stone;
            } else if (pick == 1) {
                answer += diamond * 5 + iron + stone;
            } else if (pick == 2) {
                answer += diamond * 25 + iron * 5 + stone;
            } else {
                break;
            }
        }
        
        return answer;
    }
    
    public int findPick(int[] picks) {
        int pick = -1;
        
        if (picks[0] != 0) {
            picks[0] -= 1;
            pick = 0;

        } else if (picks[1] != 0) {
            picks[1] -= 1;
            pick = 1;
            
        } else if (picks[2] != 0) {
            picks[2] -= 1;
            pick = 2;
            
        }
        
        return pick;
    }
}
