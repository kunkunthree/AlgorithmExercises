package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 寻找发帖"水王":
 * 		Tango是微软亚洲研究院的一个试验项目。研究院的员工和实习生们都很喜欢在Tango上面交流灌水。
 * 传说，Tango有一大"水王"，他不但喜欢发帖，还会回复其他ID发的每个帖子。坊间风闻该"水王"发帖数目
 * 超过了帖子总数的一半。如果你有一个当前论坛上所有帖子(包括回帖)的列表，其中帖子作者的ID也在表中，
 * 你能快速找出这个传说中的Tango水王吗？
 * 方法一：暴力求解
 * 		对每一个ID，遍历整个列表，统计出现的次数，如果超过一半，则说明找到了"水王"。复杂度O(n2)。
 * 方法二：排序
 * 		先对所有ID排序。如果某个ID出现的次数超过总数的一半，那么，这个有序的ID列表中的第N/2项一定这个ID。
 * 复杂度O(nlogn)。
 * 方法三：线性时间算法
 * 		如果每次删除两个不同的ID，那么，在剩下的ID列表中，"水王"ID出现的次数仍然超过总数的一半。
 * 可以通过不断重复这个过程，把ID列表中的ID总数降低，从而得到答案。
 */
import java.util.*;
public class Tango {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int length = in.nextInt();
		int[] array = new int[length];
		for(int i = 0 ; i < length ; i++ ){
			array[i] = in.nextInt();
		}
//		System.out.println("TangoID: "+getTangoID(array));
		int[] tango = get3TangoID(array);
		for(int i = 0 ; i < tango.length ; i++){
			System.out.println("Tango["+i+"] : "+tango[i]);
		}
	}
	public static int getTangoID(int[] array){
		int candidate = array[0];
		int nTimes = 1;
		for(int i = 1 ; i < array.length ; i++){
			if(nTimes == 0){
				candidate = array[i];
				nTimes++;
			}else{
				if(candidate == array[i]){
					nTimes++;
				}else{
					nTimes--;
				}
			}
		}
		return candidate;
	}
	public static int[] get3TangoID(int[] array){
		if(array.length < 3){
			return null;
		}
		int[] candidate = new int[3];
		int[] time = new int[3];
		for(int i = 0 ; i < candidate.length ; i++){
			candidate[i] = -1;
			time[i] = 0;
		}
		for(int i = 0 ; i < array.length ; i++){
			boolean existFlag = false;		//表示是否array[i]在candidate中
			for(int j = 0 ; j < 3 ; j++){
				if(array[i] == candidate[j]){
					time[j]++;
					existFlag = true;
					break;
				}
			}
			if(existFlag){
				continue;
			}
			boolean addFlag = false;		//是否将array[i]添加到candidate中
			for(int j = 0 ; j < 3 ; j++){
				if(time[j] == 0){
					candidate[j] = array[i];
					time[j]++;
					addFlag = true;
					break;
				}
			}
			if(addFlag){
				continue;
			}
			for(int j = 0 ; j < 3 ; j++){
				time[j]--;
			}
		}
		return candidate;
	}
}
