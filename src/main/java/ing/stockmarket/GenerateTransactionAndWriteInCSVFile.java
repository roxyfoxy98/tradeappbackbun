package ing.stockmarket;

import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class GenerateTransactionAndWriteInCSVFile implements Runnable {

	static Logger logger = Logger.getLogger(GenerateTransactionAndWriteInCSVFile.class);

	TransactionResourcesLock transactionResourcesLock;
	InstrumentResourcesLock instrumentResourcesLock;
	CustomerResourcesLock customerResourcesLock;

	BlockingQueue<Transaction> blockingQueueTransactions;
	BlockingQueue<Instruments> blockingQueueInstruments;
	BlockingQueue<Customers> blockingQueueCustomers;

	private long sleep;

	// Constructor to run all threads

	public GenerateTransactionAndWriteInCSVFile(TransactionResourcesLock transactionResourcesLock,
			InstrumentResourcesLock instrumentResourcesLock, CustomerResourcesLock customerResourcesLock,
			BlockingQueue<Transaction> blockingQueueTransactions, BlockingQueue<Instruments> blockingQueueInstruments,
			BlockingQueue<Customers> blockingQueueCustomers, long sleep) {

		this.transactionResourcesLock = transactionResourcesLock;
		this.instrumentResourcesLock = instrumentResourcesLock;
		this.customerResourcesLock = customerResourcesLock;
		this.blockingQueueTransactions = blockingQueueTransactions;
		this.blockingQueueInstruments = blockingQueueInstruments;
		this.blockingQueueCustomers = blockingQueueCustomers;

		this.sleep = sleep;
	}

	@Override
	public void run() {

		try {

			Thread.sleep(sleep);

			GenerateTransactionsThread generateTransactions = new GenerateTransactionsThread(transactionResourcesLock,
					blockingQueueTransactions);

			WriteTransactionsInCSVFile writeTransactionInCSVFile = new WriteTransactionsInCSVFile(
					transactionResourcesLock, blockingQueueTransactions);

			GenerateInstrumentsThread generateInstruments = new GenerateInstrumentsThread(instrumentResourcesLock,
					blockingQueueInstruments);

			WriteInstrumentsInCSVFile writeInstrumentsInCSVFile = new WriteInstrumentsInCSVFile(instrumentResourcesLock,
					blockingQueueInstruments);

			GenerateCustomerThread generateCustomers = new GenerateCustomerThread(customerResourcesLock,
					blockingQueueCustomers);

			WriteCustomersInCSVFile writeCustomersInCSVFile = new WriteCustomersInCSVFile(customerResourcesLock,
					blockingQueueCustomers);

			// creation of child thread

			Utils.getExecutorService().execute(generateTransactions);
			Utils.getExecutorService().execute(writeTransactionInCSVFile);

			Utils.getExecutorService().execute(generateCustomers);
			Utils.getExecutorService().execute(writeCustomersInCSVFile);

			Utils.getExecutorService().execute(generateInstruments);
			Utils.getExecutorService().execute(writeInstrumentsInCSVFile);

		} catch (Exception e) {
			logger.error("Error : " + e.getMessage());
		}

	}
}
