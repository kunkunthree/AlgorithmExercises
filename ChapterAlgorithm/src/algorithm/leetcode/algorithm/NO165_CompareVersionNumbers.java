package algorithm.leetcode.algorithm;
/*
 * medium
 * 165. Compare Version Numbers 
 * Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
 */
public class NO165_CompareVersionNumbers {
	public static void main(String[] args) {
		String s = "abc";
		String[] ss = s.split(".");
		System.out.println(ss.length);
	}
	//方法1：
	//直接法，直接计算每一层的版本号，若该层版本没有数字，则为0
	public int compareVersion(String version1, String version2) {
        int v1 = 0,v2 = 0;
        int i = 0,j = 0;
        int len1 = version1.length(),len2 = version2.length();
        while(i < len1 || j < len2){
            v1 = 0;
            while(i < len1 && version1.charAt(i) != '.'){
                v1 = v1 * 10 + version1.charAt(i) - '0';
                i++;
            }
            i++;
            v2 = 0;
            while(j < len2 && version2.charAt(j) != '.'){
                v2 = v2 * 10 + version2.charAt(j) - '0';
                j++;
            }
            j++;
            if(v1 != v2){
                break;
            }
        }
        if(v1 > v2){
            return 1;
        }else if(v1 < v2){
            return -1;
        }else{
            return 0;
        }
    }
}
