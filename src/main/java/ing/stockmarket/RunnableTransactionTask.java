package ing.stockmarket;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class RunnableTransactionTask implements Runnable {

	static Logger logger = Logger.getLogger(RunnableTransactionTask.class);

	TransactionResourcesLock lock;

	BlockingQueue<Transaction> generateTransaction;

	private String threadName;

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public RunnableTransactionTask(String name, TransactionResourcesLock lock,
			BlockingQueue<Transaction> generateTransaction) {

		threadName = name;
		this.lock = lock;
		this.generateTransaction = generateTransaction;

	}

	public void run() {

		logger.info("Inside of duplication thread!");

		System.out.println("The time is: " + new Date());

		int z = TransactionResourcesLock.getTransactionLinkedList().size();

		lock.setSwitchingTransactionFlag(2);

		synchronized (lock) {
			try {

				if (lock.getTransactionFlag().compareAndSet(true, false)) {

					if (z != 0 && lock.getShutDownTransaction().get()) {

						Transaction transaction = new Transaction();

						transaction.setId(GenerateTransactionsThread.incrementTransactionID());
						transaction.setAsk(TransactionResourcesLock.getTransactionLinkedList().getLast().getAsk());
						transaction.setBid(TransactionResourcesLock.getTransactionLinkedList().getLast().getBid());
						transaction.setQty(TransactionResourcesLock.getTransactionLinkedList().getLast().getQty());
						transaction.setTIME_LIMIT(
								TransactionResourcesLock.getTransactionLinkedList().getLast().getTIME_LIMIT());
						transaction.setInstrument_id(
								TransactionResourcesLock.getTransactionLinkedList().getLast().getInstrument_id());
						transaction.setCustomer_id(
								TransactionResourcesLock.getTransactionLinkedList().getLast().getCustomer_id());
						double valueOfQTY = transaction.getAsk();

						transaction.setBid(valueOfQTY);

						TransactionResourcesLock.getTransactionLinkedList().add(transaction);
						try {

							generateTransaction.put(transaction);

						} catch (InterruptedException e) {

							logger.error(e.getMessage());
						}

						logger.info("Start writing into file: " + Configuration.getTRANSACTION_CSVFILE_PATH());

						logger.info("The duplicated transaction is: " + "\t ID:  " + transaction.getId() + "\t BID:  "
								+ transaction.getBid() + "\t ASK:  " + transaction.getAsk() + "\t QTY:  "
								+ transaction.getQty());

					} else {

						logger.info("The execution of the generator has been done!");
					}

				}

			} catch (Exception e) {

				logger.info("Error arised from Transaction Duplicator!");

				logger.error(e.getMessage());

			} finally {

				lock.setSwitchingTransactionFlag(1);

				lock.getTransactionFlag().set(true);

				lock.notifyAll();
			}
		}
	}

}
