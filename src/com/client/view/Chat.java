/*
 * chat frame
 * because the client should keeping reading from the server,
 * making chat a thread
 */
package com.client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.client.service.ManageClientToServerThread;
import com.common.Message;
import com.common.MessageType;

public class Chat implements ActionListener{
	
//	private Socket s=null;
	
	private JFrame jframe=new JFrame();
	private JTextArea jta=null;
	private JTextField jtf=null;
	private JButton jb=null;
	private JPanel jp=null;
	private String ownerId=null, friendId=null;
	
	public Chat(String ownerId, String friendId){
		this.ownerId=ownerId;
		this.friendId=friendId;
		jta=new JTextArea();
		
		jtf=new JTextField("",15);
		jb=new JButton("Send");
		jb.addActionListener(this);
//		jb.addKeyListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		jframe.add(jta);
		jframe.add(jp, BorderLayout.SOUTH);
		jframe.setTitle(ownerId+" Chating with "+friendId);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setSize(300, 200);
		jframe.setVisible(true);
	}

	public void ShowMessage(Message msg){
		String info=msg.getSenderId()+" said to "+msg.getReceiverId()+" content: "+msg.getContent();
		this.jta.append(info);
		System.out.println("show msg successful");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if((JButton)arg0.getSource()==jb){
			
			//click send button
			Message msg=new Message();
			msg.setSenderId(this.ownerId);
			msg.setReceiverId(this.friendId);
			msg.setContent(jtf.getText());
			msg.setSentTime(new Date().toString());
			/////////////////
			msg.setMsgType(MessageType.getChatContent());
			
			///send to server
			try {
				ObjectOutputStream oos=new ObjectOutputStream(
						ManageClientToServerThread.getClientConnectServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
