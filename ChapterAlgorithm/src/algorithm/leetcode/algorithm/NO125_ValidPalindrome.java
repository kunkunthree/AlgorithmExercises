package algorithm.leetcode.algorithm;

/*
 * easy
 * 125. Valid Palindrome 
 *  Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome. 
 */
public class NO125_ValidPalindrome {
	public static void main(String[] args) {
		System.out.println((int) 'a' + " " + (int) 'A' + " " + (int) '0');
	}
    public boolean isPalindrome(String s) {
        if(s == null || s.length() <= 1){
            return true;
        }
        char[] array = s.toCharArray();
        int left = 0,right = s.length()-1;
        int x = 'a'-'A';
        while(left < right){
            while(left < right && !isAlphaNumeric(array[left])){
                left++;
            }
            while(left < right && !isAlphaNumeric(array[right])){
                right--;
            }
            if(left < right && adjust(array[left]) != adjust(array[right])){
                return false;
            }
            right--;
            left++;
        }
        return true;
    }
    public boolean isAlphaNumeric(char c){
        if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9'){
            return true;
        }
        return false;
    }
    //如果是小写字母，则改为大写字母，如果是数字，则不处理
    public char adjust(char c){
        if(c >= 'a' && c <= 'z'){
            c = (char)((int)c - 32);
        }
        return c;
    }
    //用正则表达式过滤，但是花费时间更多
    public boolean isPalindrome2(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}
