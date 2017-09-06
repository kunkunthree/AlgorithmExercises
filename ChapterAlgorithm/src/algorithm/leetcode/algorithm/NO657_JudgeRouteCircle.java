package algorithm.leetcode.algorithm;
/*
 * easy
 * 657. Judge Route Circle
 *  Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, 
 *  which means it moves back to the original place.
 *  The move sequence is represented by a string. And each move is represent by a character. 
 *  The valid robot moves are R (Right), L (Left), U (Up) and D (down). 
 *  The output should be true or false representing whether the robot makes a circle.

Example 1:
Input: "UD"
Output: true

Example 2:
Input: "LL"
Output: false

 */
public class NO657_JudgeRouteCircle {
	//方法1：
	//直接计算坐标
    public boolean judgeCircle(String moves) {
        int x = 0,y = 0;
        for(char c : moves.toCharArray()){
            switch(c){
                case 'D': y--;break;
                case 'U': y++;break;
                case 'L': x--;break;
                case 'R': x++;break;
            }
        }
        return x == 0 && y == 0 ? true : false;
    }
}
