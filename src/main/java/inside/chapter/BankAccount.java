package inside.chapter;

//110
class BankAccountDemo {
	private long number;
	private long balance;
	private int x;
	private Action lastAct;
	
	public static class Permissions{
		public boolean canDeposit, canWithdraw, canClose;
	}
	public class Action{
		private String act;
		private long amount;
		Action(String act, long amount){
			BankAccountDemo.this.x++;
			this.act = act;
			this.amount = amount;
			
		}
		public String toString(){
			return number + ":" + act + " " + amount;
		}
		public void deposit(long amount){
			balance += amount;
			lastAct = new Action("deposit", amount);
		}
	}

}
 public class BankAccount{
	 public static void main(String[] args){
		 BankAccountDemo.Permissions p = new BankAccountDemo.Permissions();
		 //不能这么写，因为一个类只能有一个public class；
//		 a = new BankAccountDemo.Action("123", 2);
		 
	 }
 }
