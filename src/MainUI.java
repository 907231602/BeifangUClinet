import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainUI extends JFrame implements MsgListener
{
	private JTextField tmsg=new JTextField(10);
	private JTextField twho=new JTextField(10);
	private MessageThread client;
	private JTextArea tcontent=new JTextArea(10,10);
	private JButton bsend=new JButton("send");
	private JPanel panel=new JPanel();
	public MainUI(MessageThread client1,String who) {
		client=client1;
		client.setMsgListener(this);
		panel.add(tmsg);
		panel.add(tcontent);
		panel.add(twho);
		panel.add(bsend);
		getContentPane().add(panel);
		setSize(600, 600);
		setVisible(true);
		
		bsend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				client.sendMessage(MsgConstants.MESSAGETYPE, 
						tmsg.getText()+MsgConstants.SPLIT+twho.getText());
				tmsg.setText("");
			}
		});
		
	}
	@Override
	public void message(String msg) {
		// TODO Auto-generated method stub
		tcontent.append(msg);
	}
}
