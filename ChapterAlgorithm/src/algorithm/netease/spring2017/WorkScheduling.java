package algorithm.netease.spring2017;
/*
 * 6.
 * [编程题] 工作安排:
 * 		现在有n位工程师和6项工作(编号为0至5)，现在给出每个人能够胜任的工作序号表(用一个字符串表示，
 * 比如：045，表示某位工程师能够胜任0号，4号，5号工作)。现在需要进行工作安排，
 * 每位工程师只能被安排到自己能够胜任的工作当中去，两位工程师不能安排到同一项工作当中去。
 * 如果两种工作安排中有一个人被安排在的工作序号不一样就被视为不同的工作安排，
 * 现在需要计算出有多少种不同工作安排计划。
输入描述:
		输入数据有n+1行：
		第一行为工程师人数n(1 ≤ n ≤ 6)
		接下来的n行，每行一个字符串表示第i(1 ≤ i ≤ n)个人能够胜任的工作(字符串不一定等长的)
输出描述:
		输出一个整数，表示有多少种不同的工作安排方案
输入例子:
6
012345
012345
012345
012345
012345
012345
输出例子:
720
 */
import java.util.*;
class Work{
	int curNum;
	Queue<Integer> egnQueue;
	public Work() {
		egnQueue = new LinkedList<Integer>();
	}
}
class WorkSet{
	int size;
	int[] array;
	boolean[][] matrix;
	Set<Integer> set;
	public WorkSet(int n,int egrCount) {
		array = new int[n];
		for(int i = 0 ; i < n ; i++){
			array[i] = 0;
		}
		matrix = new boolean[egrCount][n];
		for(int i = 0 ; i < egrCount ; i++){
			for(int j = 0 ; j < n ; j++){
				matrix[i][j] = false;
			}
		}
		size = 0;
		set = new HashSet<Integer>();
	}
	public void add(Integer i,int workNum){
		if(array[i] == 0){
			set.add(i);
		}
		matrix[i][workNum] = true;
		array[i]++;
		size++;
	}
	public void remove(int i,int workNum){
		array[i]--;
		size--;
		matrix[i][workNum] = false;
		if(array[i] == 0){
			set.remove(i);
		}
	}
	public void print(){
		int[] a = new int[array.length];
		for(int i = 0 ; i < matrix.length ; i++){
			for(int j = 0 ; j < matrix[i].length ; j++){
				if(matrix[i][j] == true){
					a[j] = i;
				}
			}
		}
		System.out.println(Arrays.toString(a));
	}
}
public class WorkScheduling {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int workCount = in.nextInt();
		int[][] a = new int[workCount][];
		char[] tmpArray;
		int arraySize;
		for(int i = 0 ; i < workCount ; i++){
			tmpArray = in.next().toCharArray();
			arraySize = tmpArray.length;
			a[i] = new int[arraySize];
			for(int j = 0 ; j < arraySize ; j++){
				a[i][j] = tmpArray[j] - '0';
			}
		}
		System.out.println(getWorkArrangementNumber(a, 6));
	}
	public static int getWorkArrangementNumber(int[][] a,int workCount){
		int engineerNum = a.length;
		Work[] works = new Work[workCount];		//工作序号表，表示每项工作胜任的工程师编号
		for(int i = 0 ; i < workCount ; i++){
			works[i] = new Work();
		}
		for(int i = 0 ; i < a.length ; i++){
			for(int workNum : a[i]){
				works[workNum].egnQueue.add(i);
			}
		}
//		Set<Integer> set = new HashSet<Integer>();
		WorkSet workSet = new WorkSet(workCount,engineerNum);
		return get(workSet, works, workCount-1, engineerNum);
//		return get(set, works, workCount-1, workCount);
	}
	public static int get(WorkSet workSet, Work[] works , int count,int size){
		int sum = 0;
		for(int x : works[count].egnQueue){
			workSet.add(x,count);
			if(count == 0 && workSet.set.size() == size){
				sum++;
//				System.out.println(Arrays.deepToString(workSet.matrix));
				workSet.print();
			}else if(count > 0){
				sum+=get(workSet, works, count-1,size);
			}
			workSet.remove(x,count);
		}
		return sum;
	}
	/*
	 * n>=6的情况
	public static int get(Set<Integer> set, Work[] works , int count,int size){
		int sum = 0;
		boolean isExist = false;
		for(int x : works[count].egnQueue){
			if(set.contains(x)){
				isExist = true;
			}else{
				set.add(x);
				isExist = false;
			}
			if(count == 0 && set.size() == size){
				sum++;
			}else if(count > 0){
				sum+=get(set, works, count-1,size);
			}
			if(isExist == false){
				set.remove(x);
			}
		}
		return sum;
	}
	*/
}
