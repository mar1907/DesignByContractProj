package modelLogic;

import java.util.ArrayList;
import java.util.HashSet;

import model.Account;
import model.Person;
import model.SavingAccountModificationException;

public interface BankProc {
	
	/**
	 * Method to add a Person object to a hashSet of Person objects.
	 * Also creates an ArrayList of client objects for the person in an
	 * associated hashMap
	 * 
	 * @param p the Person object to be added
	 * @precondition p!=null
	 * @post hashSet.size()==hashSet.size() @precondition +1
	 * @post hashSet.contains(p)==true
	 * @post hashMap.get(p)!=null
	 */
	public void addPerson(Person p);
	
	/**
	 * Method to remove a Person object from a hashSet of Person objects.
	 * Also removes its associated Account objects from the hashMap
	 * 
	 * @param p the Person to be removed
	 * @precondition p!=null
	 * @precondition hashSet.contains(p)!=false
	 * @precondition hashMap.get(p)!=null
	 * @post hashSet.size()==hashSet.size() @precondition -1
	 * @post hashSet.contains(p)==false
	 * @post hashMap.get(p)==null
	 */
	public void removePerson(Person p);
	
	/**
	 * Updates a Person object in a hashSet
	 * 
	 * @param p the Person object to be updated
	 * @precondition p!=null
	 * @precondition hashSet.contains(p)!=false
	 * @post hashSet.size()==hashSet.size() @precondition
	 * @post hashSet.contains(p)==true
	 */
	public void updatePerson(Person p);
	
	/**
	 * Adds a new Account object to a Person object in the hashMap 
	 * 
	 * @param a the Account object to be added
	 * @param p the target Person object
	 * @precondition p!=null
	 * @precondition hashSet.contains(p)==false
	 * @precondition a!=null
	 * @precondition hashMap.get(p).contains(a)==false
	 * @post hashMap.get(p).size()==hashMap.get(p).size() @precondition +1
	 * @post hashMap.get(p).contains(a)==true
	 */
	public void addAccount(Account a, Person p);
	
	/**
	 * Removes an Account object attached to a Person object from the hashMap
	 * 
	 * @param a the Account object to be removed
	 * @param p the target Person object
	 * @precondition p!=null
	 * @precondition hashSet.contains(p)!=false
	 * @precondition a!=null
	 * @precondition hashMap.get(p).contains(a)!=false
	 * @post hashMap.get(p).size()==hashMap.get(p).size() @precondition -1
	 * @post hashMap.get(p).contains(a)==false
	 */
	public void removeAccount(Account a, Person p);
	
	/**
	 * Updates an Account object attached to a Person object in the hashMap
	 * 
	 * @param a the Account object to be updated
	 * @param p the target Person object
	 * @precondition p!=null
	 * @precondition hashSet.contains(p)!=false
	 * @precondition a!=null
	 * @precondition hashMap.get(p).contains(a)!=false
	 * @post hashMap.get(p).size()==hashMap.get(p).size() @precondition
	 * @post hashMap.get(p).contains(a)==true
	 */
	public void updateAccount(Account a, Person p);
	
	/**
	 * Adds an amount of money to an account with accountId of a Person object in the hashMap
	 * 
	 * @param accountId the id of the account where money has to be added
	 * @param p the Person object associated with the account
	 * @param amount the amount to be added
	 * @throws SavingAccountModificationException
	 * @precondition p!=null
	 * @precondition hashSet.contains(p)!=false
	 * @precondition hashMap.get(p).contains(id)!=false
	 * @post hashMap.get(p).get(id).amount()==hashMap.get(p).get(id).amount() @precondition +amount
	 */
	public void addMoney(int accountId, Person p, int amount) throws SavingAccountModificationException;
	
	/**
	 * Withdraw an amount of money from an account with accountId of a Person object in the hashMap
	 * 
	 * @param accountId the id of the account from where money has to be subtracted
	 * @param p the Person object associated with the account
	 * @param amount the amount to be subtracted
	 * @return true if the withdrawal is successful, false otherwise
	 * @throws SavingAccountModificationException
	 * @precondition p!=null
	 * @precondition hashSet.contains(p)!=false
	 * @precondition hashMap.get(p).contains(id)!=false
	 * @post hashMap.get(p).get(id).amount()==hashMap.get(p).get(id).amount() @precondition -amount
	 */
	public boolean withdraw(int accountId, Person p, int amount) throws SavingAccountModificationException;
	
	/**
	 * Get the hashSet of all Person objects in the bank
	 * 
	 * @return the hashSet of all Person objects in the bank
	 * @precondition true
	 * @post @no change
	 */
	public HashSet<Person> getPersons();
	
	/**
	 * Get the ArrayList associated to the Person object in the hashMap
	 * 
	 * @param p the target Person object
	 * @return the ArrayList associated to the Person object in the hashMap
	 * @precondition p!=null
	 * @precondition hashSet.contains(p)!=false
	 * @post @no change
	 */
	public ArrayList<Account> getAccountsOfPerson(Person p);
}
