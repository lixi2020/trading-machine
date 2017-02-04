package com.lsy.trademachine.service.imp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class AppStart {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"classpath:ApplicationContext.xml");
		TradingService tradingService = (TradingService) applicationContext
				.getBean("tradingService");
		tradingService.startTrading();
	}

}
