package com.client.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.client.view.Chat;
import com.client.view.MainList;
import com.common.Message;
import com.common.MessageType;

public class ClientConnectServerThread extends Thread{
	
	//each Thread has a socket
	private Socket s;
	
	public ClientConnectServerThread(Socket s){
		this.s=s;
		
	}

	public void run(){
		//reading msg from server end
		while(true){
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message msg=(Message)ois.readObject();
				
				System.out.println("ccst getting msg from "+msg.getSenderId()+" to "
						+msg.getReceiverId()+" contents: "+msg.getContent());
				
				if(msg.getMsgType().equals(MessageType.getChatContent())){
					//show chat info on chatFrame
					Chat chat=ManageChatFrame.getChatFrame(msg.getReceiverId()+" "+msg.getSenderId());
					chat.ShowMessage(msg);
				}else if(msg.getMsgType().equals(MessageType.getResponseOnlineRequire())){
//					String onlineContent=msg.getContent();
//					String online[]=onlineContent.split(" ");
					String receiver=msg.getReceiverId();
					//get the user's list
					MainList mainList=ManageList.getList(receiver);
					if(mainList!=null){
						//update the list
						System.out.println("starting updating the list");
						mainList.updateMainList(msg);
					}
					
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
