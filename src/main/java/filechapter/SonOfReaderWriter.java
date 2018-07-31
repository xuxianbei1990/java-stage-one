package filechapter;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;

public class SonOfReaderWriter {
	
	/*
	 * BufferedReader 的子类 ,在readLine基础上，多了一个getLineNumber()
	 * 方法，返回当前行号
	 */
	private LineNumberReader LNR;
	
	private CharArrayReader ca;
	
	private ByteArrayInputStream br;
	
	public static void main(String[] args) {

	}

}
