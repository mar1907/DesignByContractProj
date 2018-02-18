package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Observable;

public class Account extends Observable implements Serializable{
	private static final long serialVersionUID = -547216657055809011L;
	private static Integer idAvailable;
	protected Integer id;
	protected int amount;
	
	public Account(){
		id=idAvailable++;
		amount=0;
	}
	
	public Account(Integer id){
		this.id=id;
		amount=0;
	}
	
	/**
	 * @return the idAvailable
	 */
	public static final Integer getIdAvailable() {
		return idAvailable;
	}

	/**
	 * @param idAvailable the idAvailable to set
	 */
	public static final void setIdAvailable(Integer idAvailable) {
		Account.idAvailable = idAvailable;
	}

	public Account(Integer id, int amount){
		this.id=id;
		this.amount=amount;
	}
	
	public void add(int amount) throws SavingAccountModificationException{
		this.amount+=amount;
		setChanged();
		notifyObservers(amount+" has been added to account "+id);
	}
	
	/**
	 * @return the amount
	 */
	public final int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public final void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the id
	 */
	public final Integer getId() {
		return id;
	}

	/**
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Account))return false;
	    Account otherAccount = (Account)other;
	    return otherAccount.id.equals(id);
	}
	
	public String toString(){
		return id.toString();
	}

}
