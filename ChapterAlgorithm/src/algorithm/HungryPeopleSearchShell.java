package algorithm;
/*
 * 饥饿的小易：
 * 		小易总是感觉饥饿，所以作为章鱼的小易经常出去寻找贝壳吃。
 * 最开始小易在一个初始位置x_0。对于小易所处的当前位置x，他只能通过神秘的力量移动到 4 * x + 3或者8 * x + 7。
 * 因为使用神秘力量要耗费太多体力，所以它只能使用神秘力量最多100,000次。
 * 贝壳总生长在能被1,000,000,007整除的位置(比如：位置0，位置1,000,000,007，位置2,000,000,014等)。
 * 小易需要你帮忙计算最少需要使用多少次神秘力量就能吃到贝壳。
输入描述:
		输入一个初始位置x_0,范围在1到1,000,000,006
输出描述:
		输出小易最少需要使用神秘力量的次数，如果使用次数使用完还没找到贝壳，则输出-1
输入例子:
		125000000
输出例子:
		1
		
		解析：
			2次8 * x + 7移动等于3次4 * x + 3移动
 */
import java.util.*;
public class HungryPeopleSearchShell {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long pos = in.nextLong();
		System.out.println(getMoveTime(pos));
	}
	public static int getMoveTime(long pos){
		int time = 0;
		int x = 0;		//4 * x + 3		移动次数
		int y = 0;		//8 * x + 7		移动次数
		long target = 1000000007;
		while(pos < target && time < 100000){
			pos = 8 * pos + 7;
			pos = 8 * pos + 7;
			time+=2;
			if(pos == target){
				break;
			}
			if(pos > target){
				pos = (pos - 7)/8;
				time--;
				if(pos == target){
					break;
				}
				pos = 4 * pos + 3;
				time++;
				if(pos == target){
					break;
				}
				pos = (pos - 3)/4;
				pos = (pos - 7)/8;
				time--;
				for(int i = 0 ; i < 3 ; i++){
					pos = 4 * pos + 3;
					time++;
					if(pos == target){
						return time;
					}
				}
				target+=1000000007;
			}
		}
		return time;
	}
}
