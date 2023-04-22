class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		int avail = 0;
		
		int deleveryCnt = 0;
		int pickupsCnt = 0;

		for (int delivery: deliveries) deleveryCnt += delivery;
		for (int pickup: pickups) pickupsCnt += pickup;

        while (n > 0 && deliveries[n - 1] == 0 && pickups[n - 1] == 0) --n;
        
		while (n > 0) {
			int d = 0;
			int p = 0;
			
			for (int i = n - 1; i >= 0; i--) {
				avail = Math.min(deliveries[i], cap - d);
				d += avail;
				deliveries[i] -= avail;
				deleveryCnt -= avail;

				avail = Math.min(pickups[i], cap - p);
				p += avail;
				pickups[i] -= avail;
				pickupsCnt -= avail;
                
				if ((deleveryCnt == 0 || d >= cap) && (pickupsCnt == 0 || p >= cap)) break;
			}

			answer += (n * 2);
            while (n > 0 && deliveries[n - 1] == 0 && pickups[n - 1] == 0) --n;
		}
		
		return answer;
    }
}
