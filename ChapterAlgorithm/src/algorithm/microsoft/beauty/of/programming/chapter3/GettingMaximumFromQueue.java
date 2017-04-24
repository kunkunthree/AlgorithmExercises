package algorithm.microsoft.beauty.of.programming.chapter3;
/*
 * 3.7	队列中取最大值操作问题：
 * 假设有这样一个拥有三个操作的队列：
 * 1.EnQueue(v)：将v加入队列中
 * 2.DeQueue()：使队列中的队首元素删除并返回此元素
 * 3.MaxElement：返回队列中的最大元素
 * 请设计一种数据结构和算法，让MaxElement操作的时间复杂度尽可能地低。
 */
import xie.util.*;
import java.util.*;
public class GettingMaximumFromQueue {
	public static void main(String[] args) {
		Queue<Integer> queue =  new LinkedList<Integer>();
	}
	//解法一：直接法
	//时间复杂度：O(N)
	public class Queue1{
		private Node head;
		private Node tail;
		public int size = 0;
		public void add(Node node){
			if(node == null){
				return;
			}
			if(size <= 0){
				head = node;
				tail = node;
			}else{
				tail.next = node;
				while(node.next != null){
					node = node.next;
					size++;
				}
				tail = node;
				size++;
			}
		}
		public Node poll(){
			if(size <= 0){
				return null;
			}
			Node tmp = head;
			head = head.next;
			size--;
			return tmp;
		}
		public Node max(){
			if(size <= 0){
				return null;
			}
			Node maxNode = head;
			Node tmp = head.next;
			while(tmp != null){
				if(tmp.data > maxNode.data){
					maxNode = tmp;
				}
				tmp = tmp.next;
			}
			return maxNode;
		}
	}
	//解法二，利用最大堆来维护队列中的元素
	//获取max时间复杂度为O(1)，维护堆复杂度为O(logN/log2)
	public class Queue2 extends Queue1{
		private ArrayList<Node> heap = new ArrayList<Node>();
		public Queue2() {
			heap.add(new Node(null,0));	//最大堆下标为0的元素不存数据
		}
		@Override
		public void add(Node node) {
			super.add(node);
			Node tmp = heap.get(1);
			heap.add(tmp);
			heap.set(1, node);
			adjustHeap(heap, 1, size);
		}
		@Override
		public Node poll() {
			Node result = super.poll();		//poll之后size减了1
			int index = heap.indexOf(result);
			Node tmp = heap.get(size+1);
			Node parent = heap.get(index/2);
			int max;
			//将最后一个元素tmp放到缺口index上，然后调整堆
			//判断是否大于父节点
			while(tmp.data > parent.data && index > 1){
				heap.set(index, parent);
				index/=2;
			}
			//判断是否小于子节点
			while(index <= size+1){
				max = index;
				if( index * 2 <= size+1 && heap.get(index * 2).data > heap.get(max).data){
					max = index * 2;
				}
				if( index * 2 +1 <= size+1 && heap.get(index * 2 +1).data > heap.get(max).data){
					max = index * 2 + 1;
				}
				if(max != index){
					heap.set(index, heap.get(max));
					index = max;
				}else{
					break;
				}
			}
			//把最后一个元素放到index上
			heap.set(index, tmp);
			heap.remove(size+1);
			return result;
		}
		@Override
		public Node max() {
			return heap.get(1);
		}
		public  void adjustHeap(ArrayList<Node> array,int i,int size){
			int max = i;
			int leftChild = 2 * i;
			int rightChild = 2* i +1;
			if(i <= size/2){
				if(leftChild <= size && array.get(leftChild).data > array.get(max).data){
					max = leftChild;
				}
				if(rightChild <= size && array.get(rightChild).data > array.get(max).data){
					max = rightChild;
				}
				if(max != i){
					Node tmp = array.get(max);
					array.set(max, array.get(i));
					array.set(i, tmp);
					adjustHeap(array, max, size);	//避免调整之后以max为父节点的子树不是堆 
				}
			}
		}
	}
	//可以尝试用其他数据结构来实现“先进先出”功能，首先实现维护栈的最大值
	//得到max的时间复杂度为O(1)
	public class Stack{
		public static final int MAXN = 1024;
		private Node top;
		private Node max;
		private int stackTop;
		private ArrayList<Integer> link2NextMaxItem = new ArrayList<Integer>();;
		private int maxStackItemIndex;
		public Stack() {
			stackTop=-1;
			maxStackItemIndex = -1;
			max = new Node(null, Integer.MIN_VALUE);
		}
		public void push(Node node){
			stackTop++;
			node.next = top;
			top = node;
			//更新max的值
			if(node.data > max.data){
				link2NextMaxItem.add(maxStackItemIndex);
				maxStackItemIndex = stackTop;
				max = node;
			}
		}
		public Node pop(){
			if(stackTop < 0){
				return null;
			}
			Node result = top;
			top = top.next;
			if(stackTop == maxStackItemIndex){
				link2NextMaxItem.remove(stackTop);
				maxStackItemIndex = link2NextMaxItem.get(stackTop-1);
			}
			stackTop--;
			//更新max
			int tmp = stackTop;
			Node node = top;
			while(tmp > maxStackItemIndex){
				node = node.next;
				tmp--;
			}
			if(node == null ){
				max = new Node(null,Integer.MIN_VALUE);
			}else{
				max = node;
			}
			//返回栈顶元素
			return result;
		}
		public Node max(){
			return max;
		}
	}
	//解法三：利用两个栈实现“先进先出”功能
	//时间复杂度为O(1)
	public class Queue3{
		private Stack stackA;	//用来存储新加进来的元素
		private Stack stackB;	//用于得到出队列的元素
		public Queue3() {
			stackA = new Stack();
			stackB = new Stack();
		}
		public void add(Node node){
			stackA.push(node);
		}
		public Node poll(){
			if(stackB.stackTop < 0){
				while(stackA.stackTop >= 0){
					stackB.push(stackA.pop());
				}
			}
			return stackB.pop();
		}
		public Node max(){
			if(stackA.stackTop <0 && stackB.stackTop < 0){
				return null;
			}
			return stackA.max.data > stackB.max.data ? stackA.max : stackB.max;
		}
	}
}

//利用新加入的元素与当前的最小值做对比，存入当前值与当前最小值的差值，
//如果该值小于0，则说明最小值被更新了，如果大于0，则最小值不变
class MinStack2 {
    long min;
    Stack<Long> stack;

    public MinStack2(){
        stack=new Stack<>();
    }
    
    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(0L);
            min=x;
        }else{
            stack.push(x-min);//Could be negative if min value needs to change
            if (x<min) min=x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        
        long pop=stack.pop();
        
        if (pop<0)  min=min-pop;//If negative, increase the min value
        
    }

    public int top() {
        long top=stack.peek();
        if (top>0){
            return (int)(top+min);
        }else{
           return (int)(min);
        }
    }

    public int getMin() {
        return (int)min;
    }
}