package model;

@SuppressWarnings("serial")
public class SavingAccountModificationException extends Exception {
	public SavingAccountModificationException() {}

    //Constructor that accepts a message
    public SavingAccountModificationException(String message)
    {
       super(message);
    }
}
