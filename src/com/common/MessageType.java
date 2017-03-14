/*
 * message categories for server processing different messages
 * 
 */
package com.common;

public class MessageType {
	private static String loginSuccessful="1";
	private static String loginFail="2";
	private static String chatContent="3";
	private static String requireOnline="4";
	private static String responseOnlineRequire="5";
	public static String getLoginSuccessful() {
		return loginSuccessful;
	}
	public static void setLoginSuccessful(String loginSuccessful) {
		MessageType.loginSuccessful = loginSuccessful;
	}
	public static String getLoginFail() {
		return loginFail;
	}
	public static void setLoginFail(String loginFail) {
		MessageType.loginFail = loginFail;
	}
	public static String getChatContent() {
		return chatContent;
	}
	public static void setChatContent(String chatContent) {
		MessageType.chatContent = chatContent;
	}
	public static String getRequireOnline() {
		return requireOnline;
	}
	public static void setRequireOnline(String requireOnline) {
		MessageType.requireOnline = requireOnline;
	}
	public static String getResponseOnlineRequire() {
		return responseOnlineRequire;
	}
	public static void setResponseOnlineRequire(String responseOnlineRequire) {
		MessageType.responseOnlineRequire = responseOnlineRequire;
	}
	
	

}
