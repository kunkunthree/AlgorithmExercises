package algorithm.coding.interviews;

import java.util.Arrays;

public class NO_44_ContinousCards {
	public static void main(String[] args) {
		int[] cards = new int[]{1,0,3,4,0,0,7,0};
		System.out.println(areContinousCards(cards));
	}
	public static boolean areContinousCards(int[] cards){
		Arrays.sort(cards);
		int zeroCount = 0;
		int i = 0;
		while(i < cards.length && cards[i] == 0){
			zeroCount++;
			i++;
		}
		int gapCount = 0;
		i++;
		while(i < cards.length){
			if( cards[i] == cards[i-1]){
				return false;
			}
			gapCount+=cards[i]-cards[i-1]-1;
			i++;
		}
		return zeroCount >= gapCount ? true : false; 
	}
}
