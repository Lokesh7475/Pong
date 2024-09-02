package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TextHandler implements ActionListener{

	JTextField txtInput = new JTextField("");
	@Override
	public void actionPerformed(ActionEvent e) {
		String input = txtInput.getText();
        System.out.println(input);
	}
	
}
