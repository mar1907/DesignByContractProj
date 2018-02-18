package modelLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JTextArea;

import model.Account;
import model.Person;
import model.SavingAccount;
import model.SavingAccountModificationException;
import model.SpendingAccount;
import printables.TextAreaPrintable;

/**
 * @invariant wellFormed()
 */
public class Bank implements BankProc, Serializable {
	private static final long serialVersionUID = 1293472328341143164L;
	private TextAreaPrintable printable;
	private HashSet<Person> persons;
	private HashMap<Person,ArrayList<Account>> bank;
	
	public Bank(){
		printable=new TextAreaPrintable();
		persons=new HashSet<Person>();
		bank=new HashMap<Person,ArrayList<Account>>();
		
	}
	
	public void addAllObservable(){
		for(Person p:persons){
			for(Account a:bank.get(p)){
				a.addObserver(p);
			}
		}
		
	}
	
	public void addPerson(Person p) {
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		int size=persons.size();
		
		persons.add(p);
		p.addPrintable(printable);
		bank.put(p, new ArrayList<Account>());
		ArrayList<String> al=new ArrayList<String>();
		//Comparator<String>
		//al.sort();
		Collections.sort(al,new Comparator<String>(){

			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		});
		String str="ala";
		str.contains("al");
		
		assert persons.size()==size+1 :"Size not changed";
		assert persons.contains(p) :"HashSet not updated";
		assert bank.get(p)!=null :"Null entry in hashMap";
		assert wellFormed() :"Not well formed";
	}

	public void removePerson(Person p) {
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		assert persons.contains(p) :"Person not present";
		assert bank.get(p)!=null :"Null entry in hashMap";
		int size=persons.size();
		
		bank.remove(p);
		persons.remove(p);
		
		assert persons.size()==size-1 :"Size not changed";
		assert !persons.contains(p) :"HashSet not updated";
		assert bank.get(p)==null :"No null entry in hashMap";
		assert wellFormed() :"Not well formed";
	}
	
	public void updatePerson(Person p) {
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		assert persons.contains(p) :"Person not present";
		int size=persons.size();
		
		persons.remove(p);
		persons.add(p);
		p.addPrintable(printable);
		
		assert persons.size()==size :"Size changed";
		assert persons.contains(p) :"Person not present";
		assert wellFormed() :"Not well formed";
	}

	public void addAccount(Account a, Person p) {
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		assert persons.contains(p) :"Person not present";
		assert a!=null :"Null argument";
		assert !bank.get(p).contains(a) :"Account already present";
		int size=bank.get(p).size();
		
		bank.get(p).add(a);
		for(Person p1:persons){
			if(p.getId()==p1.getId())
				a.addObserver(p1);
			p1.addPrintable(printable);
		}
		
		assert bank.get(p).size()==size+1 :"Size not changed";
		assert bank.get(p).contains(a) :"Account not present";
		assert wellFormed() :"Not well formed";
	}

	public void removeAccount(Account a, Person p) {
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		assert persons.contains(p) :"Person not present";
		assert a!=null :"Null argument";
		assert bank.get(p).contains(a) :"Account not present";
		int size=bank.get(p).size();
		
		bank.get(p).remove(a);
		
		assert bank.get(p).size()==size-1 :"Size not changed";
		assert !bank.get(p).contains(a) :"Account not removed";
		assert wellFormed() :"Not well formed";
	}
	
	public void updateAccount(Account a, Person p){
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		assert persons.contains(p) :"Person not present";
		assert a!=null :"Null argument";
		assert bank.get(p).contains(a) :"Account not present";
		int size=bank.get(p).size();
		
		bank.get(p).remove(a);
		bank.get(p).add(a);
		for(Person p1:persons){
			if(p.getId()==p1.getId())
				a.addObserver(p1);
			p1.addPrintable(printable);
		}
		
		assert bank.get(p).size()==size :"Size changed";
		assert bank.get(p).contains(a) :"Account not present";
		assert wellFormed() :"Not well formed";
	}
	
	public void addMoney(int accountId, Person p, int amount) throws SavingAccountModificationException{
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		assert persons.contains(p) :"Person not present";
		//other preconditions are asserted in method algorithm
		int money1=0,money2=0;
		
		for(Account a:bank.get(p)){
			if(a.getId()==accountId){
				money1=a.getAmount();
				a.add(amount);
				money2=a.getAmount();
				break;
			}
		}
		
		assert money1+amount==money2 :"Amount change inconsistency";
		assert wellFormed() :"Not well formed";
	}
	
	public boolean withdraw(int accountId, Person p, int amount) throws SavingAccountModificationException{
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		assert persons.contains(p) :"Person not present";
		//other preconditions are asserted in method algorithm
		
		for(Account a:bank.get(p)){
			if(a.getId()==accountId){
				if(a instanceof SavingAccount){
					((SavingAccount)a).withdraw();
					return true;
				}
				else{
					return ((SpendingAccount)a).withdraw(amount);
				}
			}
		}
		
		//postconditions are asserted in method algorithm
		assert wellFormed() :"Not well formed";
		return false;
	}
	
	public HashSet<Person> getPersons(){
		assert wellFormed() :"Not well formed";
		
		return persons;
	}
	
	public ArrayList<Account> getAccountsOfPerson(Person p){
		assert wellFormed() :"Not well formed";
		assert p!=null :"Null argument";
		assert persons.contains(p) :"Person not present";
		
		return bank.get(p);
	}
	
	public void setTextArea(JTextArea textArea){
		printable.setTextArea(textArea);
	}
	
	/**
	 * @invariant
	 * @return true if well formed, false otherwise
	 */
	public boolean wellFormed(){
		for(Person p:persons){
			if(bank.get(p)==null){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		String str="ala";
		System.out.println(str.contains("al"));
	}

}
