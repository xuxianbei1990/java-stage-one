package sundry;

import java.util.Iterator;
import java.util.Map;

//http://www.cnblogs.com/jianlun/p/4773711.html
public class SystemVariable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = System.getenv();
        for(Iterator<String> itr = map.keySet().iterator();itr.hasNext();){
            String key = itr.next();
            System.out.println(key + "=" + map.get(key));
        } 

	}

}
