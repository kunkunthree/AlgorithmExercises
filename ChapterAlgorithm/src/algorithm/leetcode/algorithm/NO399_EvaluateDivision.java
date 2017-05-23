package algorithm.leetcode.algorithm;
/*
 * medium
 * 399. Evaluate Division 
 *  Equations are given in the format A / B = k, where A and B are variables represented as strings, 
 *  and k is a real number (floating point number). Given some queries, return the answers.
 *   If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
where equations.size() == values.size(), and the values are positive. This represents the equations.
 Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 

The input is always valid. You may assume that evaluating the queries will result in no division by zero 
and there is no contradiction. 
 */
import java.util.*;
public class NO399_EvaluateDivision {
	//方法1：
	//DFS，用两层map构建图，深度优先搜索
	//搜索过程用一个Set记录搜索痕迹
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String,Map<String,Double>> mapStart = new HashMap<String,Map<String,Double>>();
        String[] equation,query;
        for(int i = 0 ; i < equations.length ; i++){
            equation = equations[i];
            if(!mapStart.containsKey(equation[0])){
                mapStart.put(equation[0],new HashMap<String,Double>());
            }
            if(!mapStart.get(equation[0]).containsKey(equation[1])){
                mapStart.get(equation[0]).put(equation[1],values[i]);
            }
            if(!mapStart.containsKey(equation[1])){
                mapStart.put(equation[1],new HashMap<String,Double>());
            }
            if(!mapStart.get(equation[1]).containsKey(equation[0])){
                mapStart.get(equation[1]).put(equation[0],1.0/values[i]);
            }
        }
        double[] result = new double[queries.length];
        for(int i = 0 ; i < queries.length ; i++){
            query = queries[i];
            result[i] = search(query[0],query[1],mapStart,new HashSet<String>());
        }
        return result;
    }
    public double search(String start,String end,Map<String,Map<String,Double>> mapStart,Set<String> visited){
        String curVisited = start+":"+end;
        if(visited.contains(curVisited) || !mapStart.containsKey(start) || !mapStart.containsKey(end)){
            return -1.0;
        }
        if(start.equals(end)){
            return 1.0;
        }
        visited.add(curVisited);
        Map<String,Double> mapEnd = mapStart.get(start);
        for(String curEnd : mapEnd.keySet()){
            double result = search(curEnd,end,mapStart,visited);
            if(result != -1.0){
                return result * mapEnd.get(curEnd);
            }
        }
        visited.remove(curVisited);
        return -1.0;
    }
}
