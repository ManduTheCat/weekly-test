/*
 * 2진 트리 모양 ~ 각 노드에 양 or 늑대
 * 각 노드를 돌아다니며 양 모으기 ... 각 노드를 방문할 때 마다 해당 노드에 있는 양/늑대가 따라옴
 * 양의 수 <= 늑대 수 -> 모든 양 삭제
 * 중간에 잡아먹히지 않도록 하면서 최대한 많은 수의 양 모으기
 
 * 양(0), 늑대(1)
 * 루트 노드는 항상 양(0) 존재
 * edges = [부모 노드, 자식 노드]
 
 * if(양 <= 늑대) Math.max(answer, sheep)
 */

import java.util.*;

class Solution {
    static List<List<Integer>> graph = new ArrayList<>();
    static int answer = 0, maxSheep = 0;
    public int solution(int[] info, int[][] edges) {
        
        for(int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] arr : edges) {
            graph.get(arr[0]).add(arr[1]);
        }
        
        for(int i : info) {
            if(i == 0) {
                maxSheep++;
            }
        }
        
        func(0, 1, 0, info, graph.get(0));
        
        return answer;
    }
    
    // 현재 노드(now)에서 갈 수 있는 다음 노드들(nextNodes)
    // 이미 방문한 노드라면 해당 노드가 아닌 해당 노드의 하위 노드를 nextNodes에 추가하여 무한 루프 방지
    public void func(int now,int sheep, int wolf, int[] info, List<Integer> nextNodes) {
        
        if(sheep <= wolf) { // 불가능 조건
            answer = Math.max(answer, sheep - 1);
            return;
        }
        
        answer = Math.max(answer, sheep);

        for(int next : nextNodes) { // 다음 방문 노드
            List<Integer> list = new ArrayList<>(nextNodes); // 방문 가능 노드 갱신 ~ nextNodes를 가져와서 now 하위 노드 포함
            list.remove((Integer)next); // 본인(next) 방문 제외 ** Integer는 객체이기때문에 (Integer)next를 해야 객체 동일성 비교를 해서 next가 잘 제거됨
            // 만약 list.remove(next)를 하면 next가 index 취급돼서 이상하게 제거됨
            //
            list.addAll(graph.get(next)); // 본인(next)의 하위 노드 추가
            
            if(info[next] == 0) { // 양
                func(next, sheep + 1, wolf, info, list);
            }
            else { // 늑대
                func(next, sheep, wolf + 1, info, list);
            }
        }
    }
}
