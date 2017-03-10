package algorithm.microsoft.beauty.of.programming.chapter1;
/*
 * 下过中国象棋的朋友都知道，双方的“将”和“帅”相隔遥远，并且它们不能照面。
 * 在象棋残局中，许多高手能利用这一规则走出精妙的杀招。假设棋盘上只有“将”和“帅”二子（如图 1-3所示）
 * 为了下面叙述方便，我们约定用 A表示“将”，B表示“帅”，那么A和B的运动将被限制在己方的3X3的格子中，
 * A、B可以横向或者纵向移动一格，但是不能沿对角线移动。当A、B处于一条直线上时，棋局结束。
 * 换言之，A、B不能在一条直线上，请编写一个程序输出A、B的所有可能位置。
 */
import java.util.*;
public class ChineseChess {
	public static byte x;
	public static int HALF_BITS_LENGTH = 4;
	public static byte FULLMASK = (byte)0xFF;
	public static byte RMASK = (byte)0x0F;
	public static byte LMASK = (byte)0xF0;
	public static byte GRIDW = 3;
	public static void main(String[] args) {
		solve2();
	}
	public static int getR(){
		return x & 0x0F;
	}
	public static int getL(){
		return (x & 0xF0) >> HALF_BITS_LENGTH;
	}
	public static void setR(byte n){
		x = (byte)((x & LMASK) | n);
	}
	public static void setL(byte n){
		x = (byte)((x & RMASK) | (n << HALF_BITS_LENGTH ));
	}
	public static void solve1(){
		for(setL((byte)1);getL() <= 9 ; setL((byte)(getL()+1)) ){
			for(setR((byte)1);getR() <= 9 ; setR((byte)(getR()+1)) ){
				if(getL() %3 != getR() %3){
					System.out.println("A = " + getL() + " , B = " + getR());
				}
			}
		}
	}
	public static void solve2(){
		byte n = 81;
		while(n-- != 0){
			if(n/9%3 == n%3){
				continue;
			}
			System.out.println("A = " + (n/9+1) + " , B = " + (n%9+1));
		}
	}
}
