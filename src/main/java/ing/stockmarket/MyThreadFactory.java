package ing.stockmarket;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

// This class is used to rename the threadPools managed by ExecutorService & ScheduledExecutorService

public class MyThreadFactory implements ThreadFactory {

	private final String name;
	private final AtomicInteger integer = new AtomicInteger(1);

	public MyThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable r) {

		return new Thread(r, name + " Thread - " + integer.getAndIncrement());
	}

}
