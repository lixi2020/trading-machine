package trademachine;

import com.lsy.trademachine.exrternalAPI.ICHBTCRestAPI;
import com.lsy.trademachine.exrternalAPI.imp.CHBTCRestAPI;

public class TradeMachineApp {

	public static void main(String[] args) {
      ICHBTCRestAPI chbtcRestAPI = new CHBTCRestAPI();
      chbtcRestAPI.getTicker();
	}

}
