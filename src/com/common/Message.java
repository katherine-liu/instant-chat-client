/*
 * object stream
 * user's information
 * Message should include: sent ID, receive ID, content, time
*/

package com.common;

public class Message implements java.io.Serializable{//make message in order
	
	private String msgType;
	private String senderId;
	private String receiverId;
	private String content;
	private String sentTime;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senterId) {
		this.senderId = senterId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSentTime() {
		return sentTime;
	}

	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}
	

}
