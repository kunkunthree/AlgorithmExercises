package algorithm.leetcode.algorithm;
/*
 * medium
 * 60. Permutation Sequence
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

    "123"
    "132"
    "213"
    "231"
    "312"
    "321"

Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 */
import java.util.*;
public class NO60_PermutationSequence {
	public static void main(String[] args) {
		int n = 5,k = 50;
		System.out.println(getPermutation(n, k));
	}
	//方法1：
	//经过修改的全排列算法，计算出口的当前序号，等于k则返回true
    public static String getPermutation(int n, int k) {
        if(n <= 0){
            return "";
        }
        int sum = 1;
        for(int i = 2 ; i <= n ; i++){
            sum*=i;
        }
        int[] nums = new int[n];
        for(int i = 0 ; i < n ; i++){
            nums[i] = i+1;
        }
        perm(nums,0,n,sum,0,k-1);
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            result.append(nums[i]);
        }
        return result.toString();
    }
    public static void moveTo(int[] nums,int i ,int j){
    	//move i to j
    	if(i < j){	//如果是从前往后移,则j及其前面直到第i+1项依次往前移一项，第i项移动到第j项
    		int tmp = nums[i];
    		while(i < j){
    			nums[i] = nums[i+1];
    			i++;
    		}
    		nums[j] = tmp;
    	}else if( i > j){	//如果是从后往前移,则j及其后面面直到第i-1项依次往后移一项，第i项移动到第j项
    		int tmp = nums[i];
    		while(i > j){
    			nums[i] = nums[i-1];
    			i--;
    		}
    		nums[j] = tmp;
    	}
    }
    public static boolean perm(int[] nums,int begin,int end,int sum,int cur,int k){
        if(begin == end){
            if(cur == k){
                return true;
            }else{
                return false;
            }
        }
        int nextSum = sum / (end-begin);
        for(int i = begin ; i < end ; i++){
        	moveTo(nums,i,begin);
            if(perm(nums,begin+1,end,nextSum,cur+nextSum*(i-begin),k) == true){
                return true;
            }
            moveTo(nums,begin,i);
        }
        return false;
    }
    
    //方法2：
    //[1,n]的升序排列中先将其分为n个范围，每个范围的长度为(n-1)!，每次计算首尾数字是剩余序列的第几位
    //依次类推每个(n-1)!长度范围内又可以分为n-1个长度为(n-2)!范围
    //假设n为5，k为50，{1,2,3,4,5}排列组合数为5!
    //先确定k在第几个范围内，(50-1)/(5-1)! = 2,第2+1个范围的第一位为3,其在第3个范围内的序号为k = 50-2*4! = 2.
    //将3从{1,2,3,4,5}中抽出得到{1,2,4,5}，排列组合数为4!,可以分割为4个范围，每个范围长度为3! = 6
    //(k-1)/(4-1)! = 0，k在第0+1个范围内，即把剩余序列中的第1个数1去除，得到{2,4,5},k = 2 - 0 * 3! = 2
    //(k-1)/(3-1)! = 0 , k在第0+1个范围内，即把剩余序列中的第1个数2去除，得到{4,5},k = 2 - 0 * 2! = 2
    //(k-1)/(2-1)! = 1，k在第1+1个范围内，即把剩余序列中的第2个数5去除，得到{4},k = 2 - 1 * 1! = 1
    //最后只剩下{4},即把4从序列中抽出
    //最后得到抽出的顺序{3,1,2,5,4}
    public String getPermutation2(int n, int k) {
        int pos = 0;
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();
        
        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum;
        }
        // factorial[] = {1, 1, 2, 6, 24, ... n!}
        
        // create a list of numbers to get indices
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }
        // numbers = {1, 2, 3, 4}
        k--;
        for(int i = 1; i <= n; i++){
            int index = k/factorial[n-i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k-=index*factorial[n-i];
        }
        return String.valueOf(sb);
    }
}
