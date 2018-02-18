package modelLogic;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Test;

import model.Account;
import model.Person;
import model.SavingAccount;
import model.SavingAccountModificationException;
import model.SpendingAccount;

public class BankTest {
	Bank b=new Bank();

	@Test
	public void testAddPerson() {
		Person p=new Person(0);
		b.addPerson(p);
		assertEquals(true, b.getPersons().contains(p));
	}

	@Test
	public void testRemovePerson() {
		Person p=new Person(0);
		b.addPerson(p);
		b.removePerson(p);
		assertEquals(false, b.getPersons().contains(p));
	}

	@Test
	public void testUpdatePerson() {
		Person p=new Person(1,"ala","bala");
		b.addPerson(p);
		p=new Person(1,"ala","portocala");
		b.updatePerson(p);
		assertEquals(true, b.getPersons().contains(p));
	}

	@Test
	public void testAddAccount() {
		Person p=new Person(0);
		b.addPerson(p);
		Account a=new SpendingAccount(1,200);
		b.addAccount(a, p);
		assertEquals(true, b.getAccountsOfPerson(p).contains(a));
	}

	@Test
	public void testRemoveAccount() {
		Person p=new Person(0);
		b.addPerson(p);
		Account a=new SpendingAccount(1,200);
		b.addAccount(a, p);
		b.removeAccount(a, p);
		assertEquals(false, b.getAccountsOfPerson(p).contains(a));
	}

	@Test
	public void testUpdateAccount() {
		Person p=new Person(0);
		b.addPerson(p);
		Account a=new SpendingAccount(1,200);
		b.addAccount(a, p);
		a=new SavingAccount(1,200,0);
		b.updateAccount(a, p);
		assertEquals(true, b.getAccountsOfPerson(p).contains(a));
	}

	@Test
	public void testAddMoney() {
		b.setTextArea(new JTextArea());
		Person p=new Person(0);
		b.addPerson(p);
		Account a=new SpendingAccount(1,200);
		b.addAccount(a, p);
		try {
			b.addMoney(1, p, 100);
		} catch (SavingAccountModificationException e) {
			e.printStackTrace();
		}
		a=b.getAccountsOfPerson(p).get(0);
		assertEquals(300,a.getAmount());
	}

	@Test
	public void testWithdraw() {
		b.setTextArea(new JTextArea());
		Person p=new Person(0);
		b.addPerson(p);
		Account a=new SpendingAccount(1,200);
		b.addAccount(a, p);
		try {
			b.withdraw(1, p, 100);
		} catch (SavingAccountModificationException e) {
			e.printStackTrace();
		}
		a=b.getAccountsOfPerson(p).get(0);
		assertEquals(100,a.getAmount());
	}

}
