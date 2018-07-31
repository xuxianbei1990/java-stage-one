package sundry;

import java.lang.*;

public class ConditionBreakpoint {
	private String testId;

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}
	public void testDropFrame(){
		String name;
		String address;
		name = "aget";
		address = "cc";
		System.out.print(name + address);
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		ConditionBreakpoint cbp = new ConditionBreakpoint();
		cbp.testId = "2";
		cbp.testDropFrame();
		String str = null;
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			
			
			if (i == 5) {
				str = "223";
				str.equals("1");
				
			}
			else
			if (i > 6){
				str.equals("2");
//				NullPointerException e = new NullPointerException();
//				throw e;
			}
				
			

		}
		System.out.println("End");
	}

}
