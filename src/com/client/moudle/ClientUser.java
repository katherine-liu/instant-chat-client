 package com.client.moudle;

import com.common.User;

public class ClientUser {

	public boolean checkUser(User u){
		return new ClientConnectServer().SendLoginToServer(u);
	}
}
