package com.lsy.trademachine.service.imp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lsy.trademachine.service.ITradingService;

public class TradingService implements ITradingService {
	private final Logger logger = LogManager.getLogger();

	public void healthCheck() {
		logger.info("All Good!");
		System.out.println("All Good!");
	}

}
