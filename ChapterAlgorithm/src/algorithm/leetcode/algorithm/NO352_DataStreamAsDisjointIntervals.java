package algorithm.leetcode.algorithm;
/*
 * hard
 * 352. Data Stream as Disjoint Intervals 
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen 
 * so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]

Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size? 
 */
import java.util.*;
public class NO352_DataStreamAsDisjointIntervals {
	//方法1：
	//O(logN)time adding
	//利用TreeMap存储以区间的下界为key，区间变量为value的键值对
	//利用TreeMap的lowerKey()和higherKey()方法，寻找该值的前一个区间和后一个区间
	//然后就判断该值和前后区间的关系进行添加或合并
	class SummaryRanges {
	    TreeMap<Integer,Interval> treeMap;
	    /** Initialize your data structure here. */
	    public SummaryRanges() {
	        treeMap = new TreeMap<>();
	    }
	    
	    public void addNum(int val) {
	        if(treeMap.containsKey(val)){
	            return;
	        }
	        Integer lower = treeMap.lowerKey(val);
	        Integer higher = treeMap.higherKey(val);
	        if(lower != null && higher != null && treeMap.get(lower).end+1 == val && val+1 == higher){
	            treeMap.get(lower).end = treeMap.get(higher).end;
	            treeMap.remove(higher);
	        }else if(lower != null && treeMap.get(lower).end+1 >= val){
	            treeMap.get(lower).end = Math.max(treeMap.get(lower).end,val);
	        }else if(higher != null && val+1 == higher){
	            treeMap.put(val,new Interval(val,treeMap.get(higher).end));
	            treeMap.remove(higher);
	        }else{
	            treeMap.put(val,new Interval(val,val));
	        }
	    }
	    
	    public List<Interval> getIntervals() {
	        return new ArrayList<>(treeMap.values());
	    }
	}
	
	//方法2：
	//利用BST存储区间，进行增删节点来合并或增加区间
	class SummaryRanges2 {
	    class BSTNode{
	        Interval interval;
	        BSTNode left,right;
	        BSTNode(Interval i){
	            interval = i;
	        }
	    }
	    private BSTNode addKey(int val,BSTNode root){
	        if(root == null){
	            root = new BSTNode(new Interval(val,val));
	        }else if(root.interval.start > val){
	            root.left = addKey(val,root.left);
	        }else if(root.interval.end < val){
	            root.right = addKey(val,root.right);
	        }
	        return root;
	    }
	    
	    private BSTNode findMin(BSTNode root){
	        if(root == null || root.left == null){
	            return root;
	        }else{
	            return findMin(root.left);
	        }
	    }
	    
	    private BSTNode remove(Interval x,BSTNode root){
	        if(root == null || x == null){
	            return root;
	        }else if(root.interval.start > x.end){
	            root.left = remove(x,root.left);
	        }else if(root.interval.end < x.start){
	            root.right = remove(x,root.right);
	        }else if(root.left != null && root.right != null){
	            root.interval = findMin(root.right).interval;
	            root.right = remove(root.interval,root.right);
	        }else{
	            root = (root.left != null) ? root.left : root.right;
	        }
	        return root;
	    }
	    
	    private BSTNode findKey(int val,BSTNode root){
	        if(root == null){
	            return root;
	        }else if(root.interval.start > val){
	            return findKey(val,root.left);
	        }else if(root.interval.end < val){
	            return findKey(val,root.right);
	        }else{
	            return root;
	        }
	    }
	    
	    private void inorder(BSTNode root){
	        if(root == null){
	            return;
	        }
	        inorder(root.left);
	        result.add(root.interval);
	        inorder(root.right);
	    }
	    
	    private BSTNode root;
	    private List<Interval> result;
	    
	    /** Initialize your data structure here. */
	    public SummaryRanges2() {
	        root = null;
	        result = new ArrayList<>();
	    }
	    
	    public void addNum(int val) {
	        if(root == null){
	            root = new BSTNode(new Interval(val,val));
	        }else if(findKey(val,root) == null){
	            BSTNode lower = findKey(val-1,root);
	            BSTNode higher = findKey(val+1,root);
	            if(lower == null && higher == null){
	                root = addKey(val,root);
	            }else if(lower != null && higher == null){
	                lower.interval.end++;
	            }else if(lower == null && higher != null){
	                higher.interval.start--;
	            }else{
	                Interval interval = lower.interval;
	                int end = higher.interval.end;
	                root = remove(higher.interval,root);
	                interval.end = end;
	            }
	        }
	    }
	    
	    public List<Interval> getIntervals() {
	        result.clear();
	        inorder(root);
	        return result;
	    }
	}
	
	//方法3：
	//二分搜索
	class SummaryRanges3 {
	    
	    List<Interval> list;
	    /** Initialize your data structure here. */
	    public SummaryRanges3() {
	        list = new ArrayList<Interval>();
	    }
	    
	    public void addNum(int val) {
	        Comparator<Interval> comp = new Comparator<Interval>() {
	            public int compare(Interval o1, Interval o2) {
	                if(o1.start == o2.start) return o1.end - o2.end;
	                return o1.start - o2.start;
	            }  
	        };
	        Interval interval = new Interval(val,val);
	        int i = Collections.binarySearch(list, interval, comp);
	        if(i >= 0) return;
	        i = -(i + 1);
	        list.add(i, interval);
	        i = i ==0? 0 : i -1;
	        while(i < list.size() - 1 && list.get(i).end >= list.get(i + 1).start - 1) {
	            list.get(i).end = Math.max(list.get(i).end, list.get(i + 1).end);
	            list.remove(i + 1);
	        }
	        i++;
	        while(i < list.size() - 1 && list.get(i).end >= list.get(i + 1).start - 1) {
	            list.get(i).end = Math.max(list.get(i).end, list.get(i + 1).end);
	            list.remove(i + 1);
	        }
	    }
	    
	    public List<Interval> getIntervals() {
	        return list;
	    }
	}
}
