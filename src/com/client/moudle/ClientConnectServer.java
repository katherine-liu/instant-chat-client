/*
 * client back-end
 */
package com.client.moudle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.client.service.ClientConnectServerThread;
import com.client.service.ManageClientToServerThread;
import com.common.Message;
import com.common.User;

public class ClientConnectServer {
	
	public ClientConnectServer(){
		
	}
	private Socket s;
	public boolean SendLoginToServer(Object o){
		boolean b=false;
		try {
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message msg=(Message)ois.readObject();
			//check user login
			if(msg.getMsgType().equals("1")){
				b=true;
				//create thread after login successful
				ClientConnectServerThread ccst=new ClientConnectServerThread(s);
				//start thread
				ccst.start();
				ManageClientToServerThread.addClientConnectServerThread(
						((User)o).getId(), ccst);
				System.out.println(ManageClientToServerThread.getHm());
				
			}else{
				s.close();
			}
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return b;
	}
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public void SendInfoToServer(Object o){
		
	}

}
