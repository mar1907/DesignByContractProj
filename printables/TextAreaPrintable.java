package printables;

import java.io.Serializable;

import javax.swing.JTextArea;

public class TextAreaPrintable implements Printable, Serializable {
	private static final long serialVersionUID = 7907165983868767322L;
	private JTextArea textArea;
	
	public void setTextArea(JTextArea textArea){
		this.textArea=textArea;
	}

	public synchronized void print(String arg) {
		textArea.append(arg+"\n");
	}
}
