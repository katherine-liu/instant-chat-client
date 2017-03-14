package com.client.service;

import java.util.HashMap;

import com.client.view.Chat;

public class ManageChatFrame {
	
	private static HashMap hm=new HashMap<String,Chat>();
	//add
	public static void addChatFrame(String loginIdnfriendId, Chat chat){
		hm.put(loginIdnfriendId, chat);
//		System.out.println("add chat frame successful");
	}
	//get 
	public static Chat getChatFrame(String loginIdnFriendId){
		return (Chat)hm.get(loginIdnFriendId);
		
	}

}
