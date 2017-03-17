package com.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.UUID;

import javax.swing.*;

import com.client.service.ManageChatFrame;
import com.common.Message;

public class MainList implements ActionListener, MouseListener, MouseMotionListener {
	
//	public static void main(String[] args){
//		new FriendsList();
//	}
	
	private JFrame jframe=new JFrame();
	private JPanel jpanel1_1=null, jpanel1_friends=null,jpanel1_down=null,
			jpanel2_1=null,jpanel2_stranger=null,jpanel2_down=null,
			jpanel3_1=null,jpanel3_block=null,jpanel3_down=null;
	private JButton jbuttonFriends1=null,jbuttonStrangers1=null,jbuttonBlock1=null,
			jbuttonFriends2=null,jbuttonStrangers2=null,jbuttonBlock2=null,
			jbuttonFriends3=null,jbuttonStrangers3=null,jbuttonBlock3=null;
	private JScrollPane jsp1=null, jsp2=null, jsp3=null;
	private CardLayout cardlayout=null;
	private String ownerId=null;
	private JLabel[] jlabelFriends=null;
    private boolean mouseClickProfile = true;


	public MainList(String ownId) {
		this.ownerId=ownId;
		createCard(ownId);
		//System.out.println(ownId);
	}
	
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	//Friends list
	public void createCard(String ownId){
		
		jbuttonFriends1=new JButton("Friends");
		jbuttonFriends1.addActionListener(this);
		jbuttonStrangers1=new JButton("Strangers");
		jbuttonStrangers1.addActionListener(this);
		jbuttonBlock1=new JButton("Block");
		jbuttonBlock1.addActionListener(this);
		
		//Friends list
		jpanel1_friends=new JPanel(new GridLayout(50,1,4,4));
		jlabelFriends=new JLabel[50];
		for(int i=0;i<jlabelFriends.length;i++){
			jlabelFriends[i]=new JLabel(i+1+"", new ImageIcon("src/pic/pig.png"), JLabel.LEFT);

//			jlabelFriends[i].setOpaque(true);
			jlabelFriends[i].setEnabled(false);
			//setting own label to enable
			if(jlabelFriends[i].getText().equals(ownId)){
				jlabelFriends[i].setEnabled(true);
			}
			jlabelFriends[i].addMouseListener(this);
			jpanel1_friends.add(jlabelFriends[i]);
			
		}
		jsp1=new JScrollPane(jpanel1_friends);
				
		//JPanel Down
		jpanel1_down=new JPanel(new GridLayout(2,1));
		jpanel1_down.add(jbuttonStrangers1);
		jpanel1_down.add(jbuttonBlock1);
		
		//initialize card1
		jpanel1_1=new JPanel(new BorderLayout());
		jpanel1_1.add(jbuttonFriends1, BorderLayout.NORTH);
		jpanel1_1.add(jsp1, BorderLayout.CENTER);
		jpanel1_1.add(jpanel1_down, BorderLayout.SOUTH);
		
		//card 2
		jbuttonFriends2=new JButton("Friends");
		jbuttonFriends2.addActionListener(this);
		jbuttonStrangers2=new JButton("Strangers");
		jbuttonStrangers2.addActionListener(this);
		jbuttonBlock2=new JButton("Block");
		jbuttonBlock2.addActionListener(this);
		//strangers list
		jpanel2_stranger=new JPanel(new GridLayout(20,1,4,4));
		JLabel[] jlabelStrangers=new JLabel[20];
		for(int i=0;i<jlabelStrangers.length;i++){
		jlabelStrangers[i]=new JLabel(i+1+"", new ImageIcon("src/pic/pig.png"), JLabel.LEFT);
			jpanel2_stranger.add(jlabelStrangers[i]);
		}
		jsp2=new JScrollPane(jpanel2_stranger);
		
		//JPanel Down
		jpanel2_down=new JPanel(new GridLayout(2,1));
		jpanel2_down.add(jbuttonFriends2);
		jpanel2_down.add(jbuttonStrangers2);
		
		//initialize card2
		jpanel2_1=new JPanel(new BorderLayout());
		jpanel2_1.add(jpanel2_down, BorderLayout.NORTH);
		jpanel2_1.add(jsp2, "Center");
		jpanel2_1.add(jbuttonBlock2, BorderLayout.SOUTH);
		
		//card 3
		jbuttonFriends3=new JButton("Friends");
		jbuttonFriends3.addActionListener(this);
		jbuttonStrangers3=new JButton("Strangers");
		jbuttonStrangers3.addActionListener(this);
		jbuttonBlock3=new JButton("Block");
		jbuttonBlock3.addActionListener(this);
		//Block list	
		jpanel3_block=new JPanel(new GridLayout(20,1,4,4));
		JLabel[] jlabelBlocks=new JLabel[20];
		for(int i=0;i<jlabelBlocks.length;i++){
			jlabelBlocks[i]=new JLabel(i+1+"", new ImageIcon("src/pic/pig.png"), JLabel.LEFT);
			jpanel3_block.add(jlabelBlocks[i]);
		}			
		jsp3=new JScrollPane(jpanel3_block);
		
		//JPanel Down
		jpanel3_down=new JPanel(new GridLayout(3,1));
		jpanel3_down.add(jbuttonFriends3);
		jpanel3_down.add(jbuttonStrangers3);
		jpanel3_down.add(jbuttonBlock3);
				
		//initialize card3
		jpanel3_1=new JPanel(new BorderLayout());
		jpanel3_1.add(jpanel3_down, BorderLayout.NORTH);
		jpanel3_1.add(jsp3, BorderLayout.CENTER);
		
		cardlayout=new CardLayout();
		jframe.setLayout(cardlayout);
		jframe.add(jpanel1_1, "1");
		jframe.add(jpanel2_1, "2");
		jframe.add(jpanel3_1, "3");
//		cardlayout.show(jframe.getContentPane(), "1");
		jframe.setTitle(ownId);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setSize(300, 400);
		jframe.setVisible(true);
		
	}

