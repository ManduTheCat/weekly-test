import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2383_점심식사시간 {
	
	static class Node {
		int row;
		int col;
		int number;
		
		public Node(int row, int col, int number) {
			this.row = row;
			this.col = col;
			this.number = number;
		}
	}
	
	static int T, N, NumOfPerson, MinTime;
	static boolean[] Selected;
	static ArrayList<Node> Persons;
	static ArrayList<Node> Exits;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			Persons = new ArrayList<Node>();
			Exits = new ArrayList<Node>();
			
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					int number = Integer.parseInt(st.nextToken());
					if (number == 0) {
						continue;
					} else if (number == 1) {
						Persons.add(new Node(row, col, number));
					} else {
						Exits.add(new Node(row, col, number));
					}
				}
			}
			
			NumOfPerson = Persons.size();
			MinTime = Integer.MAX_VALUE;
			
			for (int p = 0; p <= NumOfPerson; p++) {
				Selected = new boolean[NumOfPerson];
				combination(p, 0, 0);
			}
			
			
			sb.append("#" + tc + " " + MinTime + "\n");
		}
		
		System.out.println(sb.toString());
		
	}

	private static void combination(int target, int depth, int start) {
		if (depth == target) {
			ArrayList<Integer> teamA = new ArrayList<Integer>();
			ArrayList<Integer> teamB = new ArrayList<Integer>();
			
			for (int i = 0; i < NumOfPerson; i++) {
				int distance = 0;
				
				if (Selected[i]) {
					distance = calDistance(0, i);
					teamA.add(distance);
				} else {
					distance = calDistance(1, i);
					teamB.add(distance);
				}
			}
			
			Collections.sort(teamA);
			Collections.sort(teamB);
			
//			System.out.println();
//			
//			//DEBUG
//			System.out.print("Team A: ");
//			for (Integer i : teamA) {
//				System.out.print(i + " ");
//			}
//			int timeA = calTime(0, teamA);
//			System.out.print("Time( " + timeA + ")");
//			System.out.println();
//			
//			//DEBUG
//			System.out.print("Team B: ");
//			for (Integer i : teamB) {
//				System.out.print(i + " ");
//			}
//			int timeB = calTime(1, teamB);
//			System.out.print("Time( " + timeB + ")");
//			System.out.println();
			
			
			int timeA = calTime(0, teamA);
			int timeB = calTime(1, teamB);
			int localMinTime = Math.max(timeA, timeB);
			
			MinTime = Math.min(localMinTime, MinTime);
			
//			System.out.println();
			return;
		}
		
		for (int i = start; i < NumOfPerson; i++) {
			if (!Selected[i]) {
				Selected[i] = true;
				combination(target, depth+1, i+1);
				Selected[i] = false;
			}
		}
	}

	private static int calTime(int numOfExit, ArrayList<Integer> team) {
		Node exit = Exits.get(numOfExit);
		Queue<Integer> waitQ = new LinkedList<>();
		Queue<Integer> runQ = new LinkedList<>();
		
		for (Integer arrival : team) {
			waitQ.add(arrival);
		}
		
		int tick = 0;
		int finish = 0;
		int total = team.size();
		while (true) {
			
			if (runQ.size() > 0) {
				while (true) {
					if (runQ.size() <= 0) break;
					
					if (runQ.peek() <= tick) {
						runQ.poll();
						finish++;
					} else {
						break;
					}
				}
			}
			
			
			if (finish == total) break;
			
			
			if (waitQ.size() > 0) {
				while(true) {
					if (waitQ.size() <= 0) break;
					if (runQ.size() >= 3) break;
					if (waitQ.peek() > tick) break;
					
					int arriveTime = waitQ.poll();
					if (arriveTime == tick) {
						runQ.add(arriveTime + exit.number + 1);
					} else if (arriveTime < tick) {
						runQ.add(tick + exit.number);
					}
				}
			}
			
			tick++;
		}
		
		
		return tick;
	}

	private static int calDistance(int numOfExit, int i) {
		Node person = Persons.get(i);
		Node exit = Exits.get(numOfExit);
		return Math.abs(person.row - exit.row) + Math.abs(person.col - exit.col);
	}

}
