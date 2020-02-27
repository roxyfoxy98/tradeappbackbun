package PublisherSubscriberPattern;

import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import DatabaseConnection.CRUDservices;
import ing.stockmarket.Utils;

public class StockMarket extends Event {

	static Logger logger = Logger.getLogger(StockMarket.class);

	void subscribe(String channelName, Object subscriber) {

		if (!channels.containsKey(channelName)) {
			channels.put(channelName, new ConcurrentHashMap<Integer, WeakReference<Object>>());

		}

		channels.get(channelName).put(subscriber.hashCode(), new WeakReference<>(subscriber));
	}

	void publish(String channelName, Post message) throws Exception {
		try {
			if (channelName == "Transaction") {

				Future futureThread = Utils.getExecutorService().submit(new CRUDservices());

				futureThread.get();
				Utils.shutDownTheExecutor(Utils.getExecutorService(), "ExecutorService");
			}

		} catch (Exception e) {

			logger.error(e.getMessage());
		}

		for (Map.Entry<Integer, WeakReference<Object>> subs : channels.get(channelName).entrySet()) {
			WeakReference<Object> subscriberRef = subs.getValue();

			Object subscriberObj = subscriberRef.get();

			for (final Method method : subscriberObj.getClass().getDeclaredMethods()) {
				Annotation annotation = method.getAnnotation(OnMessage.class);
				if (annotation != null) {
					deliverMessage(subscriberObj, method, message);
				}
			}
		}
	}

	<T, P extends Post> boolean deliverMessage(T subscriber, Method method, Post message) {
		try {
			boolean methodFound = false;
			for (final Class paramClass : method.getParameterTypes()) {
				if (paramClass.equals(message.getClass())) {
					methodFound = true;
					break;
				}
			}
			if (methodFound) {
				method.setAccessible(true);
				method.invoke(subscriber, message);
			}

			return true;
		} catch (Exception e) {

			logger.error(e.getMessage());
		}

		return false;
	}
}
