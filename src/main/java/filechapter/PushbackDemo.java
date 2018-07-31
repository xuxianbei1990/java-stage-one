package filechapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/** 回退流
 * @author xuxb
 *
 */
public class PushbackDemo {

	void testPushback() throws IOException {
		String str = "www.baidu.com"; // 定义字符串
		ByteArrayInputStream bai = null; // 定义内存输入流
		bai = new ByteArrayInputStream(str.getBytes()); // 实例化内存输入流
		PushbackInputStream in = new PushbackInputStream(bai);
		System.out.print("读取之后的数据为：");
		int temp = 0;
		while ((temp = in.read()) != -1) { // 读取内容
			if (temp == '.') { // 判断是否读取到了“.”
				in.unread(temp); // 放回到缓冲区之中
				temp = in.read(); // 再读一遍
				System.out.print("（退回" + (char) temp + "）");
			} else {
				System.out.print((char) temp); // 输出内容
			}
		}

	}

	public static void main(String[] args) throws IOException {
		PushbackDemo pd = new PushbackDemo();
		pd.testPushback();

	}

}
