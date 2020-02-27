package PublisherSubscriberPattern;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

public class Event {

	static {
		init();
	}

	static StockMarket stockMarketReference;

	static ConcurrentHashMap<String, ConcurrentHashMap<Integer, WeakReference<Object>>> channels;

	static void init() {
		channels = new ConcurrentHashMap<>();
		stockMarketReference = new StockMarket();
	}

	public static ConcurrentHashMap<String, ConcurrentHashMap<Integer, WeakReference<Object>>> getChannels() {
		return channels;
	}

	public static void setChannels(
			ConcurrentHashMap<String, ConcurrentHashMap<Integer, WeakReference<Object>>> channels) {
		Event.channels = channels;
	}

}