package algorithm.leetcode.algorithm;
/*
 * easy
 * 190. Reverse Bits
 * Reverse bits of a given 32 bits unsigned integer.
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
 * return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
 */
import java.util.*;
public class NO190_ReverseBits {
	public static void main(String[] args) {
		System.out.println(reverseBits(0));
	}
    public static int reverseBits(int n) {
        int x = 0xFFFFFFFF;
        int bit1 = 1,bit2 = 1<<31;
        for(int i = 0 ; i < 32 ; i++){
            if((bit1&n) == 0){
                x = x & (~bit2);
            }
            bit1<<=1;
            bit2>>>=1;
        }
        return x;
    }
    //更好的写法，减少操作数
    public int reverseBits2(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;   // CATCH: must do unsigned shift
            if (i < 31) // CATCH: for last digit, don't shift!
                result <<= 1;
        }
        return result;
    }
    //如果经常调用该函数，则进行优化缓存
    private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
    public int reverseBits3(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) // convert int into 4 bytes
            bytes[i] = (byte)((n >>> 8*i) & 0xFF);
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]); // reverse per byte
            if (i < 3)
                result <<= 8;
        }
        return result;
    }

    private int reverseByte(byte b) {
        Integer value = cache.get(b); // first look up from cache
        if (value != null)
            return value;
        value = 0;
        // reverse by bit
        for (int i = 0; i < 8; i++) {
            value += ((b >>> i) & 1);
            if (i < 7)
                value <<= 1;
        }
        cache.put(b, value);
        return value;
    }
}
