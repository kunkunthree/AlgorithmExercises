package algorithm.leetcode.algorithm;
/*
 * easy
 * 551. Student Attendance Record I 
 * You are given a string representing an attendance record for a student. 
 * The record only contains the following three characters:

    'A' : Absent.
    'L' : Late.
    'P' : Present.

A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) 
or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True

Example 2:
Input: "PPALLL"
Output: False

 */
public class NO551_StudentAttendanceRecordI {
	//方法1：
	//直接计数
	public boolean checkRecord(String s) {
        int countA = 0,countCL = 0,n = s.length();
        boolean continuousL = false;
        char c,pre = '0';
        for(int i = 0 ; i < n ; i++){
            c = s.charAt(i);
            if(c == 'A' && countA++ > 0){
                return false;
            }else if(c == 'L'){
                if(pre == 'L'){
                    if(countCL++ > 1){
                        return false;
                    }
                }else{
                    countCL = 1;
                }
            }
            pre = c;
        }
        return true;
    }
	//方法2：
	public boolean checkRecord2(String s) {
        int countA = 0,n = s.length();
        char c;
        for(int i = 0 ; i < n ; i++){
            c = s.charAt(i);
            if(c == 'A' && countA++ > 0){
                return false;
            }else if(c == 'L' && i >= 2 && s.charAt(i-1) == 'L' && s.charAt(i-2) == 'L'){
                return false;
            }
        }
        return true;
    }
}
