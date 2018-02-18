package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.MainClass;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Container;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnSpending;
	private JRadioButton rdbtnSaving;
	private JButton btnGetAccounts;
	private JButton btnInsertNew;
	private JButton btnUpdate;
	private JButton btnDeleteSelected;
	private JButton btnViewReports;
	private JButton btnCreateNew;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnAddMoney;
	private JButton btnWithdraw;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	/**
	 * Create the frame.
	 */
	public MainPage(JTextArea textArea) {
		this.textArea=textArea;
		setTitle("Bank");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 952, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPersons = new JLabel("Persons");
		lblPersons.setBounds(12, 13, 56, 16);
		contentPane.add(lblPersons);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(493, 13, 1, 385);
		contentPane.add(separator_2);
		
		JLabel lblAccounts = new JLabel("Accounts");
		lblAccounts.setBounds(519, 13, 56, 16);
		contentPane.add(lblAccounts);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(283, 72, 56, 16);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(331, 69, 150, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(283, 101, 56, 16);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(331, 101, 150, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(283, 43, 56, 16);
		contentPane.add(lblId);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(331, 40, 56, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnInsertNew = new JButton("Insert new");
		btnInsertNew.setBounds(283, 208, 150, 25);
		contentPane.add(btnInsertNew);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(283, 246, 150, 25);
		contentPane.add(btnUpdate);
		
		btnDeleteSelected = new JButton("Delete selected");
		btnDeleteSelected.setBounds(283, 284, 150, 25);
		contentPane.add(btnDeleteSelected);
		
		btnViewReports = new JButton("View reports");
		btnViewReports.setBounds(283, 322, 150, 25);
		contentPane.add(btnViewReports);
		
		JLabel lblId_1 = new JLabel("Id");
		lblId_1.setBounds(771, 43, 56, 16);
		contentPane.add(lblId_1);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(839, 40, 56, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(771, 72, 56, 16);
		contentPane.add(lblAmount);
		
		textField_4 = new JTextField();
		textField_4.setBounds(839, 69, 87, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblInterest = new JLabel("Interest");
		lblInterest.setBounds(771, 101, 56, 16);
		contentPane.add(lblInterest);
		
		textField_5 = new JTextField();
		textField_5.setBounds(839, 98, 87, 22);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(771, 130, 56, 16);
		contentPane.add(lblType);
		
		rdbtnSpending = new JRadioButton("Spending");
		rdbtnSpending.setSelected(true);
		buttonGroup.add(rdbtnSpending);
		rdbtnSpending.setBounds(811, 126, 87, 25);
		contentPane.add(rdbtnSpending);
		
		rdbtnSaving = new JRadioButton("Saving");
		buttonGroup.add(rdbtnSaving);
		rdbtnSaving.setBounds(811, 157, 84, 25);
		contentPane.add(rdbtnSaving);
		
		btnCreateNew = new JButton("Create new");
		btnCreateNew.setBounds(771, 208, 124, 25);
		contentPane.add(btnCreateNew);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(771, 246, 124, 25);
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(771, 284, 124, 25);
		contentPane.add(btnDelete);
		
		btnAddMoney = new JButton("Add money");
		btnAddMoney.setBounds(771, 322, 124, 25);
		contentPane.add(btnAddMoney);
		
		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(771, 360, 124, 25);
		contentPane.add(btnWithdraw);
		
		JButton btnViewMessages = new JButton("View messages");
		btnViewMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessagesWindow mW=new MessagesWindow(MainPage.this.textArea);
				mW.setVisible(true);
			}
		});
		btnViewMessages.setBounds(283, 360, 150, 25);
		contentPane.add(btnViewMessages);
		
		btnGetAccounts = new JButton("Get accounts");
		btnGetAccounts.setBounds(283, 170, 150, 25);
		contentPane.add(btnGetAccounts);
	}
	
	public void showMessage(String msg){
		JOptionPane.showMessageDialog(contentPane, msg);
	}
	
	/**
	 * @return the textField
	 */
	public final JTextField getTextField() {
		return textField;
	}

	/**
	 * @return the textField_1
	 */
	public final JTextField getTextField_1() {
		return textField_1;
	}

	/**
	 * @return the textField_2
	 */
	public final JTextField getTextField_2() {
		return textField_2;
	}

	/**
	 * @return the textField_3
	 */
	public final JTextField getTextField_3() {
		return textField_3;
	}

	/**
	 * @return the textField_4
	 */
	public final JTextField getTextField_4() {
		return textField_4;
	}

	/**
	 * @return the textField_5
	 */
	public final JTextField getTextField_5() {
		return textField_5;
	}

	/**
	 * @return the rdbtnSpending
	 */
	public final JRadioButton getRdbtnSpending() {
		return rdbtnSpending;
	}

	/**
	 * @return the rdbtnSaving
	 */
	public final JRadioButton getRdbtnSaving() {
		return rdbtnSaving;
	}
	
	public void addGetAccountsActionListener(ActionListener aL){
		btnGetAccounts.addActionListener(aL);
	}
	
	public void addInsertPersonActionListener(ActionListener aL){
		btnInsertNew.addActionListener(aL);
	}
	
	public void addUpdatePersonActionListener(ActionListener aL){
		btnUpdate.addActionListener(aL);
	}
	
	public void addDeletePersonActionListener(ActionListener aL){
		btnDeleteSelected.addActionListener(aL);
	}
	
	public void addGetReportsActionListener(ActionListener aL){
		btnViewReports.addActionListener(aL);
	}
	
	public void addInsertAccountActionListener(ActionListener aL){
		btnCreateNew.addActionListener(aL);
	}
	
	public void addEditAccountActionListener(ActionListener aL){
		btnEdit.addActionListener(aL);
	}
	
	public void addDeleteAccountActionListener(ActionListener aL){
		btnDelete.addActionListener(aL);
	}
	
	public void addAddMoneyActionListener(ActionListener aL){
		btnAddMoney.addActionListener(aL);
	}
	
	public void addWithdrawActionListener(ActionListener aL){
		btnWithdraw.addActionListener(aL);
	}

	public void addPersonsTable(JTable newTable){
		if(table!=null){
			Container parent=scrollPane.getParent();
			parent.remove(scrollPane);
			parent.repaint();
		}
		table = newTable;
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 239, 335);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
			    if (row >= 0) {
			    	textField_2.setText(table.getValueAt(row, 0).toString());
			    	textField.setText((String)table.getValueAt(row, 1));
			    	textField_1.setText((String)table.getValueAt(row, 2));
			    }
			    if(table_1!=null){
					Container parent=scrollPane_1.getParent();
					parent.remove(scrollPane_1);
					parent.repaint();
				}
			}
		});
	}
	
	public void addAccountsTable(JTable newTable){
		if(table_1!=null){
			Container parent=scrollPane_1.getParent();
			if(parent!=null){
				parent.remove(scrollPane_1);
				parent.repaint();
			}
		}
		table_1 = newTable;
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(519, 42, 239, 335);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(table_1);
		table_1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = table_1.rowAtPoint(evt.getPoint());
			    if (row >= 0) {
			    	textField_3.setText(table_1.getValueAt(row, 0).toString());
			    	textField_4.setText(table_1.getValueAt(row, 1).toString());
			    	textField_5.setText(table_1.getValueAt(row, 2).toString());
			    	if(table_1.getValueAt(row, 0).toString().equals("Saving Account")){
			    		rdbtnSaving.setSelected(true);
			    		rdbtnSpending.setSelected(false);
			    	}
			    	else{
			    		rdbtnSaving.setSelected(false);
			    		rdbtnSpending.setSelected(true);
			    	}
			    }
			}
		});
	}
	
	@Override
	public void dispose() {
	    MainClass.endProgram();
	    super.dispose();
	}
}
