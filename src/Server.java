import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	 static boolean isStop=false;
	public static void startServer(){
		try {
			
			ServerSocket serverSocket = new ServerSocket(8899);
		while(!isStop){
			Socket socket = serverSocket.accept();
			ServerMessageProcesser smp = new ServerMessageProcesser(socket);
			smp.start();
			System.out.println(socket);

		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		startServer();
	}

}
