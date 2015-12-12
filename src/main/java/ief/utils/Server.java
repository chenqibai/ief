package ief.utils;

import java.util.ArrayList;
import java.util.List;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

public class Server {
	public void sendpush(List<String> tokens, String path, String password, String message, Integer count,
			boolean sendCount) {
		try {
			// message是一个json的字符串{“aps”:{“alert”:”iphone推送测试”}}
			PushNotificationPayload payLoad = PushNotificationPayload.fromJSON(message);
			payLoad.addAlert("iphone推送测试   www.guligei.com"); // 消息内容
			payLoad.addBadge(count); // iphone应用图标上小红圈上的数值
			payLoad.addSound("default"); // 铃音 默认
			PushNotificationManager pushManager = new PushNotificationManager();
			// true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
			pushManager.initializeConnection(new AppleNotificationServerBasicImpl(path, password, false));
			List<PushedNotification> notifications = new ArrayList<PushedNotification>();
			// 发送push消息
			if (sendCount) {
				Device device = new BasicDevice();
				device.setToken(tokens.get(0));
				PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
				notifications.add(notification);
			} else {
				List<Device> device = new ArrayList<Device>();
				for (String token : tokens) {
					device.add(new BasicDevice(token));
				}
				notifications = pushManager.sendNotifications(payLoad, device);
			}
			List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
			List<PushedNotification> successfulNotifications = PushedNotification
					.findSuccessfulNotifications(notifications);
			int failed = failedNotifications.size();
			int successful = successfulNotifications.size();
			if (successful > 0 && failed == 0) {
				System.out.print("推送成功" + failedNotifications.toString());
			} else if (successful == 0 && failed > 0) {
				System.out.print("推送失败" + failedNotifications.toString());
			} else if (successful == 0 && failed == 0) {
				System.out.print("推送失败" + failedNotifications.toString());
				System.out.println("No notifications could be sent, probably because of a critical error");
			} else {
				System.out.print("推送失败" + failedNotifications.toString());
			}
			// pushManager.stopConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Server send = new Server();
		List<String> tokens = new ArrayList<String>();
		tokens.add("936e4d92c996b273395dcbc54ee46f13cca00f12461ead8612a0224f3fddcf83");
		String path = "iefDev-1234.p12";
		String password = "1234";
		String message = "{'aps':{'alert':'iphone推送测试 www.baidu.com'}}";
		Integer count = 1;
		boolean sendCount = false;
		send.sendpush(tokens, path, password, message, count, sendCount);
	}
}
