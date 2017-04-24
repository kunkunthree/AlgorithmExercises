package algorithm.leetcode.algorithm;
/*
 * easy
 * We are playing the Guess Game. The game is as follows:
I pick a number from 1 to n. You have to guess which number I picked.
Every time you guess wrong, I'll tell you whether the number is higher or lower.
You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!

Example:
n = 10, I pick 6.
Return 6.

 */
/* The guess API is defined in the parent class GuessGame.
@param num, your guess
@return -1 if my number is lower, 1 if my number is higher, otherwise return 0
   int guess(int num); */
//这里的My number不是参数，而是给定的数
public class NO374_GuessNumberHigherorLower {
	public static void main(String[] args) {
		System.out.println(guessNumber(10));
	}
	public static int ANSWER = 6;
	public static int guess(int n){
		if(n < ANSWER){
			return 1;
		}
		if(n > ANSWER){
			return -1;
		}
		return 0;
	}
    public static  int guessNumber(int n) {
        int left = 1,right = n,mid;
        int result;
        while(left < right){
            mid = left + (right - left) / 2;
            result = guess(mid);
            if(result < 0){
                right = mid;
            }else if(result > 0){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return left;
    }
}
