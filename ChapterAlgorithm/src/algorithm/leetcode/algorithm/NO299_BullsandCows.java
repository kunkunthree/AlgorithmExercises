package algorithm.leetcode.algorithm;
/*
 * medium
 * 299. Bulls and Cows
 * You are playing the following Bulls and Cows game with your friend: 
 * You write down a number and ask your friend to guess what the number is. 
 * Each time your friend makes a guess, you provide a hint 
 * that indicates how many digits in said guess match your secret number exactly in both digit and position
 *  (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). 
 *  Your friend will use successive guesses and hints to eventually derive the secret number.

For example:

Secret number:  "1807"
Friend's guess: "7810"

Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)

Write a function to return a hint according to the secret number and friend's guess, 
use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".

Please note that both secret number and friend's guess may contain duplicate digits, for example:

Secret number:  "1123"
Friend's guess: "0111"

In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".

You may assume that the secret number and your friend's guess only contain digits, 
and their lengths are always equal.
 */
public class NO299_BullsandCows {
	//方法1：
	//O(n)time,O(1)space
	public String getHint(String secret, String guess) {
        int[] secretCount = new int[10];
        int[] guessCount = new int[10];
        char sc,gc;
        int length = secret.length(),countA = 0,countB = 0;
        for(int i = 0 ; i < length ; i++){
            sc = secret.charAt(i);
            gc = guess.charAt(i);
            if(sc != gc){
                secretCount[sc-'0']++;
                guessCount[gc-'0']++;
            }else{
                countA++;
            }
        }
        for(int i = 0 ; i < 10 ; i++){
            countB+=Math.min(secretCount[i],guessCount[i]);
        }
        return countA+"A"+countB+"B";
    }
	//方法2：
	//比方法1更简洁，只用1个数组存储比较
	public String getHint2(String secret, String guess) {
	    int bulls = 0;
	    int cows = 0;
	    int[] numbers = new int[10];
	    for (int i = 0; i<secret.length(); i++) {
	        int s = Character.getNumericValue(secret.charAt(i));
	        int g = Character.getNumericValue(guess.charAt(i));
	        if (s == g) bulls++;
	        else {
	            if (numbers[s] < 0) cows++;
	            if (numbers[g] > 0) cows++;
	            numbers[s] ++;
	            numbers[g] --;
	        }
	    }
	    return bulls + "A" + cows + "B";
	}
}
