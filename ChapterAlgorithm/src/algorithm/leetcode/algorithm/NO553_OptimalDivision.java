package algorithm.leetcode.algorithm;
/*
 * medium
 * 553. Optimal Division 
 * Given a list of positive integers, the adjacent integers will perform the float division. 
 * For example, [2,3,4] -> 2 / 3 / 4.

However, you can add any number of parenthesis at any position to change the priority of operations. 
You should find out how to add parenthesis to get the maximum result, and return the corresponding 
expression in string format. Your expression should NOT contain redundant parenthesis.

Example:

Input: [1000,100,10,2]
Output: "1000/(100/10/2)"
Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
since they don't influence the operation priority. So you should return "1000/(100/10/2)". 

Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2

Note:
    1.	The length of the input array is [1, 10].
    2.	Elements in the given array will be in range [2, 1000].
    3.	There is only one optimal division for each test case.

 */
public class NO553_OptimalDivision {
	//方法1：
	//遍历回溯，设计两个函数，得到结果最大的函数和得到最小的函数，
	//要让结果最大，只要把被除数得到最大，除数得到最小即可
	//同理，要让结果最小，只要把被除数得到最小，除数得到最大即可
	class Result{
        double val;
        String str;
    }
    public String optimalDivision(int[] nums) {
        return getMax(nums,0,nums.length-1).str;
    }
    private Result getMax(int[] nums,int start,int end){
        Result res = new Result();
        res.val = -1.0;
        
        if(start == end){
            res.str = nums[start] + "";
            res.val = (double)nums[start];
        }else if(start + 1 == end){
            res.str = nums[start] + "/" + nums[end];
            res.val = (double)nums[start]/(double)nums[end];
        }else if(start + 1 < end){
            for(int i = start ; i < end ; i++){
                Result left = getMax(nums,start,i);
                Result right = getMin(nums,i+1,end);
                if(left.val / right.val > res.val){
                    res.val = left.val / right.val;
                    res.str = left.str + "/" + (end - i >= 2 ? "(" + right.str + ")" : right.str); 
                }
            }
        }
        return res;
    }
    private Result getMin(int[] nums,int start,int end){
        Result res = new Result();
        res.val = Double.MAX_VALUE;
        
        if(start == end){
            res.str = nums[start] + "";
            res.val = (double)nums[start];
        }else if(start + 1 == end){
            res.str = nums[start] + "/" + nums[end];
            res.val = (double)nums[start]/(double)nums[end];
        }else if(start + 1 < end){
            for(int i = start ; i < end ; i++){
                Result left = getMin(nums,start,i);
                Result right = getMax(nums,i+1,end);
                if(left.val / right.val < res.val){
                    res.val = left.val / right.val;
                    res.str = left.str + "/" + (end - i >= 2 ? "(" + right.str + ")" : right.str); 
                }
            }
        }
        return res;
    }
    
    //方法2：
    //直接分析，因为X1/X2/X3..../Xn无论怎么加最终结果都会是 (X1/X2) * Y
    //要使得最终结果最大，即使得Y最大，那么只要Y=X3*X4*...*Xn就可以
    //即最终结果为X1/(X2/X3/.../Xn)
    public String optimalDivision2(int[] nums) {
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < nums.length ; i++){
            if(i == 0){
                result.append(nums[i]);
            }else if(i == 1 && nums.length > 2){
                result.append("/("+nums[i]);
            }else{
                result.append("/"+nums[i]);
            }
        }
        if(nums.length > 2){
            result.append(")");
        }
        return result.toString();
    }
}
