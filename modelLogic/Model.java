package modelLogic;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Person;
import model.SavingAccount;
import model.SavingAccountModificationException;
import model.SpendingAccount;
import validators.*;

public class Model {
	private Bank bank;
	private List<Validator<Account>> accountValidators;
	private List<Validator<Person>> personValidators;
	
	public Model(Bank bank){
		this.bank=bank;
		accountValidators=new ArrayList<Validator<Account>>();
		accountValidators.add(new AmountValidator());
		accountValidators.add(new InterestValidator());
		personValidators=new ArrayList<Validator<Person>>();
		personValidators.add(new EmailValidator());
	}
	
	@SuppressWarnings("unchecked")
	public String addPerson(String name, String email){
		String msg="";
		Person p=new Person(name,email);
		for(@SuppressWarnings("rawtypes") Validator v:personValidators){
			msg+=v.isValid(p);
		}
		if(msg.equals("")){
			bank.addPerson(p);
		}
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	public String updatePerson(int id, String name, String email){
		String msg="";
		Person p=new Person(id,name,email);
		for(@SuppressWarnings("rawtypes") Validator v:personValidators){
			msg+=v.isValid(p);
		}
		if(msg.equals("")){
			bank.updatePerson(p);
		}
		return msg;
	}
	
	public void deletePerson(int id){
		Person p=new Person(id);
		bank.removePerson(p);
	}
	
	public String getReports(){
		String msg="";
		int total=0;
		ArrayList<Person> persons=new ArrayList<Person>(bank.getPersons());
		for(Person p:persons){
			int subtotal=0;
			ArrayList<Account> accounts=bank.getAccountsOfPerson(p);
			for(Account a:accounts){
				subtotal+=a.getAmount();
			}
			msg+="Client "+p.getId()+" has a total of "+subtotal+" in all their accounts;\n";
			total+=subtotal;
		}
		msg+="\nTotal in the bank: "+total;
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	public String addAccount(int personId, int amount, double interest, boolean spending){
		String msg="";
		Person p=new Person(personId);
		Account a;
		if(spending){
			a=new SpendingAccount();
		}
		else{
			a=new SavingAccount(interest);
		}
		bank.addAccount(a, p);
		try {
			a.add(amount);
		} catch (SavingAccountModificationException e) {
			//nothing
		}
		for(@SuppressWarnings("rawtypes") Validator v:accountValidators){
			msg+=v.isValid(a);
		}
		if(!msg.equals("")){
			bank.removeAccount(a, p);
		}
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	public String updateAccount(int personId, int accountId, int amount, double interest, boolean spending){
		String msg="";
		Person p=new Person(personId);
		Account a;
		if(spending){
			a=new SpendingAccount(accountId,amount);
		}
		else{
			a=new SavingAccount(accountId,amount,interest);
		}
		for(@SuppressWarnings("rawtypes") Validator v:accountValidators){
			msg+=v.isValid(a);
		}
		if(msg.equals("")){
			bank.updateAccount(a, p);
		}
		return msg;
	}
	
	public void deleteAccount(int personId, int accountId){
		Person p=new Person(personId);
		Account a=new Account(accountId);
		bank.removeAccount(a, p);
	}
	
	public String addMoneyToAccount(int personId, int accountId, int amount){
		String msg="";
		if(amount<0){
			return "Add a positive amount;\n";
		}
		Person p=new Person(personId);
		try {
			bank.addMoney(accountId, p, amount);
		} catch (SavingAccountModificationException e) {
			msg+="Can't add money to savings account;\n";
		}
		return msg;
	}
	
	public String withdrawFromAccount(int personId, int accountId, int amount){
		String msg="";
		Person p=new Person(personId);
		if(amount<0){
			return "Add a positive amount;\n";
		}
		try {
			if(!bank.withdraw(accountId, p, amount)){
				msg+="Can't withdraw that amount, not enough money;\n";
			}
		} catch (SavingAccountModificationException e) {
			msg+="Can't withdraw from empty saving account:\n";
		}
		return msg;
	}
	
	public ArrayList<Person> getPersons(){
		return new ArrayList<Person>(bank.getPersons());
	}
	
	public ArrayList<Account> getAccountsOfPerson(int id){
		Person p=new Person(id);
		return bank.getAccountsOfPerson(p);
	}
}
