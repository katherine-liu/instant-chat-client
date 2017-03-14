/*
 * manage sockets from client to server
 */
package com.client.service;

import java.util.HashMap;

public class ManageClientToServerThread {

	private static HashMap hm=new HashMap<String, ClientConnectServerThread>();
	//add clientconnectserverThread into hm
	public static void addClientConnectServerThread(String userId, ClientConnectServerThread ccst){
		hm.put(userId, ccst);
		
	}
	//get ClientConnectServerThread via userId
	public static ClientConnectServerThread getClientConnectServerThread(String userId){
		
		return (ClientConnectServerThread)hm.get(userId);
		
	}
	public static HashMap getHm() {
		return hm;
	}
	public static void setHm(HashMap hm) {
		ManageClientToServerThread.hm = hm;
	}
	
	
	
}

