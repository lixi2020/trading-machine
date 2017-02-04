package com.lsy.trademachine.exrternalAPI.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.lsy.trademachine.exrternalAPI.ICHBTCRestAPI;
import com.lsy.trademachine.exrternalAPI.datacontract.TickerResponse;

public class CHBTCRestAPI implements ICHBTCRestAPI {
	public void placeOrder() {
	}

	public void cancelOrder() {
	}

	@Override
	public TickerResponse getTicker() {
		final String uri = "http://api.chbtc.com/data/v1/ticker";
		RestTemplate restTemplate = new RestTemplate();
		for (HttpMessageConverter<?> myConverter : restTemplate
				.getMessageConverters()) {
			if (myConverter instanceof MappingJackson2HttpMessageConverter) {
				List<MediaType> myMediaTypes = new ArrayList<MediaType>();
				myMediaTypes.addAll(myConverter.getSupportedMediaTypes());
				myMediaTypes.add(MediaType
						.parseMediaType("text/javascript; charset=utf-8"));
				((MappingJackson2HttpMessageConverter) myConverter)
						.setSupportedMediaTypes(myMediaTypes);
			}
		}

		TickerResponse tickerResponse = restTemplate.getForObject(uri,
				TickerResponse.class);

		System.out.println(tickerResponse.getTicker());
		return tickerResponse;
	}
}
