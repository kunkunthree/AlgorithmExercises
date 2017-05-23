package xie.util;
/*
 * 进程控制：
 * 		一项常见的任务是运行程序，并将产生的输出发送到控制台。这个包中含有一个可以简化这项任务
 * 的实用工具。在使用这个工具时，可能会产生2种错误：普通的导致异常的错误——对这些错误我们只
 * 需要重新抛出一个运行时异常，以及进程自身的执行过程中产生的错误，我们希望用单独的异常来报告这些错误：
 * 
 */
public class OSExecuteException extends RuntimeException{
		public OSExecuteException(String why) {
			super(why);
		}
}
