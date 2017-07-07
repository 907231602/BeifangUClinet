import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginUI extends JFrame implements MsgListener{
	
	private JTextField txtFieldName = new JTextField(10);
	private JTextField txtFieldPwd = new JTextField(10);
	private JButton jButton = new JButton("µÇÂ¼");
	private JPanel panel = new JPanel();
	//Dialog dialog=new Dialog();
	private MessageThread messageThread = new MessageThread();
	public LoginUI(){
		messageThread.start();
		System.out.println(this.getClass().getName());
		messageThread.setMsgListener(this);
		panel.add(txtFieldName);
		panel.add(txtFieldPwd);
		panel.add(jButton);
		jButton.addActionListener(l);
		getContentPane().add(panel);
		setSize(600,600);
		setVisible(true);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	private ActionListener l = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			messageThread.sendMessage(MsgConstants.LOGINTYPE, 
					txtFieldName.getText() + MsgConstants.SPLIT + txtFieldPwd.getText());
		}                                       
	};
	@Override
	public void message(String msg) {
		// TODO Auto-generated method stub
		System.out.println("UI:"+msg);
		if(msg.equals(MsgConstants.ISSUCCESS)){
			new MainUI(messageThread,txtFieldName.getText());
		}
		//txtFieldName.setText(msg);
	}
	
	
	
}
