package com.lsy.trademachine.exrternalAPI;

import com.lsy.trademachine.exrternalAPI.datacontract.TickerResponse;



public interface ICHBTCRestAPI {
	public TickerResponse getTicker();
	public void placeOrder();
	public void cancelOrder();
}
