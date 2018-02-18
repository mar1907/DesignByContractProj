package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Person;
import model.SavingAccount;
import modelLogic.Model;
import view.MainPage;

public class Controller {
	private JTable personsTable;
	private JTable accountsTable;
	private MainPage view;
	private Model model;
	
	public Controller(MainPage view, Model model){
		this.view=view;
		this.model=model;
		addPersonsTable();
		view.addGetAccountsActionListener(new GetAccountsActionListener());
		view.addInsertPersonActionListener(new InsertPersonActionListener());
		view.addUpdatePersonActionListener(new UpdatePersonActionListener());
		view.addDeletePersonActionListener(new DeletePersonActionListener());
		view.addGetReportsActionListener(new GetReportsActionListener());
		view.addInsertAccountActionListener(new CreateAccountActionListener());
		view.addEditAccountActionListener(new EditAccountActionListener());
		view.addDeleteAccountActionListener(new DeleteAccountActionListener());
		view.addAddMoneyActionListener(new AddMoneyActionListener());
		view.addWithdrawActionListener(new WithdrawActionListener());
	}
	
	public class GetAccountsActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			addAccountsTable((Integer) personsTable.getValueAt(personsTable.getSelectedRow(),0));
		}
	}
	
	public class InsertPersonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String msg=model.addPerson(view.getTextField().getText(), view.getTextField_1().getText());
			if(!msg.equals("")){
				view.showMessage(msg);
			}
			addAccountsTable(-1);
			addPersonsTable();
		}
	}
	
	public class UpdatePersonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String msg=model.updatePerson(Integer.parseInt(view.getTextField_2().getText()), 
					view.getTextField().getText(), view.getTextField_1().getText());
			if(!msg.equals("")){
				view.showMessage(msg);
			}
			addAccountsTable(-1);
			addPersonsTable();
		}
	}
	
	public class DeletePersonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			model.deletePerson(Integer.parseInt(view.getTextField_2().getText()));	
			addAccountsTable(-1);
			addPersonsTable();
		}		
	}
	
	public class GetReportsActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			view.showMessage(model.getReports());		
		}
	}
	
	public class CreateAccountActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String msg;
			if (view.getRdbtnSpending().isSelected()) {
				msg = model.addAccount(Integer.parseInt(view.getTextField_2().getText()),
						Integer.parseInt(view.getTextField_4().getText()),0,true);
			}
			else{
				msg = model.addAccount(Integer.parseInt(view.getTextField_2().getText()),
						Integer.parseInt(view.getTextField_4().getText()),Double.parseDouble(view.getTextField_5().getText()),false);
			}
			if(!msg.equals("")){
				view.showMessage(msg);
			}
			addAccountsTable((Integer) personsTable.getValueAt(personsTable.getSelectedRow(),0));
		}		
	}
	
	public class EditAccountActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String msg;
			if(accountsTable.getValueAt(accountsTable.getSelectedRow(), 3).toString().equals("Spending Account")){
				msg=model.updateAccount(Integer.parseInt(view.getTextField_2().getText()),
						Integer.parseInt(view.getTextField_3().getText()), 
						Integer.parseInt(view.getTextField_4().getText()), 0, true);
			}
			else{
				msg=model.updateAccount(Integer.parseInt(view.getTextField_2().getText()),
						Integer.parseInt(view.getTextField_3().getText()), 
						Integer.parseInt(view.getTextField_4().getText()), 
						Double.parseDouble(view.getTextField_5().getText()), true);
			}
			if(!msg.equals("")){
				view.showMessage(msg);
			}
			addAccountsTable((Integer) personsTable.getValueAt(personsTable.getSelectedRow(),0));
		}
	}
	
	public class DeleteAccountActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			model.deleteAccount(Integer.parseInt(view.getTextField_2().getText()), 
					Integer.parseInt(view.getTextField_3().getText()));
			addAccountsTable((Integer) personsTable.getValueAt(personsTable.getSelectedRow(),0));
		}		
	}
	
	public class AddMoneyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String msg=model.addMoneyToAccount(Integer.parseInt(view.getTextField_2().getText()), 
					Integer.parseInt(view.getTextField_3().getText()), 
					Integer.parseInt(view.getTextField_4().getText()));
			if(!msg.equals("")){
				view.showMessage(msg);
			}
			addAccountsTable((Integer) personsTable.getValueAt(personsTable.getSelectedRow(),0));
		}
	}
	
	public class WithdrawActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String msg=model.withdrawFromAccount(Integer.parseInt(view.getTextField_2().getText()), 
					Integer.parseInt(view.getTextField_3().getText()), 
					Integer.parseInt(view.getTextField_4().getText()));
			if(!msg.equals("")){
				view.showMessage(msg);
			}
			addAccountsTable((Integer) personsTable.getValueAt(personsTable.getSelectedRow(),0));
		}
	}
	
	private void addPersonsTable(){
		Vector<String> columns=new Vector<String>();
		columns.add("Id");
		columns.add("Name");
		columns.add("Email");
		Vector<Vector<Object>> data=new Vector<Vector<Object>>();
		for(Person p:model.getPersons()){
			Vector<Object> personData=new Vector<Object>();
			personData.add(p.getId());
			personData.add(p.getName());
			personData.add(p.getEmail());
			data.add(personData);
		}
		@SuppressWarnings("rawtypes")
		final Class[] columnClass = new Class[] {
	            Integer.class, String.class, String.class
	    };
		@SuppressWarnings("serial")
		DefaultTableModel tableModel=new DefaultTableModel(data, columns){
	         @Override
	         public Class<?> getColumnClass(int columnIndex)
	         {
	             return columnClass[columnIndex];
	         }
	         @Override
	         public boolean isCellEditable(int row, int column) {
	             return false;
	         }
		};
		personsTable=new JTable(tableModel);
		view.addPersonsTable(personsTable);
	}
	
	private void addAccountsTable(int id){
		if (id!=-1) {
			Vector<String> columns = new Vector<String>();
			columns.add("Id");
			columns.add("Amount");
			columns.add("Interest");
			columns.add("Type");
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			for (Account a : model.getAccountsOfPerson(id)) {
				Vector<Object> accountData = new Vector<Object>();
				accountData.add(a.getId());
				accountData.add(a.getAmount());
				if (a instanceof SavingAccount) {
					accountData.add(((SavingAccount) a).getInterest());
					accountData.add("Saving Account");
				} else {
					accountData.add(0);
					accountData.add("Spending Account");
				}
				data.add(accountData);
			}
			@SuppressWarnings("rawtypes")
			final Class[] columnClass = new Class[] { Integer.class, Integer.class, Double.class, String.class };
			@SuppressWarnings("serial")
			DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnClass[columnIndex];
				}
			};
			accountsTable = new JTable(tableModel);
		}
		else{
			accountsTable=new JTable();
		}
		view.addAccountsTable(accountsTable);
	}
}
