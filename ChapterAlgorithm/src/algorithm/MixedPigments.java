package algorithm;
/*
 * 混合颜料:
 * 		你就是一个画家！你现在想绘制一幅画，但是你现在没有足够颜色的颜料。
 * 为了让问题简单，我们用正整数表示不同颜色的颜料。你知道这幅画需要的n种颜色的颜料，
 * 你现在可以去商店购买一些颜料，但是商店不能保证能供应所有颜色的颜料，所以你需要自己混合一些颜料。
 * 混合两种不一样的颜色A和颜色B颜料可以产生(A XOR B)这种颜色的颜料
 * (新产生的颜料也可以用作继续混合产生新的颜色,XOR表示异或操作)。
 * 本着勤俭节约的精神，你想购买更少的颜料就满足要求，所以兼职程序员的你需要编程来
 * 计算出最少需要购买几种颜色的颜料？
输入描述:
		第一行为绘制这幅画需要的颜色种数n (1 ≤ n ≤ 50)
		第二行为n个数xi(1 ≤ xi ≤ 1,000,000,000)，表示需要的各种颜料.
		输出描述:
		输出最少需要在商店购买的颜料颜色种数，注意可能购买的颜色不一定会使用在画中，只是为了产生新的颜色。
输入例子:
		3
		1 7 3
输出例子:
		3	
		
		解析：
				和线性代数里求线性方程组差不多，通过线性变换求极大线性无关组。
		而题目把“线性变换”改成了“异或变换”，原理是一样的，就是求一组能表示所有颜色的“基”,
		最后保留一个上三角矩阵。一个数是由32位表示的，那么我们可以把一个数看成是32维空间的一个向量。
		最后形成了一个n*32的矩阵，然后就可以按照我们线性代数的方法进行变换啦，变成上三角矩阵，
		最后剩几行就是需要几种颜色。
 */
import java.util.*;
public class MixedPigments {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		ArrayList<Integer> colors = new ArrayList<Integer>();
		for(int i = 0 ; i < num ; i++){
			colors.add(in.nextInt());
		}
		System.out.println(getMinColorNum(colors)+"");
	}
	public static int getMinColorNum(ArrayList<Integer> list){
		Collections.sort(list);	//升序
		int end = list.size()-1;
		int preEnd = end -1;
		int result = 1;
		while(list.size() > 1){		
			// 如果两者的最高位相同，说明最高位可以消掉，  
            // 将两者 xor ，或者说将矩阵两行相减消掉最高位 
			int endValue = list.get(end);				
			int preEndValue = list.get(preEnd);
			//如果两个数相同，则直接跳过，删除其中一个数
			if(endValue != preEndValue && getMaxBit(endValue) == getMaxBit(preEndValue)){
				int tmp = endValue ^ preEndValue;
				 // 如果 temp 这个 比最大两个数小的 数没有被找到，则将 temp 加到 colors 数组中，进行再次 xor
				if(!list.contains(tmp)){
					list.add(tmp);
					Collections.sort(list);
					end++;
					preEnd++;
				}
			}
			else{
				result++;
			}
			// 如果两个最大数的最高位可以消掉，那么消除之后，最大数已被消掉，没有用了  
            // 如果两个最大数的最高位不可以消掉，那么结果＋ 1 ，最大数也没有用了。  
            // 弹出最大数  
			list.remove(end);
			end--;
			preEnd--;
		}
		return result;
	}
	public static int getMaxBit(int n){
		int maxBit = 0;
		while(n > 0){
			maxBit++;
			n>>>=1;
		}
		return maxBit;
	}
}
