package ing.stockmarket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class WriteTransactionsInCSVFile implements Runnable {

	static Logger logger = Logger.getLogger(WriteTransactionsInCSVFile.class);

	TransactionResourcesLock lock;

	BlockingQueue<Transaction> generateTransaction;

	public WriteTransactionsInCSVFile(TransactionResourcesLock lock, BlockingQueue<Transaction> generateTransaction) {

		this.lock = lock;
		this.generateTransaction = generateTransaction;
	}

	public void run() {

		logger.info("The date when the data is written in the CSV file is :" + new Date());

		PrintWriter printWriter = null;

		try {

			Thread.sleep(40);

			FileWriter fileWriter = new FileWriter(Configuration.getTRANSACTION_CSVFILE_PATH(), true);

			printWriter = new PrintWriter(fileWriter);

			while (true) {

				if (lock.getShutDownTransaction().get()) {

					Transaction trans = generateTransaction.take();

					TransactionResourcesLock.getTransactionLinkedList().add(trans);

					logger.info("Start writing into file: " + Configuration.getTRANSACTION_CSVFILE_PATH());

					printWriter.append(String.valueOf(trans.getId()));
					printWriter.append(",");
					printWriter.append(String.valueOf(trans.getAsk()));
					printWriter.append(",");
					printWriter.append(String.valueOf(trans.getBid()));
					printWriter.append(",");
					printWriter.append(String.valueOf(trans.getQty()));
					printWriter.append(",");
					printWriter.append(String.valueOf(trans.getTIME_LIMIT()));
					printWriter.append("\n");

				} else {

					try {

						printWriter.flush();

						printWriter.close();

						logger.info("This file was closed successful!\n");

						lock.getShutDownTransaction().set(true);

						return;

					} catch (Exception ex) {

						logger.error("Forcefully shutdown \"Transaction.csv\" ");
						logger.error(ex.getMessage());
					}

				}

			}

		} catch (InterruptedException | IOException e) {

			logger.error(e.getMessage());

			printWriter.flush();

			printWriter.close();

			logger.info("This file was closed successful!\n");

			lock.getShutDownTransaction().set(true);

		}
	}
}
