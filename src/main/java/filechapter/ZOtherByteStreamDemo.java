package filechapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 其他字节流
 * 
 * @author xuxb
 * 
 */
//
class DataInputDemo {
	void testDataOutput() throws Exception {
		String filename = "src//main//resources//filechapter//DataInput.txt";
		double[] datas = new double[]{1.0, 2.0, 4.0, 5.0, 7, 8, 9, 10};
		OutputStream fout = new FileOutputStream(filename);
		DataOutputStream out = new DataOutputStream(fout);
		out.writeInt(datas.length);
		for (double b : datas) {
			out.writeDouble(b);
		}
		out.close();
		FileConstant.OpenFile(filename);
	}
	
	void testDataInput() throws Exception{
		InputStream fin = new FileInputStream("src//main//resources//filechapter//DataInput.txt");
		StringBuilder sb = new StringBuilder();
		DataInputStream in = new DataInputStream(fin);
		double[] datas = new double[in.readInt()];
		for (int i = 0; i < datas.length; i++){
			datas[i] = in.readDouble();
			sb.append(datas[i]);
		}		
		System.out.println(sb.toString());
		in.close();
	}
}

public class ZOtherByteStreamDemo {

	void testFileDescriptor() throws IOException{
		FileInputStream in = new FileInputStream(FileConstant.READFILEPATH); 
		FileDescriptor fd = in.getFD();
		fd.sync();//强制把文件同步到磁盘
	}
	
	public static void main(String[] args) throws Exception {
		new DataInputDemo().testDataInput();

	}

}
