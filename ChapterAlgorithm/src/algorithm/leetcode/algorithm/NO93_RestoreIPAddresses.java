package algorithm.leetcode.algorithm;
/*
 * medium
 * 93. Restore IP Addresses
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter) 
 */
import java.util.*;
public class NO93_RestoreIPAddresses {
	public static void main(String[] args) {
		String s = "010010";
		System.out.println(restoreIpAddresses2(s));
	}
	//方法1：
	//递归,DFS
	public List<String> restoreIpAddresses(String s) {
        return restoreIpAddressesHelper(s,"",0,0);
    }
    public List<String> restoreIpAddressesHelper(String s,String tmpS,int count,int index){
        List<String> list = new ArrayList<String>();
        if(count == 4){
            if(index == s.length()){
                list.add(tmpS);
                return list;
            }else{
                return new ArrayList<String>();
            }
        }
        int num = 0;
        if(count > 0){
            tmpS+=".";
        }
        for(int i = index ; i < index+3 && i < s.length(); i++){
            num=num * 10 + s.charAt(i)-'0';
            if(i > index && num < 10 || num > 255){
                break;
            }
            for(String str : restoreIpAddressesHelper(s,tmpS+num,count+1,i+1)){
                list.add(str);
            }
        }
        return list;
    }
    
    //方法2：
    //迭代
    public static List<String> restoreIpAddresses2(String s) {
        List<String> list = new ArrayList<String>();
        int num1 = 0 ,num2 = 0 ,num3 = 0 ,num4 = 0;
        for(int i = 0 ; i < i+3 && i < s.length(); i++){
            num1 = num1* 10 + s.charAt(i)-'0';
            num2 = 0;
            if(i > 0 && num1 < 10 || num1 > 255)break;
            for(int j = i+1 ; j < i+4 && j < s.length(); j++){
                num2= num2* 10 + s.charAt(j)-'0';
                num3 = 0;
                if(j > i+1 && num2 < 10 || num2 > 255)break;
                for(int k = j+1 ; k < j+4 && k < s.length(); k++){
                    num3= num3* 10 + s.charAt(k)-'0';
                    num4 = 0;
                    if(k > j+1 && num3 < 10 || num3 > 255)break;
                    for(int l = k+1 ; l < k+4 && l < s.length() ; l++){
                        num4= num4* 10 + s.charAt(l)-'0';
                        if(l > k+1 && num4 < 10 || num4 > 255)break;
                        if(l+1 == s.length()){
                            list.add(num1+"."+num2+"."+num3+"."+num4);
                        }
                    }
                }
            }
        }
        return list;
    }
}
