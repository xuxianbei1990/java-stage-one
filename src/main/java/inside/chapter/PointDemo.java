package inside.chapter;
//93
/*
 * Cloneable Comparable Runnable
 */

import java.util.HashMap;

interface X {
	int val = 1;
}

interface Y extends X {
	int val = 2;
	int sum = val + X.val;
}

class Z implements Y{
	public void test(){
	  System.out.println("z.val=" + Z.val + ",z.sum=" + Z.sum);
	}
}

interface Comparable<T>{
	int compareTo(T obj);
}


class Point implements Comparable<Point>{
	public int compareTo(Point p){
		return 0;
	}
	public int compareTo(String obj){
		return 0;
	}
}
public class PointDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Point p = new Point();
		Z z = new Z();
		z.test();

	}

}
