package com.lsy.trademachine.service.imp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lsy.trademachine.service.ITradingService;
import com.lsy.trademachine.strategy.IBaseStrategy;
import com.lsy.trademachine.strategy.imp.AverageMovingWindow;

public class TradingService implements ITradingService {
	private final Logger logger = LogManager.getLogger();
	private IBaseStrategy strategy = new AverageMovingWindow();

	public void healthCheck() {
		logger.info("All Good!");
		System.out.println("All Good!");
	}

	@Override
	public void startTrading() {
		try {
			strategy.start();
			strategy.end();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
