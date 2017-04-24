package algorithm.leetcode.algorithm;

/*
 * east
 * 401. Binary Watch 
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), 
 * and the 6 LEDs on the bottom represent the minutes (0-59).

 Each LED represents a zero or one, with the least significant bit on the right.

 For example, the above binary watch reads "3:25".

 Given a non-negative integer n which represents the number of LEDs that are currently on, 
 return all possible times the watch could represent.

 Example:

 Input: n = 1
 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

 Note:

 The order of output does not matter.
 The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

 */
import java.util.*;

public class NO401_BinaryWatch {
	public List<String> readBinaryWatch(int num) {
		List<String> result = new ArrayList<String>();
		int[] h = new int[] { 1, 2, 4, 8 };
		int[] m = new int[] { 1, 2, 4, 8, 16, 32 };
		for (int i = 0; i <= num; i++) {
			List<Integer> hl = readBinaryWatchHelper(i, 0, 0, h);
			List<Integer> ml = readBinaryWatchHelper(num - i, 0, 0, m);
			for (int tmpH : hl) {
				if (tmpH >= 12) {
					continue;
				}
				for (int tmpM : ml) {
					if (tmpM >= 60) {
						continue;
					}
					if (tmpM < 10) {
						result.add(tmpH + ":0" + tmpM);
					} else {
						result.add(tmpH + ":" + tmpM);
					}
				}
			}
		}
		// Collections.sort(result);
		return result;
	}

	public List<Integer> readBinaryWatchHelper(int time, int sum, int pos,
			int[] array) {
		List<Integer> list = new ArrayList<Integer>();
		if (time == 0) {
			list.add(sum);
			return list;
		}
		for (int i = pos; i < array.length; i++) {
			for (int e : readBinaryWatchHelper(time - 1, sum + array[i], i + 1,
					array)) {
				list.add(e);
			}
		}
		return list;
	}

	// 通过预处理，得到处理速度最大
	String[][] hour = { { "0" }, { "1", "2", "4", "8" },
			{ "3", "5", "6", "9", "10" }, { "7", "11" } };
	String[][] minute = {
			{ "00" }, // 1
			{ "01", "02", "04", "08", "16", "32" }, // 6
			{ "03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33",
					"34", "36", "40", "48" }, // 15
			{ "07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35",
					"37", "38", "41", "42", "44", "49", "50", "52", "56" }, // 20
			{ "15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53",
					"54", "57", "58" }, // 14
			{ "31", "47", "55", "59" } }; // 4

	public List<String> readBinaryWatch2(int num) {
		List<String> ret = new ArrayList();
		for (int i = 0; i <= 3 && i <= num; i++) {
			if (num - i <= 5) {
				for (String str1 : hour[i]) {
					for (String str2 : minute[num - i]) {
						ret.add(str1 + ":" + str2);
					}
				}
			}
		}
		return ret;
	}
	
	//穷举法
	public List<String> readBinaryWatch3(int num) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    result.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return result;
    }
}
