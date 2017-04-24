package algorithm.leetcode.algorithm;
/*
 * medium
 * 75. Sort Colors
 *  Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 *   with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem. 
 */
public class NO75_SortColors {
	public static void main(String[] args) {
		int[] nums = new int[]{0};
		sortColors2(nums);
	}
	//方法1：
	//计数法，统计0,1,2分别的个数，再根据个数给数组赋值
    public void sortColors(int[] nums) {
        int[] color = new int[3];
        for(int i = 0 ; i < nums.length ; i++){
            switch(nums[i]){
                case 0: color[0]++;break;
                case 1: color[1]++;break;
                case 2: color[2]++;break;
            }
        }
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = 0 ; j < color.length ; j++){
                if(color[j] > 0){
                    nums[i] = j;
                    color[j]--;
                    break;
                }
            }
        }
    }
    //方法2：
    //假设排序后的数组是0的个数为i，1的个数为j，2的个数为k
    //可以把数组看作是在都是2的长度为i+j+k的数组上覆盖长度为i+j的1的数组，然后再覆盖长度为i的1的数组
    //那么我们遍历一遍时，遇到0时，0,1,2长度+1，遇到1时，1,2长度+1，遇到2时，2的长度+1
    //先对2进行操作，再对1进行操作，再对0进行操作，因为大的数总在小的数多，
    //所以先对大的数操作，小的数才不会被比它大的数覆盖
    //每次操作都可以看作是刷漆，排在前面的2被0和1刷掉，排在前面的1被0刷掉。
    public static void sortColors2(int[] nums) {
        int length = nums.length,tmp;
        int[] color = new int[3];
        for(int i = 0 ; i < length ; i++){
            tmp = nums[i];
            for(int j = color.length-1 ; j >= tmp ; j--){
                nums[color[j]++] = j;
            }
        }
    }
    //方法3：
    //用3个指针，left,mid,right,
    //mid用于扫描，left和right用于指示下一个放0或2的位置
    //当mid扫到0，且left！=mid时，交换left和mid所在的数，
    //交换后，left指针往右移，mid不动，因为交换后的数可能为2
    //同理，当mid扫到2，且right！=mid时，
    //交换right和mid所在的数，right指针往左移，mid不动，因为交换后的数可能为0
    //其他情况不交换，mid继续往后扫描，直到mid比right大的时候结束
    public void sortColors3(int[] nums) {
        int length = nums.length,tmp;
        int left = 0,right = length-1;
        for(int mid = 0 ; mid <= right ; mid++){
            if(nums[mid] == 0 && mid != left){
                tmp = nums[mid];
                nums[mid] = nums[left];
                nums[left] = tmp;
                left++;mid--;
            }else if(nums[mid] == 2 && mid != right){
                tmp = nums[mid];
                nums[mid] = nums[right];
                nums[right] = tmp;
                right--;mid--;
            }
        }
    }
}
