package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextArea;

import model.Account;
import model.Person;
import modelLogic.Bank;
import modelLogic.Model;
import view.MainPage;

public class MainClass {
	private static Bank b;
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		//b=new Bank();
		b=null;
		try {
	         FileInputStream fileIn = new FileInputStream("bank.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         b = (Bank) in.readObject();
	         Person.setIdAvailable((Integer)in.readObject());
	         Account.setIdAvailable((Integer)in.readObject());
	         in.close();
	         fileIn.close();
	      }catch(Exception i) {
	         i.printStackTrace();
	      }
		
		
		b.addAllObservable();
		JTextArea textArea=new JTextArea();
		b.setTextArea(textArea);
		Model model=new Model(b);
		MainPage view=new MainPage(textArea);
		Controller controller=new Controller(view,model);
		view.setVisible(true);
	}
	
	public static void endProgram(){
		try {	
			FileOutputStream fileOut =new FileOutputStream("bank.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(b);
	        out.writeObject(Person.getIdAvailable());
	        out.writeObject(Account.getIdAvailable());
	        out.close();
	        fileOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
