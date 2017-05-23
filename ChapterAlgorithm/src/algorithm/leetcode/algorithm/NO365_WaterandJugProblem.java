package algorithm.leetcode.algorithm;
/*
 * medium
 * 365. Water and Jug Problem
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. 
 * You need to determine whether it is possible to measure exactly z litres using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

Operations allowed:

    Fill any of the jugs completely with water.
    Empty any of the jugs.
    Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.

Example 1: (From the famous "Die Hard" example)

Input: x = 3, y = 5, z = 4
Output: True

Example 2:

Input: x = 2, y = 6, z = 5
Output: False

 */
public class NO365_WaterandJugProblem {
	//方法1：
	//数学定理：裴蜀定理
	//对任何整数a、b和它们的最大公约数d，关于未知数x和y的线性不定方程（称为裴蜀等式）：
	//若a,b是整数,且（a,b)=d，那么对于任意的整数x,y,ax+by都一定是d的倍数，
	//特别地，一定存在整数x,y，使ax+by=d成立。
	//We can always find a and b to satisfy ax + bx = d where d = gcd(x, y)
	//So, everything is clear, if z % d == 0, then we have (a*z/d)*x + (b*z/d)*y = z, which means m and n exist.
	public boolean canMeasureWater(int x, int y, int z) {
        if(x+y < z){
            return false;
        }
        if(x == 0 && y == 0 & z == 0){
            return true;
        }
        return z%getGCD(x,y) == 0;
    }
    public static int getGCD(int x,int y){
		if(y == 0){
			return x;
		}else if (x < y){
			return getGCD(y, x);
		}else if((x&1) == 0 && (y&1) == 0){
			return getGCD(x>>1, y>>1)<<1;
		}else if((x&1) == 0 && (y&1) == 1){
			return getGCD(x>>1, y);
		}else if((x&1) == 1 && (y&1) == 0){
			return getGCD(x, y>>1);
		}else{
			return getGCD(x-y, y);
		}
	}
}
