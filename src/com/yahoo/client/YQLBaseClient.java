package com.yahoo.client;

public class YQLBaseClient {

	/**
	 * @param woeid
	 */
	public void getWeather(String woeid, CallBackListener listener) {
		YQLBaseClientThread YQLBaseClientThread = new YQLBaseClientThread();
		YQLBaseClientThread.setListener(listener);
		YQLBaseClientThread.getWeather(woeid);
	}
	
	public void getNews(CallBackListener listener) {
		YQLBaseClientThread YQLBaseClientThread = new YQLBaseClientThread();
		YQLBaseClientThread.setListener(listener);
		YQLBaseClientThread.getNews();
	}
}
