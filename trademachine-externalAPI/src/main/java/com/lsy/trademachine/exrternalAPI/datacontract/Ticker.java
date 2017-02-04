package com.lsy.trademachine.exrternalAPI.datacontract;

import java.io.Serializable;

public class Ticker implements Serializable{
	private double high;
	private double low;
	private double buy;
	private double sell;
	private double last;
	private double vol;

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getBuy() {
		return buy;
	}

	public void setBuy(double buy) {
		this.buy = buy;
	}

	public double getSell() {
		return sell;
	}

	public void setSell(double sell) {
		this.sell = sell;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public double getVol() {
		return vol;
	}

	public void setVol(double vol) {
		this.vol = vol;
	}

	@Override
	public String toString() {
		return "Ticker [high=" + high + ", low=" + low + ", buy=" + buy
				+ ", sell=" + sell + ", last=" + last + ", vol=" + vol + "]";
	}

}
