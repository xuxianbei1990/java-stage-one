package inside.chapter;
//113
import java.util.Iterator;
import java.util.NoSuchElementException;

interface SharedData{
	int x =1;
	class Data{
		private int x = 0;
		public int getX(){
			return x;
		}
		public void setX(int newX){
			x = newX;
		}
	}
	Data data = new Data();
}

public class IteratorDemo {
	public static Iterator<Object> walkThrough(final Object[] objs){
		
		class Iter implements Iterator<Object>{
			private int pos = 0;
			public boolean hasNext(){
				return (pos < objs.length);
			}
			public Object next() throws NoSuchElementException{
				if (pos >= objs.length)
					throw new NoSuchElementException();
				return objs[pos++];
			}
			public void remove(){
				throw new UnsupportedOperationException();
			}
		}
		return new Iter();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
