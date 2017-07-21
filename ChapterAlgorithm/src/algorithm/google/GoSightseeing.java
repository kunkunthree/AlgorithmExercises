package algorithm.google;
/*
 * 
 */
import java.util.*;
import java.io.*;
public class GoSightseeing {
	public static void main(String[] args) {
//		BufferedReader in = new BufferedReader(
//				new InputStreamReader(
//						new FileInputStream("A-small-practice-go-sightseeing.in")));
//		System.out.println(System.getProperty("user.dir"));
		Scanner in = null;
		try{
//			in = new Scanner(new File("src/algorithm/google/A-small-practice-go-sightseeing.in"));
			in = new Scanner(new File("src/algorithm/google/A-large-practice-go-sightseeing.in"));
		}catch(Exception e){
			e.printStackTrace();
		}
		int n = in.nextInt();
		String[] results = new String[n];
		for(int i = 0 ; i < n ; i++){
			int num = in.nextInt();
			int eachTime = in.nextInt();
			int deadline = in.nextInt();
			int[] startTime = new int[num-1];
			int[] frequency = new int[num-1];
			int[] duration = new int[num-1];
			for(int j = 0 ; j < num-1 ; j++){
				startTime[j] = in.nextInt();
				frequency[j] = in.nextInt();
				duration[j] = in.nextInt();
			}
			results[i] = "Case #"+(i+1) + ": " + getMaxNumber(num, eachTime, deadline, startTime, frequency, duration) + "\n";
			System.out.println(results[i]);
		}
//		for(String result : results){
//			System.out.println(result);
//		}
		FileOutputStream out = null;
		try{
//			out = new FileOutputStream("src/algorithm/google/A-small-practice-go-sightseeing.out");
			out = new FileOutputStream("src/algorithm/google/A-large-practice-go-sightseeing.out");
			for(String result : results){
				out.write(result.getBytes());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
/*
//		int num = 4;
//		int eachTime = 3;
//		int deadline = 12;
//		int[] startTime = new int[]{3,6,1};
//		int[] frequency = new int[]{2,2,3};
//		int[] duration = new int[]{1,2,2};
//		System.out.println(getMaxNumber(num, eachTime, deadline, startTime, frequency, duration));
		
//		int num = 3;
//		int eachTime = 2;
//		int deadline = 30;
//		int[] startTime = new int[]{1,3};
//		int[] frequency = new int[]{2,2};
//		int[] duration = new int[]{27,1};
//		System.out.println(getMaxNumber(num, eachTime, deadline, startTime, frequency, duration));
		
//		int num = 4;
//		int eachTime = 1;
//		int deadline = 11;
//		int[] startTime = new int[]{2,4,8};
//		int[] frequency = new int[]{1,1,2};
//		int[] duration = new int[]{2,5,2};
//		System.out.println(getMaxNumber(num, eachTime, deadline, startTime, frequency, duration));
		
//		int num = 5;
//		int eachTime = 10;
//		int deadline = 5000;
//		int[] startTime = new int[]{14,27,30,2000};
//		int[] frequency = new int[]{27,11,8,4000};
//		int[] duration = new int[]{31,44,20,3};
//		System.out.println(getMaxNumber(num, eachTime, deadline, startTime, frequency, duration));
 */
	}
	/*
	public static String getMaxNum(int num,int eachTime,int deadline,int[] startTime,int[] frequency,int[] duration){
		Stack<int[]> stack = new Stack<int[]>(); //存储到达时间和出发时间
		int firstStartTime = 0;
		if(eachTime <= startTime[0]){
			firstStartTime = startTime[0];
		}else{
			if((eachTime - startTime[0])%frequency[0] == 0){
				firstStartTime = eachTime;
			}else{
				firstStartTime = startTime[0] + ((eachTime-startTime[0])/frequency[0]+1)*frequency[0];
			}
		}
		stack.push(new int[]{eachTime,firstStartTime });
		while(!stack.isEmpty() && stack.size() < num-1){
			int i = stack.size();
			int[] pre = stack.peek();
			if(pre[1] < pre[0]){
				stack.pop();
				if(!stack.isEmpty()){
					pre = stack.peek();
					pre[1]-=frequency[i-1];
				}
			}
			int arriveTime = pre[1]+duration[i-1];
			int leaveTime = Math.max(arriveTime+eachTime, startTime[i]);
			if((leaveTime - startTime[i])%frequency[i] != 0){
				leaveTime = startTime[i] + ((leaveTime-startTime[i])/frequency[i]+1)*frequency[i];
			}
			if(leaveTime > deadline){
				while(leaveTime > deadline && leaveTime >= arriveTime){
					leaveTime-=frequency[i];
				}
			}
			if(leaveTime >= arriveTime){
				stack.push(new int[]{arriveTime,leaveTime});
			}else{
				pre[1]-=frequency[i-1];
			}
		}
		if(stack.isEmpty()){
			return "IMPOSSIBLE";
		}
		int count = 0;
		while(!stack.isEmpty()){
			int[] cur = stack.pop();
			if(cur[1] - cur[0] > eachTime){
				count++;
			}
		}
		return count+"";
	}
	*/
	public static String getMaxNumber(int num,int eachTime,int deadline,int[] startTime,
																				int[] frequency,int[] duration){
		int[] path = new int[num-1];
		int result = helper(path, 0, num-1, num-1, eachTime, deadline, startTime, frequency, duration);
		if(result < 0){
			return "IMPOSSIBLE";
		}
		return result+"";
	}
	private static  int helper(int[] path,int start,int end,
			int num,int eachTime,int deadline,
			int[] startTime,int[] frequency,int[] duration){
		if(start == end){
			return count(path, num, eachTime, deadline, startTime, frequency, duration);
		}
		int max = -1;
		for(int i = 0 ; i < 2 ; i++){
			path[start] = i;
			max = Math.max(max,helper(path, start+1, end, num, eachTime, deadline, startTime, frequency, duration));
		}
		return max;
	}
	private static int count(int[] path,int num,int eachTime,int deadline,
														int[] startTime,int[] frequency,int[] duration){
		int leaveTime = 0;
		int arriveTime = 0;
		for(int i = 0 ; i < num ; i++){
			if(path[i] == 1){
				leaveTime = arriveTime + eachTime;
			}else{
				leaveTime = arriveTime;
			}
			if(leaveTime <= startTime[i]){
				leaveTime = startTime[i];
			}else if((leaveTime-startTime[i])%frequency[i] != 0){
				int freNum = (leaveTime-startTime[i])/frequency[i] + 1;
				leaveTime = startTime[i]+frequency[i] * freNum;
			}
			arriveTime = leaveTime + duration[i];
			if(arriveTime > deadline){
				return -1;
			}
//			System.out.println(i + " : " + leaveTime + "   " + arriveTime);
		}
		int count = 0;
		for(int i : path){
			count+=i;
		}
//		System.out.println(Arrays.toString(path) + "  : " + count);
		return count;
	}
}
