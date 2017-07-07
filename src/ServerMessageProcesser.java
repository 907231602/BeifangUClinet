import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerMessageProcesser extends Thread {
	private Socket socket;
	private boolean isStop=false;
	private DataOutputStream dout;
	private DataInputStream din;
	public ServerMessageProcesser(Socket socket){
		this.socket = socket;
		try {
			dout=new DataOutputStream(socket.getOutputStream());
			din=new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("Server run");
		while(!isStop){
			try {
				String msgINfo=din.readUTF();
				System.out.println(msgINfo);
				String[] msgs= msgINfo.split(MsgConstants.SPLIT);
				for(String s:msgs){
					System.out.println(s);
				}
				String type=msgs[0];
				String uName="zyf";
				if(type.equals(MsgConstants.LOGINTYPE)){
					 uName=msgs[1];
					String uPass=msgs[2];
					if(uName!=null||uName.equals("")){
						dout.writeUTF(MsgConstants.ISSUCCESS);
					DataMaps.dataMaps.put(uName, this);
					}
				}
				if(type.equals(MsgConstants.MESSAGETYPE)){
					String msg=msgs[1];
					String name=msgs[2];
					System.out.println("testName:"+uName);
					DataMaps.dataMaps.get(name).dout.writeUTF(msg);
					DataMaps.dataMaps.get(uName).dout.writeUTF(msg);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
