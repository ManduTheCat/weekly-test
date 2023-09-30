/*
 * 맨 처음은 A
 * 상 : 다음 알파벳
 * 하 : 이전 알파벳
 * 좌 : 커서 왼쪽 이동
 * 우 : 커서 오른쪽 이동
 * 주어진 문자열을 만드는데 필요한 조이스틱 조작 횟수 최솟값

 * 상/하 조작횟수는 고정
 * 문제는 좌/우를 어떻게 이동해야 최소 ?
 * 어차피 한번이라도 지나면 해당 위치의 문자는 바뀜... A가 아닌 문자들을 모두 한 번씩은 방문 해야 함
 * i번째 문자 이후 처음으로 A가 아닌 위치 next를 어떻게 가는지 비교 ...
 * i 찍고 next or next 찍고 i
 */

class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int way = length - 1; // 좌우 조작 횟수. 최대값은 좌 -> 우
        
        for(int i = 0; i < length; i++) {
            char target = name.charAt(i);
            
            // 해당 알파벳을 만드는데 필요한 조이스틱 상/하 조작횟수
            answer += Math.min(target - 'A', 'Z' - target + 1);
            
            int next = i + 1; // i번째 문자 이후, 처음으로 A가 아닌 문자가 나오는 위치
            while(next < length && name.charAt(next) == 'A') {
                next++;
            }
            
            way = Math.min(way, i * 2 + length - next); // i번째까지 오른쪽 방향 + next까지 왼쪽 방향 ~ i(원점 > i) + i(i > 원점) + length - next(원점 > next)
            way = Math.min(way, (length - next) * 2 + i); // next까지 왼쪽 방향 + i번째까지 오른쪽방향 ~ length - next(원점 > next) + length - next(next > 원점) + i(원점 > i)
        }
        
        return answer + way;
    }
}
