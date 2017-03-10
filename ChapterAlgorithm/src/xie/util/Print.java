package xie.util;
import java.io.*; 
public class Print {
	static String s = "print";
	//System.out
	public static void println(Object obj){
		System.out.println(obj);
	}
	public static void println(){
		System.out.println();
	}
	public static void print(Object obj){
		System.out.print(obj);
	}
	public static PrintStream printf(String format,Object...args){
		return System.out.printf(format, args);
	}
	//System.err
	public static void errln(Object obj){
		System.err.println(obj);
	}
	public static void errln(){
		System.err.println();
	}
	public static void err(Object obj){
		System.err.print(obj);
	}
	public static PrintStream err(String format,Object...args){
		return System.err.printf(format, args);
	}
}