	//update displaying list
	public void updateMainList(Message msg){
		String onlineList[]=msg.getContent().split(" ");
		System.out.println("updating list gonna be: "+msg.getContent());
		for(int i=0;i<onlineList.length;i++){
			jlabelFriends[Integer.parseInt(onlineList[i])-1].setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//CardLayout switch
		if((JButton)arg0.getSource()==jbuttonFriends2||(JButton)arg0.getSource()==jbuttonFriends3){
			cardlayout.show(jframe.getContentPane(), "1");
			System.out.println("listening friends");
			}
		else if((JButton)arg0.getSource()==jbuttonStrangers1||(JButton)arg0.getSource()==jbuttonStrangers3){
			cardlayout.show(jframe.getContentPane(), "2");
			System.out.println("listening strangers");
		}else if((JButton)arg0.getSource()==jbuttonBlock1||(JButton)arg0.getSource()==jbuttonBlock2){
			cardlayout.show(jframe.getContentPane(), "3");
			System.out.println("listening block");
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
        String friendId=((JLabel)mouseEvent.getSource()).getText();
        // Distinguish between a single click and a double click
        if (mouseEvent.getClickCount() == 2) {
			Chat chat=new Chat(this.ownerId, friendId);
			//add chat frame
			ManageChatFrame.addChatFrame(this.ownerId+" "+friendId, chat);
            mouseClickProfile = true;
        } else {
//            Integer timerInterval = (Integer) Toolkit.getDefaultToolkit().getDesktopProperty(
//                    "awt.multiClickInterval");
			Integer timerInterval = 200;
            Timer timer = new Timer(timerInterval.intValue(), new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (mouseClickProfile) {
                        mouseClickProfile = false; // reset flag
                    } else {
                        JOptionPane.showMessageDialog(jframe,
                            generateUserProfile(friendId),
                            "User Profile",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

	@Override
	public void mouseEntered(MouseEvent arg0) {
		JLabel jlabel=(JLabel)arg0.getSource();
		jlabel.setForeground(Color.BLUE);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JLabel jlabel=(JLabel)arg0.getSource();
		jlabel.setForeground(Color.BLACK);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//Generate user's profile dynamically
	private String generateUserProfile(String friendId) {
	    return String.format("User ID: %s %n" +
                             "Gender: %s %n" +
                             "Phone Number: %s %n" +
                             "Location: %s %n" +
                             "Wechat ID: %s %n" +
                             "Motto: MAGA!!!",
                            friendId,
                            Integer.parseInt(friendId) % 2 == 0 ? "Male" : "Female",
                            Integer.parseInt(friendId) % 2 == 0 ? "2136186108" : "6785490350",
                            Integer.parseInt(friendId) % 2 == 0 ? "China" : "United States",
                            UUID.randomUUID().toString().substring(0,7));
    }
	
}
