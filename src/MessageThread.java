import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessageThread extends Thread {
	
	private Socket messageSocket;
	private DataOutputStream dout;
	private DataInputStream din;
	private boolean isStop=false;
	private MsgListener msgListener;
	public MsgListener getMsgListener() {
		return msgListener;
	}
	public void setMsgListener(MsgListener msgListener) {
		this.msgListener = msgListener;
	}
	public MessageThread() {
		try {
			
			messageSocket = new Socket("localhost", 8899);
			
			dout = new DataOutputStream(messageSocket.getOutputStream());
			din = new DataInputStream(messageSocket.getInputStream());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMessage(String type,String msg){
		try {
			if(dout == null){
				throw new RuntimeException("无法连接到服务器");
			}
			dout.writeUTF(buildString(type, msg));;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String buildString(String type,String msg){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(type);
		stringBuffer.append(MsgConstants.SPLIT);
		stringBuffer.append(msg);
		return stringBuffer.toString();
	}
	@Override
	public void run(){
		System.out.println("Message run");
		while(!isStop){
			try {
				String info=din.readUTF();
				msgListener.message(info);
				System.out.println("客户端："+info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
