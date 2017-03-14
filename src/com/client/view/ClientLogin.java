
package com.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.client.moudle.ClientUser;
import com.client.service.ManageClientToServerThread;
import com.client.service.ManageList;
import com.common.Message;
import com.common.MessageType;
import com.common.User;

public class ClientLogin implements ActionListener{
	
	public static void main(String[] args){
		new ClientLogin();
	}

	private JFrame jframe=new JFrame();
	
	//north
	private JPanel jpanelNorth=null;
	
	private JLabel jlabelNorth=null;
	
	//center
	//three JPanels via
	private JPanel jpanelCenter=null;
	private JLabel jlabelAccount=null;
	private JLabel jlabelPassword=null;
	private JLabel jlabelForget=null;
	private JLabel jlabelProtect=null;
	
	
	private JTextField jtextfeildAccount=null;
	private JPasswordField jpf=null;
	private JCheckBox jcheckboxRemember=null;
	private JCheckBox jcheckboxAutologin=null;
	
	private int x=0, y=0;
	
	//south
	private JPanel jpanelSouth=null;
	
	private JButton jbuttonLogin=null, jbuttonSignup=null,jbuttonGuider=null;
	
	public ClientLogin(){
		//north
		jlabelNorth=new JLabel("Tile Pic",SwingConstants.CENTER);
		
		jframe.add(jlabelNorth,BorderLayout.NORTH);
		
		//center
		jpanelCenter=new JPanel(new GridLayout(3,3,4,2));
		
		jlabelAccount=new JLabel("Account");
		jlabelPassword=new JLabel("Password");
		jlabelForget=new JLabel("Forget");
		jlabelProtect=new JLabel("Protect");
		
		jtextfeildAccount=new JTextField();
		jpf=new JPasswordField();
		jcheckboxRemember=new JCheckBox("Remember");
		jcheckboxAutologin=new JCheckBox("AutoLogin");
		
		jpanelCenter.add(jlabelAccount);
		jpanelCenter.add(jtextfeildAccount);
		jpanelCenter.add(jlabelForget);
		jpanelCenter.add(jlabelPassword);
		jpanelCenter.add(jpf);
		jpanelCenter.add(jlabelProtect);
		
		jpanelCenter.add(jcheckboxRemember);
		jpanelCenter.add(jcheckboxAutologin);
		
		jframe.add(jpanelCenter,BorderLayout.CENTER);
		
		//south
		jpanelSouth=new JPanel();
		jbuttonLogin=new JButton("Login");
		jbuttonLogin.addActionListener(this);
		jbuttonSignup=new JButton("Sign Up");
		jbuttonGuider=new JButton("Guider");
		
		jpanelSouth.add(jbuttonLogin);
		jpanelSouth.add(jbuttonSignup);
		jpanelSouth.add(jbuttonGuider);
		
		jframe.add(jpanelSouth,BorderLayout.SOUTH);
		
		//set Login window
		jframe.setSize(350, 240);
		x=10+x;
		y=10+y;
		jframe.setLocation(x+10, y+10);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if((JButton)arg0.getSource()==jbuttonLogin){
			System.out.println("login pressed");
			ClientUser clientUser=new ClientUser();
			User u=new User();
			u.setId(jtextfeildAccount.getText().trim());
			String pwd=new String(jpf.getPassword());
			u.setPassword(pwd);
			
			if(clientUser.checkUser(u)){
				MainList mainList=new MainList(u.getId());
				System.out.println("login successful");
				ManageList.addList(u.getId(), mainList);
				
				
				//to get the online friends and show one the friendList
				try {
					ObjectOutputStream oos=new ObjectOutputStream(
							ManageClientToServerThread.getClientConnectServerThread(u.getId()).getS().getOutputStream());
					Message msg=new Message();
					msg.setMsgType(MessageType.getRequireOnline());
					//one id for one List panel
					msg.setSenderId(u.getId());
					oos.writeObject(msg);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//close login User Interface
				jframe.dispose();
			}else{
				JOptionPane.showMessageDialog(jframe, "Account/Password is incorrect");
			}
			
		}
		
	}
	
}
