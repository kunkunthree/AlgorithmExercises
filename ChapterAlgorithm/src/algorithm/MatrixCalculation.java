package algorithm;
/*
 * 矩阵计算：
 * 	1.矩阵乘法
 * 	2.矩阵快速幂
 */
public class MatrixCalculation {
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(100));
	}
	//矩阵乘法：c = a × b
	public static int[][] matrixMultiply(int[][] a,int[][] b){
		int rowCount = a.length;
		int columnCount = b[0].length;
		int[][] c = new int[rowCount][columnCount];
		int tmp;
		for(int i = 0 ; i < rowCount ; i++){
			for(int j = 0 ; j < columnCount ; j++){
				tmp = 0;
				for(int k = 0 ; k < a[i].length ; k++){
					tmp+=a[i][k]*b[k][j];
				}
				c[i][j] = tmp;
			}
		}
		return c;
	}
	//矩阵快速幂
	//矩阵的幂 A^n可以通过二分法快速得到，先将n以二进制形式表示，
	//例如n = 100(10进制)时,n = 1100100（二进制）= 4 + 32 + 64 (10进制),那么A^n = (A^4)*(A^32)*(A^64)
	//	while(N > 0){
	//		if((N & 1) == 1){
	//			res=res*A;
	//		}
	//		N>>=1;
	//		A=A*A;
	//	}
	//A必须为方阵
	public static int[][] matrixPower(int[][] A,int n){
		int length = A.length;
		int[][] result = new int[length][length];
		for(int i = 0 ; i < length ; i++){
			for(int j = 0 ; j < length ; j++){
				result[i][j] = 1;
			}
		}
		while(n > 1){
			if((n & 1) == 1){
				result = matrixMultiply(result, A);
			}
			n>>=1;
			A = matrixMultiply(A, A);
		}
		return result;
	}
}
