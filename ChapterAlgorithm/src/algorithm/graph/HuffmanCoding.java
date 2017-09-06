package algorithm.graph;
/*
 * 哈夫曼编码算法：
 * 	
 */
class HuffmanNode{
	public int weight;
	public int parent,lchild,rchild;
	public HuffmanNode() {}
	public HuffmanNode(int weight,int parent,int lchild,int rchild) {
		this.weight = weight;
		this.parent = parent;
		this.lchild = lchild;
		this.rchild = rchild;
	}
}
public class HuffmanCoding {
	public static void HuffmanCoding(HuffmanNode[] HT,char[][] HC,int[] w,int n){
		//w存放n个字符的权值（均大于0），构造哈夫曼树HT，并求出n个字符的哈夫曼编码HC
		if(n <= 1){
			return;
		}
		int m = 2*n - 1;
		HT = new HuffmanNode[m+1];	//0号单元未用
		for(int i = 0 ; i <= m ; i++){
			if(i < n){
				HT[i] = new HuffmanNode(w[i], 0, 0, 0); 
			}else{
				HT[i] = new HuffmanNode(0,0,0,0);
			}
		}
		for(int i = n+1 ; i <= m ; i++){	//构建哈夫曼树
			//在HT[1...i-1]选择parent为0切weight最小的两个结点，其序号分别为s1和s2
			int[] s = select(HT, i-1);
			HT[s[0]].parent = i;
			HT[s[1]].parent = i;
			HT[i].lchild = s[0];
			HT[i].rchild = s[1];
			HT[i].weight = HT[s[0]].weight + HT[s[1]].weight;
		}
		//——从叶子到根逆向求每个字符的哈夫曼编码——
		HC = new char[n+1][];
		char[] cd = new char[n];			//分配求编码的工作空间
		cd[n-1] = '\0';									//编码结束符
		for(int i = 1 ; i <= n ; i++){			//逐个字符求哈夫曼编码
			int start = n-1;							//编码结束符位置
			for(int c = i , f = HT[i].parent ; f != 0 ; c = f , f = HT[f].parent){
				if(HT[f].lchild == c){
					cd[--start] = '0';
				}else{
					cd[--start] = '1';
				}
			}
			HC[i] = cd;
			cd = new char[n];
			cd[n-1] = '\0';
		}
	}
	public static int[] select(HuffmanNode[] HT,int size){
		int[] result = new int[]{-1,-1};
		for(int i = 1 ; i <= size ; i++){
			if(result[0] == -1){
				if(HT[i].parent == 0){
					result[0] = i;
				}
			}else{
				if(HT[i].parent == 0 && HT[i].weight < result[0]){
					result[0] = i;
				}
			}
		}
		for(int i = 1 ; i <= size ; i++){
			if(result[1] == -1){
				if(result[0] != i && HT[i].parent == 0){
					result[1] = i;
				}
			}else{
				if(result[0] != i && HT[i].parent == 0 && HT[i].weight < result[1]){
					result[1] = i;
				}
			}
		}
		return result;
	}
}
