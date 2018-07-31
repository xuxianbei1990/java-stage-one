package filechapter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.List;

/**
 * 其他字符流
 * 
 * @author xuxb
 *
 */

//符号化输入文本 只能识别Unicode 编码
class StreamTokenizerDemo {
	StreamTokenizer in;
	StringBuilder sb;
	int c;
	{
		sb = new StringBuilder();
		// 扫描一个单词
		c = StreamTokenizer.TT_WORD;
		// 扫描一个数字  扫描最大值是9999 
		c = StreamTokenizer.TT_NUMBER;
		// 扫描到行末
		c = StreamTokenizer.TT_EOF;
		// 扫描到文件末尾
		c = StreamTokenizer.TT_EOF;
	}
	
	void sumStream() throws IOException {
		Reader source = new FileReader("src//main//resources//filechapter//StreamTokenizer.txt");
		in = new StreamTokenizer(source);
		double result = 0.0;
		
		while(in.nextToken() != StreamTokenizer.TT_EOF) {
			if (in.ttype == StreamTokenizer.TT_NUMBER) {
				sb.append("---"+ in.nval);
				result += in.nval;
			}
		}
		System.out.println(sb);
		System.out.println(result);
	}
	
	void readAttrs() throws IOException {
		Reader source = new FileReader("src//main//resources//filechapter//StreamTokenizer.txt");
		in = new StreamTokenizer(source);
		List<String> list = new LinkedList<String>();
		in.commentChar('#');
		in.ordinaryChar('/');
		in.wordChars(1, 2);
		
		while(in.nextToken() != StreamTokenizer.TT_EOF) {
			if (in.ttype == StreamTokenizer.TT_WORD) {
				list.add(in.sval);
			} else if (in.ttype == '=') {
				throw new IOException("无效 attr name");
			}
		}
		System.out.println(list.toString());
		
	}
	
}

public class ZOtherCharStreamDemo {

	public static void main(String[] args) throws IOException {
		StreamTokenizerDemo std = new StreamTokenizerDemo();
		std.readAttrs();

	}

}
