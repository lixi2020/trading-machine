package com.lsy.trademachine.strategy.imp;

import java.util.Date;

import com.lsy.trademachine.strategy.IBaseStrategy;

public abstract class BaseStrategy implements IBaseStrategy {
	protected boolean isStarted = false;
	protected Date starDateTime;
	protected Date endDateTime;

	@Override
	public void start() throws Exception {
		isStarted = true;
		starDateTime = new Date();
	}

	@Override
	public void end() throws Exception {
		isStarted = false;
		endDateTime = new Date();
	}

}
