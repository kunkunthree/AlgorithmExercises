package algorithm.leetcode.algorithm;
/*
 * medium
 * 380. Insert Delete GetRandom O(1)
 * Design a data structure that supports all following operations in average O(1) time.

    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements. 
    Each element must have the same probability of being returned.

Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();

 */
import java.util.*;
public class NO380_InsertDeleteGetRandom_O1 {
	//方法1：
	//利用ArrayList把数存起来，利用map把值和下标对应起来
	//当添加新的值时，通过判断是否存在map的key中来决定是否添加，添加到list前则把当前数组长度和数对应起来
	//当删除操作时，判断是否在map的key中来决定是否删除，删除时，把该值与数组最后一个值交换，
	//然后删除最后一个值
	public class RandomizedSet {
	    Map<Integer,Integer> map;
	    List<Integer> list;
	    Random rand;
	    /** Initialize your data structure here. */
	    public RandomizedSet() {
	        map = new HashMap<>();
	        list = new ArrayList<>();
	        rand = new Random();
	    }
	    
	    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	    public boolean insert(int val) {
	        if(map.containsKey(val)){
	            return false;
	        }
	        map.put(val,list.size());
	        list.add(val);
	        return true;
	    }
	    
	    /** Removes a value from the set. Returns true if the set contained the specified element. */
	    public boolean remove(int val) {
	        if(!map.containsKey(val)){
	            return false;
	        }
	        int index = map.get(val);
	        if(index < list.size()-1){
	            int endValue = list.get(list.size()-1);
	            map.put(endValue,index);
	            list.set(index,endValue);
	        }
	        map.remove(val);
	        list.remove(list.size()-1);
	        return true;
	    }
	    
	    /** Get a random element from the set. */
	    public int getRandom() {
	        return list.get(rand.nextInt(list.size()));
	    }
	}
}
