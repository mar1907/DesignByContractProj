package model;

public class SpendingAccount extends Account {
	private static final long serialVersionUID = -1813177363962013408L;
	
	public SpendingAccount(Integer id, int amount){
		super(id,amount);
	}
	
	public SpendingAccount(){
		super();
	}
	
	public boolean withdraw(int amount){
		if(this.amount>=amount){
			this.amount-=amount;
			setChanged();
			notifyObservers(amount+" has been subtracted from account "+id);
			return true;
		}
		else{
			return false;
		}
	}
}
