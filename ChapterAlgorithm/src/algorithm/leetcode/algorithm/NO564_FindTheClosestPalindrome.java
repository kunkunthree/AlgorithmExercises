package algorithm.leetcode.algorithm;
/*
 * hard
 * 564. Find the Closest Palindrome 
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"

Note:
    The input n is a positive integer represented by string, whose length will not exceed 18.
    If there is a tie, return the smaller one as answer.

 */
import java.util.*;
public class NO564_FindTheClosestPalindrome {
	//方法1：
	//考虑所有情况
	//所有不等于n本身的所有回文字符串，在其中寻找绝对值最小的字符串
	long ans = 0, diff = Long.MAX_VALUE, n = 0;
    private void update(String val) {
        try {
            long tmp = Long.parseLong(val);
            if (tmp == n) return;
            if (Math.abs(tmp - n) < diff || (Math.abs(tmp - n) == diff && tmp < ans)) {
                ans = tmp;
                diff = Math.abs(tmp - n);
            }
        } catch (Exception e) { }
    }
    
    private void concat(long leftHalf) {
        String s = "" + leftHalf, rs = new StringBuilder(s).reverse().toString();
        update(s + rs); // abc -> abccba
        update(s + rs.substring(1)); // abc -> abcba
    }
    
    public String nearestPalindromic(String n) {
        this.n = Long.parseLong(n);
        diff = Long.MAX_VALUE;
        long leftHalf = Long.parseLong(n.substring(0, (n.length() + 1) / 2));
        concat(leftHalf - 1);
        concat((leftHalf - 1) * 10 + 9); // Handle 1, 1000, 100000, etc.
        concat(leftHalf);
        concat(leftHalf + 1);
        concat((leftHalf + 1) / 10); // Handle 9, 999, 99999, etc.
        return "" + ans;
    }
    
    //方法2：
    //找比n大的最小的回文字符串和比n小的最大回文字符串，两者比较与n的绝对值
    //返回绝对值小的
    public String nearestPalindromic2(String n) {
        Long number = Long.parseLong(n);
        Long big = findHigherPalindrome(number + 1);
        Long small = findLowerPalindrome(number - 1);
        return Math.abs(number - small) > Math.abs(big - number) ? String.valueOf(big) : String.valueOf(small);
    }
    public Long findHigherPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray(); // limit
        int m = s.length;
        char[] t = Arrays.copyOf(s, m); // target
        for (int i = 0; i < m / 2; i++) {
            t[m - 1 - i] = t[i];
        }
        for (int i = 0; i < m; i++) {
            if (s[i] < t[i]) {
                return Long.parseLong(String.valueOf(t)); 
            } else if (s[i] > t[i]) { 
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (++t[j] > '9') {
                        t[j] = '0';
                    } else {
                        break;
                    }
                }
                // make it palindrome again
                for (int k = 0; k < m / 2; k++) {
                    t[m - 1 - k] = t[k];
                }
                return Long.parseLong(String.valueOf(t)); 
            }
        }
        return Long.parseLong(String.valueOf(t));    
    }
    public Long findLowerPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray();
        int m = s.length;
        char[] t = Arrays.copyOf(s, m);
        for (int i = 0; i < m / 2; i++) {
            t[m - 1 - i] = t[i];
        }
        for (int i = 0; i < m; i++) {
            if (s[i] > t[i]) {
                return Long.parseLong(String.valueOf(t)); 
            } else if (s[i] < t[i]) {
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (--t[j] < '0') {
                        t[j] = '9';
                    } else {
                        break;
                    }
                }
                if (t[0] == '0') {
                    char[] a = new char[m - 1];
                    Arrays.fill(a, '9');
                    return Long.parseLong(String.valueOf(a)); 
                }
                // make it palindrome again
                for (int k = 0; k < m / 2; k++) {
                    t[m - 1 - k] = t[k];
                }
                return Long.parseLong(String.valueOf(t)); 
            }
        }
         return Long.parseLong(String.valueOf(t));  
    }
}
