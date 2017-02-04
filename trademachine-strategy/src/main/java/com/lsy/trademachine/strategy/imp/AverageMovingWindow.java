package com.lsy.trademachine.strategy.imp;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lsy.trademachine.exrternalAPI.ICHBTCRestAPI;
import com.lsy.trademachine.exrternalAPI.datacontract.Ticker;
import com.lsy.trademachine.exrternalAPI.imp.CHBTCRestAPI;
import com.lsy.trademachine.strategy.IAverageMovingWindow;

public class AverageMovingWindow extends BaseStrategy implements
		IAverageMovingWindow {
	private final Logger logger = LogManager.getLogger();
	private int count = 0;
	private int windowSize = 10;
	private double money = 10000;
	private double netWorth=0;
	private boolean hasMoney = true;
	private double coin = 0;
	private Window buyWindow = new Window(windowSize);
	private Window sellWindow = new Window(windowSize);
	private double averageBuyPrice = 0;
	private double averageSellPrice = 0;
	private int currentWindowCount = 0;
	private ICHBTCRestAPI CHBTCRestAPI = new CHBTCRestAPI();
	private Ticker ticker;

	@Override
	public void start() throws Exception {
		super.start();
		while (count < 3600 * 5) {
			count = count + 1;
			ticker = CHBTCRestAPI.getTicker().getTicker();
			
			
			// move window
			if (currentWindowCount < windowSize) {
				buyWindow.add(ticker.getBuy());
				sellWindow.add(ticker.getSell());
				currentWindowCount++;
				continue;
			}else{
				buyWindow.add(ticker.getBuy());
				sellWindow.add(ticker.getSell());			
			}

			// calculate average buy price
			averageBuyPrice = buyWindow.calculateAverage();
			averageSellPrice = sellWindow.calculateAverage();
			
			// current buy and sell price
			double buy=ticker.getBuy();
			double sell=ticker.getSell();
			
			
			//decide buy or sell or hold
			if(hasMoney){
				//buy?
				if(buy>=averageBuyPrice){
					//Print ticker
					logger.info(ticker.toString());
					//likely goes up, buy!
					DecimalFormat df=new DecimalFormat("#.0000");
					df.setRoundingMode(RoundingMode.DOWN);
					coin=Double.parseDouble(df.format(money/buy));
					money=0;
					hasMoney=false;
					netWorth=coin*sell;
					logger.info("Buy Coin:"+coin+"Networth:"+netWorth+" Buy Price:"+buy);
				}
			}else{
				//sell?
				if(sell<=averageSellPrice){
					//Print ticker
					logger.info(ticker.toString());
					//likely goes down, sell!
					money=coin*(sell-0.03);
					coin=0;
					hasMoney=true;
					netWorth=money;
					logger.info("Sell Coin:"+coin+"Networth:"+netWorth+" Sell Price:"+sell);
				}
			}
			
			

			Thread.sleep(2000l);
		}
	}

	@Override
	public void end() throws Exception {
		super.end();

	}

	class Window {
		private int size;
		private int currentSize;
		private WindowNode head;
		
		private WindowNode tail;
		Window(int size){
			this.size=size;
			this.currentSize=0;
		}
		public void add(double value){
			if(currentSize==0){
				head=new WindowNode(value);
				tail=head;
				currentSize++;
			}else if(currentSize<size){
				tail.setNextNode(new WindowNode(value));
				tail=tail.getNextNode();
				currentSize++;
			}else{
				head=head.getNextNode();
				tail.setNextNode(new WindowNode(value));
				tail=tail.getNextNode();
			}
		}
		
		public double calculateAverage(){
			double total=0;
			WindowNode node=head;
			while(node!=null){
				total=total+node.getValue();
				node=node.getNextNode();
			}
			return total/currentSize;
		}
	}
	
	class WindowNode{
		private double value;
		private WindowNode nextNode;
		private WindowNode preNode;
		WindowNode(double value){
			this.value=value;
		}
		
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}
		public WindowNode getNextNode() {
			return nextNode;
		}
		public void setNextNode(WindowNode nextNode) {
			this.nextNode = nextNode;
		}
		public WindowNode getPreNode() {
			return preNode;
		}
		public void setPreNode(WindowNode preNode) {
			this.preNode = preNode;
		}
		
	}

}
