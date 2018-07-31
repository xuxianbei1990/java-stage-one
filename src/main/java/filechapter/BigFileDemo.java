
package filechapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class BigFileDemo {

	public static int count(File file, String charSet, char ch) throws IOException {
		Charset charset = Charset.forName(charSet);
		CharsetDecoder decoder = charset.newDecoder();
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		
		long size = fc.size();
		MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, size);
		CharBuffer cb = decoder.decode(bb);
		int count = 0;
		for (int i = 0; i < size && i < Integer.MAX_VALUE; i++)
			if (cb.charAt(i) == ch)
				count++;
		fc.close();
		return count;
	}
	
	public static void main(String[] args) {
		

	}

}
