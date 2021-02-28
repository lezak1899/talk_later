package edu.lingnan.talklater.modules.chat.domain;

import java.util.HashMap;

import io.netty.channel.Channel;

/**
 * Description: 用于绑定用户账号和通讯管道
 * date: 2021/2/5 11:28
 * author likunzhu
 * version
 * since JDK 1.8
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
