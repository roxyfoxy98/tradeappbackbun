package PublisherSubscriberPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ing.stockmarket.CustomerResourcesLock;
import ing.stockmarket.Customers;
import ing.stockmarket.GenerateTransactionAndWriteInCSVFile;
import ing.stockmarket.InstrumentResourcesLock;
import ing.stockmarket.Instruments;
import ing.stockmarket.RunnableCustomerTask;
import ing.stockmarket.RunnableInstrumentTask;
import ing.stockmarket.RunnableTransactionTask;
import ing.stockmarket.ThreadForTesting;
import ing.stockmarket.Transaction;
import ing.stockmarket.TransactionResourcesLock;
import ing.stockmarket.Utils;

public class Post {

	// Business logic of threads.

	static Logger logger = Logger.getLogger(Post.class);

	public String message;

	TransactionResourcesLock transactionResourcesLock = new TransactionResourcesLock();
	InstrumentResourcesLock instrumentResourcesLock = new InstrumentResourcesLock();
	CustomerResourcesLock customerResourcesLock = new CustomerResourcesLock();

	BlockingQueue<Transaction> blockingQueueTransactions = new LinkedBlockingQueue<>();
	BlockingQueue<Instruments> blockingQueueInstruments = new LinkedBlockingQueue<>();
	BlockingQueue<Customers> blockingQueueCustomers = new LinkedBlockingQueue<>();

	RunnableInstrumentTask instrumentScheduler = new RunnableInstrumentTask("Instrument Duplicator Thread ",
			instrumentResourcesLock, blockingQueueInstruments);

	RunnableCustomerTask custumerScheduler = new RunnableCustomerTask("Customer Duplicator Thread ",
			customerResourcesLock, blockingQueueCustomers);

	RunnableTransactionTask transactionScheduler = new RunnableTransactionTask("Transaction Duplicator Thread ",
			transactionResourcesLock, blockingQueueTransactions);

	Post(String message) {

		this.message = message;

		if (message == "Transaction") {
			this.runningAllThreads();
		} else if (message == "Test") {

			this.RunningThreadForTesting();
		} else {

			logger.info("Something weired!");
		}
	}

	public void runningAllThreads() {

		try {

			List<Future> futureList = new ArrayList<Future>();

			futureList.add(Utils.getExecutorService()
					.submit(new GenerateTransactionAndWriteInCSVFile(transactionResourcesLock, instrumentResourcesLock,
							customerResourcesLock, blockingQueueTransactions, blockingQueueInstruments,
							blockingQueueCustomers, 1)));

			futureList.add(Utils.getExecutorService()
					.submit(new GenerateTransactionAndWriteInCSVFile(transactionResourcesLock, instrumentResourcesLock,
							customerResourcesLock, blockingQueueTransactions, blockingQueueInstruments,
							blockingQueueCustomers, 12000)));

			futureList.add(Utils.getExecutorService()
					.submit(new GenerateTransactionAndWriteInCSVFile(transactionResourcesLock, instrumentResourcesLock,
							customerResourcesLock, blockingQueueTransactions, blockingQueueInstruments,
							blockingQueueCustomers, 24000)));

			Utils.getSchedulerExecutorService()
					.scheduleAtFixedRate(new RunnableInstrumentTask("Instrument Duplicator Thread ",
							instrumentResourcesLock, blockingQueueInstruments), 2, 10, TimeUnit.SECONDS);
			Utils.getSchedulerExecutorService()
					.scheduleAtFixedRate(new RunnableCustomerTask("Customer Duplicator Thread ", customerResourcesLock,
							blockingQueueCustomers), 2, 10, TimeUnit.SECONDS);

			Utils.getSchedulerExecutorService()
					.scheduleAtFixedRate(new RunnableTransactionTask("Transaction Duplicator Thread ",
							transactionResourcesLock, blockingQueueTransactions), 2, 10, TimeUnit.SECONDS);

			for (Future future : futureList) {

				future.get();
			}

			Utils.shutDownTheExecutor(Utils.getSchedulerExecutorService(), "ScheduledExecutorService");

		} catch (Exception e) {

			logger.error(e.getMessage());

		}

	}

	public void RunningThreadForTesting() {

		Thread newThread = new Thread(new ThreadForTesting());

		newThread.start();
	}
}
