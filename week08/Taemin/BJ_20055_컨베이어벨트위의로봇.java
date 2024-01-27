import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어벨트위의로봇 {
	
	static class Conveyor {
		int life;
		boolean robot;
		
		public Conveyor(int life, boolean robot) {
			this.life = life;
			this.robot = robot;
		}
	}
	
	static int N, K, Count, Level;
	static Conveyor[] Belt;
	static ArrayDeque<Integer> First, Second;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Belt = new Conveyor[2*N+1];
		First = new ArrayDeque<>();
		Second = new ArrayDeque<>();
		
		// 초기화
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 2*N; i++) {
			Belt[i] = new Conveyor(Integer.parseInt(st.nextToken()), false);
		}
		
		for (int i = 1; i <= N; i++) {
			First.addLast(i);
		}
		
		for (int i = N + 1; i <= 2*N; i++) {
			Second.addFirst(i);
		}
		
		Count = 0;
		Level = 0;
		while (true) {
			if (Count >= K) break;
			Level++;
			
			// 1. 회전 
			
			//Belt[First.peekLast()].robot = false;
			int firstOut = First.pollLast();
			Second.addLast(firstOut);
			int secondOut = Second.pollFirst();
			First.addFirst(secondOut);
			
			// 2. 로봇 이동 
			int front = First.peekFirst();
			int last = First.peekLast();
			
			int cur = last;
			while (true) {
				if (cur == front) {
					
					if (Belt[cur].robot) {
						
						if (cur == 2*N) {
							if (Belt[1].life > 0 && !Belt[1].robot) {
								Belt[cur].robot = false;
								Belt[1].robot = true;
								Belt[1].life = Belt[1].life - 1;
								if (Belt[1].life == 0) {
									Count++;
								}
							}
						}
						else {
							if (Belt[cur+1].life > 0 && !Belt[cur+1].robot) {
								Belt[cur].robot = false;
								Belt[cur+1].robot = true;
								Belt[cur+1].life = Belt[cur+1].life - 1;
								if (Belt[cur+1].life == 0) {
									Count++;
								}
							}
						}
					}
					
					break;
				}
				else {
					if (cur == last) {
						Belt[cur].robot = false;
						if (cur == 1) {
							cur = 2*N;
						}
						else {
							cur--;
						}
					}
					else {
						if (cur == 2*N) {
							if (Belt[cur].robot) {
								if (Belt[1].life > 0 && !Belt[1].robot) {
									Belt[cur].robot = false;
									Belt[1].robot = true;
									Belt[1].life = Belt[1].life - 1;
									if (Belt[1].life == 0) {
										Count++;
									}
								}								
							}
							
							cur--;
						}
						else {
							if (Belt[cur].robot) {
								if (Belt[cur+1].life > 0 && !Belt[cur+1].robot) {
									Belt[cur].robot = false;
									Belt[cur+1].robot = true;
									Belt[cur+1].life = Belt[cur+1].life -1;
									if (Belt[cur+1].life == 0) {
										Count++;
									}
								}								
							}
							
							if (cur == 1) {
								cur=2*N;
							}
							else {
								cur--;								
							}
						}
					}
				}
			}
			Belt[First.peekLast()].robot = false;
			
			
			// 3
			if (!Belt[First.peekFirst()].robot && Belt[First.peekFirst()].life > 0) {
				Belt[First.peekFirst()].robot = true;
				Belt[First.peekFirst()].life -= 1;
				
				if (Belt[First.peekFirst()].life == 0) {
					Count++;
				}
			}
			

			// 4
			
		}
		
		System.out.println(Level);
	}

}
