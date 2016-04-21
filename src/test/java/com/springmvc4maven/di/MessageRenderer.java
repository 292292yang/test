/**
 * 
 */
package com.springmvc4maven.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author TrueLiteTrace
 *
 */
public class MessageRenderer {
	/*
	 * 1. DI를 적용하지 않았을 경우 
	 * public void render() { // MessageProvider mp = new 
	 *  HelloWorldMessageProvider(); MessageProvider mp = new
	 * HiWorldMessageProvider(); System.out.println(mp.getMessage()); }
	 * 
	 * public static void main(String[] agrs) { MessageRenderer renderer = new
	 * MessageRenderer(); renderer.render(); }
	 */

	/**
	 * 2. DI를 적용하였을 경우
	 * 
	 * private MessageProvider messageProvider;
	 * 
	 * public void setMessageProvider(MessageProvider messageProvider) {
	 * this.messageProvider = messageProvider; }
	 * 
	 * public void render() { System.out.println(messageProvider.getMessage());
	 * }
	 * 
	 * // client public static void mian(String[] args) { MessageRenderer
	 * renderer = new MessageRenderer(); renderer.setMessageProvider(new
	 * HelloWorldMessageProvider()); renderer.render();
	 * 
	 * renderer.setMessageProvider(new HiWorldMessageProvider());
	 * renderer.render(); }
	 */

	/**
	 * 3. Spring Bean Configuration xml file을 설정하였을 경우
	 */
	private MessageProvider messageProvider;

	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}

	public void render() {
		System.out.println(messageProvider.getMessage());
	}

	// client
	public static void main(String[] args) {
		// ApplicationContext를 사용하여 각 클래스들 간의 의존관계를 직접 di.xml file을 읽어 들여 맵핑 한다.
		ApplicationContext ac = new ClassPathXmlApplicationContext("di.xml");
		MessageRenderer renderer = (MessageRenderer)ac.getBean("messageRenderer");
		renderer.render();
	}
}
