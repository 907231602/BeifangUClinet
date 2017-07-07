package com.oracle.beifang.client.Process;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientProcessor extends Thread {
	
	private Socket socket;
	
	private DataOutputStream dout;
	private DataInputStream din;
	public ClientProcessor(){
		try {
			
			socket = new Socket("localhost", 9999);
			dout = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
