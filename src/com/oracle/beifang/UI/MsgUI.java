package com.oracle.beifang.UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.oracle.beifang.client.Process.ClientProcessor;


public class MsgUI extends JFrame {
	
	private JTextField txtUserName = new JTextField(10);
	private JTextField txtUserPsw = new JTextField(10);
	private JButton btnLogin = new JButton("µÇÂ¼");
	private JPanel panel = new JPanel();
	
	private ClientProcessor clientProcessor = new ClientProcessor();
	public MsgUI(){
		panel.add(txtUserName);
		panel.add(txtUserPsw);
		panel.add(btnLogin);
		
		getContentPane().add(panel);
		setSize(600,600);
		setVisible(true);
		
	}

}
