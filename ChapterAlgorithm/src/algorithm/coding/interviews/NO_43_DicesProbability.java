package algorithm.coding.interviews;

public class NO_43_DicesProbability {
	public static void main(String[] args) {
		diceProbability2(2);
	}
	private static int maxValue = 6;
	public static void diceProbability(int number){
		if(number <= 0){
			return;
		}
		int maxSum = number * maxValue;
		double total = Math.pow(maxValue, number);
		int[] probability = new int[maxSum - number + 1];
		countProbability(number, probability, number, 0);
		
		for(int i = number ; i <= maxSum ; i++){
			System.out.println(String.format("%d: %e", i , probability[i-number]/(double)total));
		}
	}
	private static void countProbability(int number,int[] probability,int time,int sum){
		if(time == 0){
			probability[sum-number]++;
			return;
		}
		for(int i = 1 ; i <= maxValue ; i++){
			countProbability(number, probability, time-1, i+sum);
		}
	}
	
	public static void diceProbability2(int number){
		int maxSum = number * maxValue;
		int[][] probability = new int[2][maxSum+1];
		for(int i = 1 ; i <= maxValue ; i++){
			probability[0][i] = 1;
		}
		int flag = 0;
		for(int k = 2 ; k <= number ; k++){
			for(int i = 0 ; i < k ; i ++){
				probability[1-flag][i] = 0;
			}
			for(int i = k ; i <= k*maxValue ; i++){
				probability[1-flag][i] = 0;
				for(int j = 1 ; j <= i && j <= maxValue ; j++){
					probability[1-flag][i] += probability[flag][i-j];
				}
			}
			flag = 1 - flag;
		}
		double total = Math.pow(maxValue, number);
		for(int i = number ; i <= maxSum ; i++){
			System.out.println(String.format("%d: %e", i , probability[flag][i]/(double)total));
		}
	}
}
