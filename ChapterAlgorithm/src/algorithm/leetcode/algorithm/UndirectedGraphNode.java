package algorithm.leetcode.algorithm;
import java.util.*;
public class UndirectedGraphNode {
	public int label;
	List<UndirectedGraphNode> neighbors;
	public UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
	@Override
	public String toString() {
		Set<Integer> set = new HashSet<Integer>();
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.offer(this);
		StringBuilder sb = new StringBuilder("{");
		UndirectedGraphNode node;
		int length;
		while(!queue.isEmpty()){
			length = queue.size();
			for(int i = 0 ; i < length ; i++){
				node = queue.poll();
				sb.append(node.label+",");
				for(UndirectedGraphNode tmpNode : node.neighbors){
					if(!set.contains(tmpNode.label)){
						set.add(tmpNode.label);
						queue.offer(tmpNode);
					}
					sb.append(tmpNode.label+",");
				}
				sb.setCharAt(sb.length()-1, '#');
			}
		}
		sb.setCharAt(sb.length()-1, '}');
		return sb.toString();
	}
}
