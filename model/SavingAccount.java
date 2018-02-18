package model;

public class SavingAccount extends Account {
	private static final long serialVersionUID = 5932385243998392241L;
	private double interest;
	
	public SavingAccount(double interest){
		super();
		this.interest=interest;
	}
	
	public SavingAccount(Integer id, int amount, double interest){
		super(id,amount);
		this.interest=interest;
	}
	
	public void add(int sum) throws SavingAccountModificationException{
		if(amount==0){
			amount=sum;
			setChanged();
			notifyObservers(amount+" has been added to account "+id+" with interest "+interest);
			amount+=interest*sum;
		}
		else{
			throw new SavingAccountModificationException();
		}
	}
	
	/**
	 * @return the interest
	 */
	public final double getInterest() {
		return interest;
	}

	public void withdraw() throws SavingAccountModificationException{
		if (amount!=0) {
			setChanged();
			notifyObservers(amount+"has been subtracted from account "+id);
			amount = 0;
		}
		else{
			throw new SavingAccountModificationException();
		}
	}
	
	
}
