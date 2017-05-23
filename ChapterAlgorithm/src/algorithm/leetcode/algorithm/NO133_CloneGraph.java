package algorithm.leetcode.algorithm;
/*
 * medium
 * 133. Clone Graph
 *  Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:

Nodes are labeled uniquely.
We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

    First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
    Second node is labeled as 1. Connect node 1 to node 2.
    Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.

Visually, the graph looks like the following:

        1
       / \
     /     \
    0 ---  2
	         / \
	         \_/

 */
import java.util.*;
public class NO133_CloneGraph {
	public static void main(String[] args) {
//		UndirectedGraphNode node0 = new UndirectedGraphNode(0);
//		UndirectedGraphNode node2 = new UndirectedGraphNode(1);
		UndirectedGraphNode[] nodes = new UndirectedGraphNode[6];
		for(int i = 0 ; i < nodes.length ; i++){
			nodes[i] = new UndirectedGraphNode(i);
		}
		nodes[0].neighbors.add(nodes[1]);
		nodes[0].neighbors.add(nodes[5]);
		nodes[1].neighbors.add(nodes[2]);
		nodes[1].neighbors.add(nodes[5]);
		nodes[2].neighbors.add(nodes[3]);
		nodes[3].neighbors.add(nodes[4]);
		nodes[3].neighbors.add(nodes[4]);
		nodes[4].neighbors.add(nodes[5]);
		nodes[4].neighbors.add(nodes[5]);
		
		System.out.println(cloneGraph(nodes[0]));
	}
	//方法1：
	//迭代实现，用一个队列存储还没设置好neighbors的节点，用一个Map存储已经建立好唯一的节点，
	//避免重复创建相同标签的节点，并能重复使用
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node == null){
            return node;
        }
        Map<Integer,UndirectedGraphNode> map = new HashMap<Integer,UndirectedGraphNode>();
        UndirectedGraphNode tmpNode,result = null;
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        List<UndirectedGraphNode> list;
        queue.offer(node);
        while(!queue.isEmpty()){
            int length = queue.size();
            for(int i = 0 ; i < length ; i++){
                node = queue.poll();
                if(!map.containsKey(node.label)){   //如果map中没有记录该点，新建节点存入map
                    map.put(node.label,new UndirectedGraphNode(node.label));
                }
                //从map中获取对应标签的节点
                tmpNode = map.get(node.label);
                if(result == null){
                    result = tmpNode;
                }
                //获取相邻节点列表
                list = tmpNode.neighbors;
                for(UndirectedGraphNode n : node.neighbors){
                    if(!map.containsKey(n.label)){
                        queue.offer(n);
                        map.put(n.label,new UndirectedGraphNode(n.label));
                    }
                    list.add(map.get(n.label));
                }
            }
        }
        return result;
    }
	//方法2：
	//递归实现，利用HashMap
	public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        Map<Integer,UndirectedGraphNode> map = new HashMap<Integer,UndirectedGraphNode>();
        return cloneGraphHelper(map,node);
    }
    public UndirectedGraphNode cloneGraphHelper(Map<Integer,UndirectedGraphNode> map,UndirectedGraphNode node){
        if(node == null){
            return node;
        }
        UndirectedGraphNode cloneNode;
        List<UndirectedGraphNode> list;
        if(!map.containsKey(node.label)){   //如果map中没有记录该点，新建节点存入map
            map.put(node.label,new UndirectedGraphNode(node.label));
        }
        //从map中获取对应标签的节点
        cloneNode = map.get(node.label);
        //获取相邻节点列表
        list = cloneNode.neighbors;
        for(UndirectedGraphNode n : node.neighbors){
            if(!map.containsKey(n.label)){
                map.put(n.label,new UndirectedGraphNode(n.label));
                cloneGraphHelper(map,n);
            }
            list.add(map.get(n.label));
        }
        return cloneNode;
    }
    //方法3：
    //递归实现，简化代码
    public UndirectedGraphNode cloneGraph3(UndirectedGraphNode node) {
        Map<Integer,UndirectedGraphNode> map = new HashMap<Integer,UndirectedGraphNode>();
        return cloneGraphHelper(map,node);
    }
    public UndirectedGraphNode cloneGraphHelper2(Map<Integer,UndirectedGraphNode> map,UndirectedGraphNode node){
        if(node == null){
            return node;
        }
        UndirectedGraphNode cloneNode;
        List<UndirectedGraphNode> list;
        if(!map.containsKey(node.label)){   //如果map中没有记录该点，新建节点存入map
            map.put(node.label,new UndirectedGraphNode(node.label));
        }else{
            return map.get(node.label);
        }
        //从map中获取对应标签的节点
        cloneNode = map.get(node.label);
        //获取相邻节点列表
        list = cloneNode.neighbors;
        for(UndirectedGraphNode n : node.neighbors){
            list.add(cloneGraphHelper2(map,n));
        }
        return cloneNode;
    }
}
