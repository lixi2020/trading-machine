package com.lsy.trademachine.strategy.imp;

import com.lsy.trademachine.strategy.IAverageMovingWindow;

public class AverageMovingWindow extends BaseStrategy implements
		IAverageMovingWindow {
	private int count = 0;
	private int windowSize = 10;
	private double money = 10000;
	private boolean hasMoney = true;
	private double coin = 0;
	private double[] windows = new double[windowSize];
	private double averagePoint = 0;

	@Override
	public void start() throws Exception {
		super.start();
		while (count < 1800 || money > 10500 || money < 9700) {
			count = count + 1;
			
			
			

			Thread.sleep(2000l);
		}
	}

	@Override
	public void end() throws Exception {
		super.end();

	}

}
