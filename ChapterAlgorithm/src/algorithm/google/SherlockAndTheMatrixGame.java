package algorithm.google;
import java.io.File;
import java.io.FileOutputStream;
/*
 * 
 */
import java.util.*;
public class SherlockAndTheMatrixGame {
	public static void main(String[] args) {
		double time = System.currentTimeMillis();
//		Scanner in = new Scanner(System.in);
		Scanner in = null;
		try{
			in = new Scanner(new File("src/algorithm/google/B-small-practice-SherlockAndTheMatrixGame.in"));
//			in = new Scanner(new File("src/algorithm/google/B-large-practice-SherlockAndTheMatrixGame.in"));
		}catch(Exception e){
			e.printStackTrace();
		}
		int num = in.nextInt();
		String[] results = new String[num];
		for(int i = 0 ; i < num ; i++){
			int n = in.nextInt();
			int k = in.nextInt();
			int A1 = in.nextInt();
			int B1 = in.nextInt();
			int C = in.nextInt();
			int D = in.nextInt();
			int E1 = in.nextInt();
			int E2 = in.nextInt();
			int F = in.nextInt();
			results[i] = "Case #" + (i+1) + ": " + getKthMaxSubmatrixSum(getMatrix(n, A1, B1, C, D, E1, E2, F), n, n, k) + "\n";
//			System.out.println(results[i]);
		}
		System.out.println("计算用时：" +  String.format("%.2f",  (System.currentTimeMillis() - time)/1000.0) + "s");
		time = System.currentTimeMillis();
//		int n = in.nextInt();
//		long k = in.nextLong();
//		long A1 = in.nextLong();
//		long B1 = in.nextLong();
//		long C = in.nextLong();
//		long D = in.nextLong();
//		long E1 = in.nextLong();
//		long E2 = in.nextLong();
//		long F = in.nextLong();
//		System.out.println(getKthMaxSubmatrixSum(getMatrix(n, A1, B1, C, D, E1, E2, F), n, n, k) );
		
		for(String result : results){
			System.out.println(result);
		}
		FileOutputStream out = null;
		try{
			out = new FileOutputStream("src/algorithm/google/B-small-practice-SherlockAndTheMatrixGame.out");
//			out = new FileOutputStream("src/algorithm/google/B-large-practice-SherlockAndTheMatrixGame.out");
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
		System.out.println("输出到文件用时：" +  String.format("%.2f",  (System.currentTimeMillis() - time)/1000.0) + "s");
	}
	public static long[][] getMatrix(int n,long A1,long B1,long C,long D,long E1,long E2,long F){
		long[] A = new long[n];
		long[] B = new long[n];
		long x = A1,y = B1, r = 0,s = 0;
		A[0] = A1;
		B[0] = B1;
		for(int i = 1 ; i < n ; i++){
			long lastX = x,lastY = y,lastR = r,lastS = s;
			x = (C*lastX + D*lastY + E1)%F;
			y = (D*lastX + C *lastY + E2)%F;
			r = (C*lastR + D*lastS + E1)%2;
			s = (D*lastR + C*lastS + E2)%2;
			A[i] = (r%2 == 0 ? 1 : -1)*x;
			B[i] = (s%2 == 0 ? 1 : -1)*y;
		}
//		System.out.println("A : \n" + Arrays.toString(A));
//		System.out.println("B : \n" + Arrays.toString(B));
		return getMatrix(A, B);
	}
	public static long[][] getMatrix(long[] A,long[] B){
		int m = A.length,n = B.length;
		long[][] matrix = new long[m][n];
		for(int i = 0 ; i < m ; i++){
			for(int j = 0 ; j < n ; j++){
				matrix[i][j] = A[i]*B[j];
			}
		}
//		System.out.println("matrix:\n" + Arrays.deepToString(matrix));
		return matrix;
	}
	public static long getKthMaxSubmatrixSum(long[][] matrix,int m,int n,long k){
		long[][] sum = new long[m+1][n+1];
		for(int i = 0 ; i < m ; i++){
			for(int j = 0 ; j < n ; j++){
				sum[i+1][j+1] = sum[i][j+1] + sum[i+1][j] - sum[i][j] + matrix[i][j];
			}
		}
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long subSum = 0;
		for(int x1 = 0 ; x1 <= m ; x1++){
			for(int y1 = 0 ; y1 <= n ; y1++){
				for(int x2 = x1+1 ; x2 <= m ; x2++){
					for(int y2 = y1+1 ; y2 <= n ; y2++){
//						if(x1 == x2 && y1 == y2 || x2 == 0 || y2 == 0){
//							continue;
//						}
						subSum = sum[x2][y2] - sum[x2][y1] - sum[x1][y2] + sum[x1][y1];
						pq.offer(subSum);
						if(pq.size() > k){
							pq.poll();
						}
					}
				}
			}
		}
		subSum = pq.peek();
//		for(int i = 0 ; i < 1000 ; i++){
//			System.out.println(pq.poll());
//		}
//		System.out.println("sum : ");
//		for(long[] row : sum){
//			System.out.println(Arrays.toString(row));
//		}
		/*
		FileOutputStream out = null;
		try{
			out = new FileOutputStream("src/algorithm/google/B-small-practice-SherlockAndTheMatrixGame.out");
//			out = new FileOutputStream("src/algorithm/google/B-large-practice-SherlockAndTheMatrixGame.out");
//			while(!pq.isEmpty()){
//				out.write((pq.poll()+"\n").getBytes());
//			}
			for(long[] row : sum){
//				System.out.println(Arrays.toString(row));
				out.write((Arrays.toString(row)+"\n").getBytes());
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
		*/
		return subSum;
	}
}
