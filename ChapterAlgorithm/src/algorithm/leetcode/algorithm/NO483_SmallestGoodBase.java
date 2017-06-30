package algorithm.leetcode.algorithm;
/*
 * hard
 * 483. Smallest Good Base 
 * For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.

Now given a string representing n, you should return the smallest good base of n in string format.

Example 1:
Input: "13"
Output: "3"
Explanation: 13 base 3 is 111.

Example 2:
Input: "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.

Example 3:
Input: "1000000000000000000"
Output: "999999999999999999"
Explanation: 1000000000000000000 base 999999999999999999 is 11.

Note:
    1.		The range of n is [3, 10^18].
    2.		The string representing n is always valid and will not have leading zeros.

 */
public class NO483_SmallestGoodBase {
	public static void main(String[] args) {
	}
	//方法1：
	//如果我们用k表示基数，m表示转为全1数字的位数，那么数字n就可以拆分为：
	//n = 1 + k + k^2 + k^3 + ... + k^(m-1)
	//利用求和公式可以表示为 n = (k^m - 1) / (k - 1)
	//要想k最小，那么就是要m最大，即等式越长越好
	//k>=2,n>=3,那么至少有2项，即m>=2
	//当k取最小的2时，m最大，m<=log(n + 1)/log(2)
	//由不等式 n = 1 + k + k^2 + k^3 + ... + k^(m-1) > k^(m-1)
	//解出k < n^(1 / (m-1)) ,得到k的上限
	//所以  2<=m<=log(n + 1)/log(2) , 2<=k<n^(1 / (m-1))
	
	//我们遍历所有可能的m值，然后利用二分查找法来确定k的值，
	//对每一个k值，我们通过联合m值算出总和sum，然后跟n来对比即可
	public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        for(int i = (int)(Math.log(num+1)/Math.log(2)) ; i >= 2 ; i--){
            long left = 2,right = (int)Math.pow(num,1.0/(i-1)) + 1,mid;
            while(left < right){
                mid = left + (right - left)/2;
                long sum = 0;
                for(int j = 0 ; j < i ; j++){
                    sum = sum * mid + 1;
                }
                if(sum == num){
                    return String.valueOf(mid);
                }else if(sum < num){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }
        }
        return String.valueOf(num-1);
    }
}
