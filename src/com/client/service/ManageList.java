package com.client.service;

import java.util.HashMap;

import com.client.view.MainList;

public class ManageList {

	private static HashMap hm=new HashMap<String, MainList>();
	
	public static void addList(String ownerId, MainList mainList){
		hm.put(ownerId, mainList);
	}
	
	public static MainList getList(String ownerId){
		return (MainList)hm.get(ownerId);
	}
	
}
