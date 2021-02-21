package edu.lingnan.talklater.modules.chat.domain;

import edu.lingnan.talklater.modules.msg.domain.MsgXx;

import java.io.Serializable;

public class DataContent implements Serializable {

	private static final long serialVersionUID = 8021381444738260454L;

	private Integer action;		// 动作类型
	private MsgXx chatMsg;	// 用户的聊天内容entity
	private String extand;		// 扩展字段
	
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public MsgXx getChatMsg() {
		return chatMsg;
	}
	public void setChatMsg(MsgXx chatMsg) {
		this.chatMsg = chatMsg;
	}
	public String getExtand() {
		return extand;
	}
	public void setExtand(String extand) {
		this.extand = extand;
	}
}
