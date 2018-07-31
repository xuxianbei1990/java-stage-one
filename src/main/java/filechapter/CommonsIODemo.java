package filechapter;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class CommonsIODemo {

	public static void main(String[] args) throws IOException {
		IOUtils.toInputStream("123");
	}

}
