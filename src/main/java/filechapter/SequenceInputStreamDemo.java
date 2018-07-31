package filechapter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * SequenceInputStream 读取一个或者多个字节流，创建一个单一
 * 的输入流
 * @author xuxb
 *
 */
public class SequenceInputStreamDemo {

	@SuppressWarnings("resource")
	void testSequence() throws IOException {
		InputStream in, fileIn, bufIn;
		
		List<InputStream> inputs = 
				new ArrayList<InputStream>(100);
		String[] args = {FileConstant.OUTFILEPATH, FileConstant.READFILEPATHGBK, FileConstant.READFILEPATH};
		
		for (String arg: args) {
			fileIn = new FileInputStream(arg);
			bufIn = new BufferedInputStream(fileIn);
			inputs.add(bufIn);
		}
		
		Enumeration<InputStream> files = Collections.enumeration(inputs);
		in = new SequenceInputStream(files);
		
		int ch;
		StringBuilder sb = new StringBuilder();
		while ((ch = in.read()) != -1) {
			sb.append(ch);
		}
		System.out.print(sb.toString());
	}

	
	public static void main(String[] args) throws IOException {
		new SequenceInputStreamDemo().testSequence();
	}

}
