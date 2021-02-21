package edu.lingnan.talklater.modules.chat.domain;

import java.util.HashMap;

import io.netty.channel.Channel;

/**
 * @Description: 用户账号和channel的关联关系处理
 */
public class UserChannelRel {

	private static HashMap<String, Channel> manager = new HashMap<>();

	public static void put(String Username, Channel channel) {
		manager.put(Username, channel);
	}
	
	public static Channel get(String Username) {
		return manager.get(Username);
	}
	
	public static void output() {
		for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
			System.out.println("Username: " + entry.getKey()
							+ ", ChannelId: " + entry.getValue().id().asLongText());
		}
	}
}
