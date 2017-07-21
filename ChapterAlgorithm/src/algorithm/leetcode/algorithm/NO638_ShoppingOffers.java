package algorithm.leetcode.algorithm;
/*
 * medium
 * 638. Shopping Offers 
 *  In LeetCode Store, there are some kinds of items to sell. Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items
 with a sale price.

You are given the each item's price, a set of special offers, and the number we need to buy for each item. 
The job is to output the lowest price you have to pay for exactly certain items as given, 
where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you need to 
pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

Example 1:

Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
Explanation: 
There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.

Example 2:

Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
Output: 11
Explanation: 
The price of A is $2, and $3 for B, $4 for C. 
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
You cannot add more items, though only $9 for 2A ,2B and 1C.

Note:
    1.		There are at most 6 kinds of items, 100 special offers.
    2.		For each item, you need to buy at most 6 of them.
    3.		You are not allowed to buy more items than you want, even if that would lower the overall price.

 */
import java.util.*;
public class NO638_ShoppingOffers {
	public static void main(String[] args) {
		Map<int[],Integer> map1 = new HashMap<int[], Integer>();
		int[] nums1 = new int[]{1,1,1};
		map1.put(nums1, 1);
		int[] nums2 = new int[]{1,1,1};
		map1.put(nums2, 2);
		System.out.println(map1.get(nums1));
		System.out.println(map1.get(nums2));
		
		Map<List<Integer>,Integer> map2 = new HashMap<List<Integer>, Integer>();
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(1);
		map2.put(list1, 1);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(1);
		map2.put(list2, 2);
		System.out.println(map2.get(list1));
		System.out.println(map2.get(list2));
		
	}
	//方法1：
	//dp+dfs，动态规划+深度优先搜寻
	//用一个map记录所有needs所对应的最小值
	//当map中已经有某个needs时，返回这个need对应的值
	//当map中没有：
	//初始化res = Integer.MAX_VALUE
	//			遍历所有special need：
	//					当购买一个special offer切没有超过needs时，则创建一个新的need，
	//					则选择该special offer所需要的花费+dfs新的need所得的值与res比较得到最小值
	//			考虑不使用special offer的情况：
	//					全部都使用各个item各自的价格得到noSpecial的花费，与res比较得到最小值
	//设置当前needs对应res
	//返回res
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if(price == null || price.size() == 0){
            return 0;
        }
        Map<List<Integer>,Integer> dp = new HashMap<>();
        List<Integer> allZero = new ArrayList<>();
        for(int i=0;i<needs.size();i++) {
            allZero.add(0);
        }
        dp.put(allZero, 0);
        return dfs(needs,price,special,dp);
    }
    private int dfs(List<Integer> needs, List<Integer> price, List<List<Integer>> special, Map<List<Integer>,Integer> dp){
        if(dp.containsKey(needs)){
            return dp.get(needs);
        }
        int res = Integer.MAX_VALUE;
        for(List<Integer> s : special){
            List<Integer> needsCopy = new ArrayList<>(needs);
            boolean isValid = true;
            for(int i = 0 ; i < needs.size() ; i++){
                needsCopy.set(i,needsCopy.get(i) - s.get(i));
                if(needsCopy.get(i) < 0){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                res = Math.min(res, s.get(needs.size()) + dfs(needsCopy, price, special, dp));
            }
        }
        int noSpecial = 0;
        for(int i = 0 ; i < needs.size() ; i++){
            noSpecial+=price.get(i)*needs.get(i);
        }
        res = Math.min(res,noSpecial);
        dp.put(needs,res);
        return res;
    }
    //方法2：
    //
    public int shoppingOffers2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shopping(price,special,needs,0);
    }
    public int shopping(List<Integer>price,List<List<Integer>> special, List<Integer> needs,int i)
    {
        if(i==special.size())
            return dot(needs,price);
        ArrayList<Integer> clone=new ArrayList<>(needs);
        int j=0;
        for(j=0;j<special.get(i).size()-1;j++)
        {
            int diff=clone.get(j)-special.get(i).get(j);
            if(diff<0)
                break;
            clone.set(j,diff);
        }
        
        if(j==special.get(i).size()-1)
        {
//        	System.out.println("hi");
            return Math.min(special.get(i).get(j)+shopping(price,special,clone,i), shopping(price,special,needs,i+1));
        }
        else
            return shopping(price,special,needs,i+1);
    }
    public int dot(List<Integer> a,List<Integer> b)
    {
        int sum=0;
        for(int i=0;i<a.size();i++)
        {
           sum+=a.get(i)*b.get(i);
        }
        return sum;
    }
}
