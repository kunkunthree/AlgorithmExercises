package algorithm.sort;
/*
 * 堆排序：
 * 堆排序的思想：
   利用大顶堆(小顶堆)堆顶记录的是最大关键字(最小关键字)这一特性，使得每次从无序中选择最大记录(最小记录)
   变得简单。
    其基本思想为(大顶堆)：
    1)将初始待排序关键字序列(R1,R2....Rn)构建成大顶堆，此堆为初始的无序区；
    2)将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,......Rn-1)和新的有序区(Rn),且满足R[1,2...n-1]<=R[n]; 
    3)由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,......Rn-1)调整为新堆，
    	然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2....Rn-2)和新的有序区(Rn-1,Rn)。
    	不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
    	
  排序稳定性：
  我们知道堆的结构是节点i的孩子为2*i和2*i+1节点，大顶堆要求父节点大于等于其2个子节点，
  小顶堆要求父节点小于等于其2个子节点。
  在一个长为n 的序列，堆排序的过程是从第n/2开始和其子节点共3个值选择最大(大顶堆)或者最小(小顶堆),
  这3个元素之间的选择当然不会破坏稳定性。但当为n /2-1, n/2-2, ...1这些个父节点选择元素时，就会破坏稳定性。
  有可能第n/2个父节点交换把后面一个元素交换过去了，而第n/2-1个父节点把后面一个相同的元素没 有交换，
  那么这2个相同的元素之间的稳定性就被破坏了。
  
  所以，堆排序不是稳定的排序算法。
 */
import java.util.*;
public class HeapSort {
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int size = in.nextInt();
//		int[] array = new int[size+1];
//		for(int i = 1 ; i <= size ; i++){
//			array[i] = in.nextInt();
//		}
		int[] array = new int[]{0,65, 70, 41, 59, 67, 91, 62, 62, 62, 1, 28, 58, 50};
		int size = array.length -1;
		System.out.println("排序前：");
		System.out.println(Arrays.toString(array));
		heapSort(array, size);
		System.out.println("排序后：");
		System.out.println(Arrays.toString(array));
	}
	public static void adjustHeap(int[] array,int i,int size){
		int max = i;
		int leftChild = 2 * i;
		int rightChild = 2* i +1;
		if(i <= size/2){
			if(leftChild <= size && array[leftChild] > array[max]){
				max = leftChild;
			}
			if(rightChild <= size && array[rightChild] > array[max]){
				max = rightChild;
			}
			if(max != i){
				int tmp = array[max];
				array[max] = array[i];
				array[i] = tmp;
				adjustHeap(array, max, size);	//避免调整之后以max为父节点的子树不是堆 
			}
		}
	}
	public static void heapSort(int[] array,int size){
		//建立大顶堆,下标为0的元素不存数据
		for(int i = size/2 ; i >= 1 ; i--){
			adjustHeap(array, i, size);
		}
		for(int i = size ; i>1 ; i--){
			//将对顶元素和最后一个元素交换，使每次最后一个元素都为最大元素，即筛选出最大元素
			int tmp = array[i];
			array[i] = array[1];
			array[1] = tmp;
			//重新建立长度为i-1的大顶堆，进行下一次的筛选
			adjustHeap(array, 1, i-1);
		}
	}
}
