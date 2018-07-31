package numberchapter;
//
public class JavaBaseType {

	/**
	 * 基本数据类型：
	 * Integer、Long、Byte、Double、Float、Short 都是抽象类
	 * Number的子类。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Number x = 5.0;
		String s = "5.0";
		System.out.println(x.equals(s));
		//NumberXXXValue 转化成对应的类型
		System.out.println(x.doubleValue() + 0.1);
		//String 类型转换
		System.out.println(String.valueOf(s));
		//Int 边界值
		System.out.println(Integer.MAX_VALUE);

	}

}
