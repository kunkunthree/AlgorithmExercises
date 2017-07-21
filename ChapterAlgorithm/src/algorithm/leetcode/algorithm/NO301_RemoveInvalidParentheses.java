package algorithm.leetcode.algorithm;
/*
 * hard
 * 301. Remove Invalid Parentheses 
 *  Remove the minimum number of invalid parentheses in order to make the input string valid. Return all 
 *  possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:

"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

similar problems:
20. Valid Parentheses 
 */
import java.util.*;
public class NO301_RemoveInvalidParentheses {
	//方法1：
	//recursive DFS，O(n*(2^n))time
	//第一次循环从左往右统计‘('和’)'的个数差count，遇到'('则count增加1，遇到')'则count减少1，
	//当count<0时，说明需要此时需要从中删除一个')'，
	//为了避免重复，只删除连续的')'的第一个')'，然后记录此时删除的位置，继续DFS下去，而每一个删除后DFS结束后，
	//不会再进行这一次循环，以免出现重复的结果以及如“())(”等不符合规则的字符串
	//当此次循环count一直都大于等于0，则说明此时'('的个数大于等于')'的个数
	//那么此时需要删除')'的个数，为了删除个数，那么只要将此时得到的字符串进行逆转，
	//统计')'和'('的个数差，遇到')'则count增加1，遇到'('则count减少1，再一次进行DFS即可
	//最后，将得到'('的个数大于等于')'，')'的个数大于等于'('，即'('的个数等于')'，得到符合规则的结果
	public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        helper(s,result,0,0,new char[]{'(',')'});
        return result;
    }
    private void helper(String s,List<String> result,int last_i,int last_j,char[] par){
        for(int count = 0,i = last_i ; i < s.length() ; i++){
            if(s.charAt(i) == par[0]){
                count++;
            }else if(s.charAt(i) == par[1]){
                count--;
            }
            if(count >= 0){
                continue;
            }
            //如果发现此时par[0]的个数小于par[1]的个数，那么需要在[last_j,i]中去除一个par[1]以平衡两者个数
            for(int j = last_j ; j <= i ; j++){
                //只去除连续出现的par[1]的第一个，以免最后结果出现重复的情况
                if(s.charAt(j) == par[1] && (j == last_j || s.charAt(j-1) != par[1])){
                    //平衡后只能从i开始重新计数
                    helper(s.substring(0,j)+s.substring(j+1,s.length()), result, i, j, par);
                }
            }
            //平衡后不能继续计数，以免出现重复的结果以及如“())(”等不符合规则的字符串
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if(par[0] == '('){  // finished left to right，此时'('的个数大于等于')'的个数
            helper(reversed,result,0,0,new char[]{')','('});
        }else{  // finished right to left，此时')'的个数大于等于'('的个数,即，'('的个数等于')'的个数
            result.add(reversed);
        }
    }
    //方法2：
    //BFS，O(n*(2^n))time
    //用一个队列queue记录遍历路径，一个set记录遍历痕迹避免结果重复
    //每次对queue出列的字符串进行判定是否符合，如果不符合，则说明需要从中删除一个'('或’)'来平衡后继续BFS
    //当删除后的字符串未出现在set中，则将其加入set和queue，继续下一次循环
    //但是如果符合，则说明此时是删除个数最少的路径，此时不会应有比该路径删除个数更多的路径，则不再进行删除操作
    //此时只需要判断是否满足规则，满足则加入结果集即可
    public List<String> removeInvalidParentheses2(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        boolean found = false;
        while(!queue.isEmpty()){
            s = queue.poll();
            if(isValid(s)){
                result.add(s);
                found = true;
            }
            if(found == true){
                continue;
            }
            for(int i = 0 ; i < s.length() ; i++){
                if(s.charAt(i) != '(' && s.charAt(i) != ')'){
                    continue;
                }
                String tmp = s.substring(0,i)+s.substring(i+1,s.length());
                if(!visited.contains(tmp)){
                    queue.offer(tmp);
                    visited.add(tmp);
                }
            }
        }
        return result;
    }
    private boolean isValid(String s){
        int count = 0;
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '('){
                count++;
            }else if(s.charAt(i) == ')'){
                count--;
            }
            if(count < 0){
                return false;
            }
        }
        return count == 0;
    }
}
