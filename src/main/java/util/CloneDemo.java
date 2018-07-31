package util;

/* jdk 1.8
 * clone 复制一份和源对象一样的独立对象内存块
 * 
 * 对象拷贝实现Cloneable接口，基本类型可以直接拷贝 ;引用类型只做引用的拷贝
 * (八种基本类型。六种数字类型（四个整数型（默认是int 型），两个浮点型（默认是double 型）），
 * 一种字符类型，还有一种布尔型。) 外加String
 * 
 * 对象深拷贝： 如果深拷贝需要实现该对象clone接口，并且在宿主类主动调用clone
 */
class ObjVO {
	String str;
}

class DeepCloneObjVO implements Cloneable {
	String str;

	public DeepCloneObjVO clone() {
		try {
			return (DeepCloneObjVO) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}

class IntegerStack implements Cloneable {
	private int[] buffer;
	private int top;
	private int testInt;
	String Name;
	ObjVO title = new ObjVO();
	DeepCloneObjVO dc = new DeepCloneObjVO();

	public IntegerStack(int maxContents) {
		buffer = new int[maxContents];
		top = -1;
	}

	public void push(int val) {
		buffer[++top] = val;
	}

	public int pop() {
		return buffer[top--];
	}

	public IntegerStack clone() {
		try {
			IntegerStack nObj = (IntegerStack) super.clone();
			nObj.buffer = buffer.clone();
			nObj.dc = dc.clone();
			return nObj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buffer.length; i++) {
			sb.append(buffer[i]);
		}
		sb.append(";int类型: " + testInt).append(";实现clone对象拷贝dc：" + dc.str)
		.append(";应用类型title:" + title.str).append(";字符串:" + Name)
				.append(";深拷贝对象类型:" + dc.str);
		return sb.toString();
	}

	public int getTestInt() {
		return testInt;
	}

	public void setTestInt(int testInt) {
		this.testInt = testInt;
	}
}

public class CloneDemo {
	public static void main(String[] args) {
		IntegerStack first = new IntegerStack(2);
		first.push(2);
		first.push(9);
		first.setTestInt(18);
		first.title.str = "sky";
		first.Name = "暗夜";
		first.dc.str = "sky";
		IntegerStack second = first.clone();
		System.out.println("对象拷贝成功：");
		System.out.println("first:" + first);
		System.out.println("second:" + second);
		second.dc.str = "moon";
		second.title.str = "moon";
		second.Name = "死灵";
		second.setTestInt(19);
		System.out.println("基本类型拷贝和应用类型拷贝：");
		System.out.println("first:" + first);
		System.out.println("second:" + second);
	}

}
