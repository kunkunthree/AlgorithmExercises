package algorithm.leetcode.algorithm;
/*
 * easy
 * 202. Happy Number
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer, 
 * replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1
 *  (where it will stay), or it loops endlessly in a cycle which does not include 1. 
 *  Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number
    1^2 + 9^2 = 82
    8^2 + 2^2 = 68
    6^2 + 8^2 = 100
    1^2 + 0^2 + 0^2 = 1

similar problems:
258. Add Digits 
263. Ugly Number 
 */
import java.util.*;
public class NO202_HappyNumber {
	public static void main(String[] args) {
//		for(int i = 2 ; i < 100 ; i++){
//			System.out.println(i + "  " + isHappy(i));
//		}
		System.out.println(isHappy(2));
	}
	//方法1：
    public static boolean isHappy(int n) {
        if(n <= 0){
            return false;
        }
        Set<Integer> set = new HashSet<Integer>();
        int oldValue = n,newValue,count = 0;
        set.add(oldValue);
        int tmp;
        while(oldValue != 1){
            newValue = 0;
            tmp = oldValue;
            while(tmp != 0){
                newValue+=(tmp%10)*(tmp%10);
                tmp/=10;
            }
            if(oldValue == newValue){
                if(oldValue == 1){
                    return true;
                }else{
                    return false;
                }
            }
            if(set.contains(newValue)){
                return false;
            }
            set.add(newValue);
            oldValue = newValue;
            System.out.println(newValue);
        }
        if(oldValue == 1){
            return true;
        }else{
            return false;
        }
    }
    //方法2：
    //O(1)space
    //利用两个指针，一个快一个慢，当进入循环时，快指针和慢指针会相遇
    public static boolean isHappy2(int n) {
        int slow, fast;
        slow = fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while(slow != fast);
        if (slow == 1) return true;
        else return false;
    }
    public static int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

}
