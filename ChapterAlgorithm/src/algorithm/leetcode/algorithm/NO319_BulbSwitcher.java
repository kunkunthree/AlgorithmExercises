package algorithm.leetcode.algorithm;
/*
 * medium
 * 319. Bulb Switcher
 *  There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. 
 *  On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). 
 *  For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. 
 *  Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.

 */
public class NO319_BulbSwitcher {
//	A bulb ends up on iff it is switched an odd number of times.
//	Call them bulb 1 to bulb n. Bulb i is switched in round d if and only if d divides i. 
//	So bulb i ends up on if and only if it has an odd number of divisors.
//	Divisors come in pairs, like i=12 has divisors 1 and 12, 2 and 6, and 3 and 4. 
//	Except when i is a square, like 36 has divisors 1 and 36, 2 and 18, 3 and 12, 4 and 9,
//	and double divisor 6. So bulb i ends up on if and only if i is a square.
//	So just count the square numbers.
//	Let R = int(sqrt(n)). That's the root of the largest square in the range [1,n]. 
//	And 1 is the smallest root. So you have the roots from 1 to R, that's R roots. 
//	Which correspond to the R squares. So int(sqrt(n)) is the answer. 
//	(C++ does the conversion to int automatically, because of the specified return type).
	//直接分析，因为一个灯泡只有在开关奇数次后才会开着，而一个灯泡i的开关次数的奇偶性由i的因子数决定，
	//一般来说因子都是成对出现的，如12的因子有，1和12，2和6,3和4，两个因子相乘等于该数，n = i * j
	//而当且仅当这个数为平方数时，它的因子数才会时奇数，此时n = i * i
	//例如16的因子有，1和16,2和8,还有4
	//所以决定n个灯泡在第n轮时亮着的灯泡数取决于小于等于n的平方数有多少个，也就是求n的开根号取整
	
	//方法1：
	//调用Math库自带的sqrt函数
	public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
	//方法2：
	//手动二分法求平方根
	public int bulbSwitch2(int n) {
        int left = 1;
        int right = n/2;
        int mid;
        long product;
        while(left < right){
            mid = right - (right - left) / 2;
            product = (long)mid * mid;
            if(product == n){
                return mid;
            }else if(product > n){
                right = mid-1;
            }else if(product < n){
                left = mid;
            }
        }
        return n <= 0 ? 0 : left;
    }
}
