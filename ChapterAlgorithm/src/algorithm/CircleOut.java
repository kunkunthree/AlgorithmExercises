package algorithm;
import static xie.util.Print.*;
import java.util.*;
/*
 * 出圈游戏：50人围成一圈数到3和3的倍数时出圈，问剩下的人是谁
 */


public class CircleOut {
	public static int cycle(int total, int k){
		List<Integer> dataList = new LinkedList<>();
		for(int i = 0 ; i < total ; i++){
			dataList.add(i+1);
		}
		int index = -1;
		while(dataList.size() > 1){
			index = (index + k) % dataList.size();
			dataList.remove(index--);
		}
		return dataList.get(0);
	}
	public static void main(String[] args) {
		println("50人围成一圈数到3和3的倍数时出圈，剩下的人在原来的位置时第" + cycle(50, 3) + "个");
	}
}
