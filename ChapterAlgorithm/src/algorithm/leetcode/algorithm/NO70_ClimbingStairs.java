package algorithm.leetcode.algorithm;
/*
 * easy
 * 70. Climbing Stairs
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?  
 */
import java.util.*;
public class NO70_ClimbingStairs {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt();
			System.out.println(climbStairs2(n));
		}
	}
    public static int climbStairs(int n) {
        int sum = 0,tmp1,tmp2;
        int i = n;
        while(i >= (n+1)/2){
            tmp1 = 1;
            tmp2 = 1;
            System.out.println(i + " " + (n-i));
            int m = n-i;
            if(m > i/2){
            	m = i - ( n - i);
            }
            for(int j = n-i,k=0; j >= 1 ; j--,k++){
                if(tmp1 % j != 0){
                	tmp2*=j;
                }
                if(tmp2 % i-k != 0){
                	tmp1*=(i-k);
                }
            }
            System.out.println("C("+i+","+(n-i)+") = " + tmp1 + "/" + tmp2 + " = " + tmp1/tmp2);
            sum+=tmp1/tmp2;
            i--;
        }
        return sum;
    }
    public static HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    public static int climbStairs2(int n) {
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }
        int sum = 0;
        int tmp = 0;
        if(map.containsKey(n-2)){
            tmp = map.get(n-2);
        }else{
            tmp = climbStairs2(n-2);
            map.put(n-2,tmp);
        }
        sum+=tmp;
        if(map.containsKey(n-1)){
            tmp = map.get(n-1);
        }else{
            tmp = climbStairs2(n-1);
            map.put(n-1,tmp);
        }
        sum+=tmp;
        return sum;
    }
    //利用fibonacci数列
    public int climbStairs3(int n) {
        // base cases
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;
        
        int one_step_before = 2;
        int two_steps_before = 1;
        int all_ways = 0;
        
        for(int i=2; i<n; i++){
        	all_ways = one_step_before + two_steps_before;
        	two_steps_before = one_step_before;
            one_step_before = all_ways;
        }
        return all_ways;
    }
}
