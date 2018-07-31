package numberchapter;

import java.io.File;

import org.apache.commons.lang3.ArrayUtils;

public class StringDemo {

	// 文件截取从0到最后一个小数点。然后按照“_”分组
	private String[] parseFileName(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf(".")).split("_");
	}

	// 创建目录
	private void createDirectory(String[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("D:\\upload\\").append(array[0]).append(File.separator).append(array[1]).append(File.separator);

		String directoryPath = sb.toString();

		File directory = new File(directoryPath);
		if (!directory.exists())
			directory.mkdirs();
	}

	// 检索字符串
	private void indexofString() {
		// String str = "dstcter";
		// if (str.indexOf('c', 3) > -1){
		// System.out.print('1');
		// }
		String str = "rdgrde-1";
		int isub = str.indexOf("-");
		if (isub > 0) {
			System.out.println(isub + str.substring(0, isub));
		}
	}

	// 字符串是否和string某部分一样
	void regionMatches() {
		String str = "Look, look";
		boolean b1, b2;

		b1 = str.regionMatches(6, "Look", 0, 4);
		b2 = str.regionMatches(true, 6, "Look", 0, 4);
		System.out.print(b1 + ":" + b2);
	}
	
	//String分组
	void split(){
		String[] result = ("Kevin has seen《LEON》seveal times,because it is a good film./" +
		"/凯文已经看过《这个杀手不太冷》几次了，因为它是一部好电影。" +
		"/名词:凯文。").split("/");
		for (String str: result)
			System.out.println(str);
	}

	/*
	 * 测试是否是数值
	 */
	void testIsNum() {
		String str = "123";
		if (str.matches("^(-?\\d+)(\\.\\d+)?$")) {
			System.out.print("数值");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringDemo sd = new StringDemo();
		// String[] strings =
		// sd.parseFileName("11010266050209X_201705_00_V2_20170620101809791.1.143_.zip");
		// for (String string : strings) {
		// System.out.println(string + "--Count:" + strings.length);
		// }
		// sd.createDirectory(strings);
		// sd.indexofString();
		 sd.split();

	}

}
