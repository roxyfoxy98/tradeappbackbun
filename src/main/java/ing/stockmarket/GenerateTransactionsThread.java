package ing.stockmarket;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class GenerateTransactionsThread implements Runnable {

	static Logger logger = Logger.getLogger(GenerateTransactionsThread.class);

	TransactionResourcesLock lock;

	BlockingQueue<Transaction> generateTransaction;

	private static int i = 0;
	private static int o = 1;

	public GenerateTransactionsThread(TransactionResourcesLock lock, BlockingQueue<Transaction> generateTransaction) {

		this.lock = lock;

		this.generateTransaction = generateTransaction;

	}

	public static int incrementTransactionID() {

		return ++i;
	}

	public void run() {

		logger.info("Start generating a random array of transactions!");

		Random rand = new Random();
		synchronized (lock) {
			try {

				for (int j = 0; j < 2000; j++) {

					while (lock.getSwitchingTransactionFlag() != 1 || !lock.getTransactionFlag().get()) {

						try {
							lock.wait();
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}

					if (j == 1999) {

						lock.getShutDownTransaction().set(false);
					}
					Transaction trans = new Transaction();

					trans.setId(++i);

					trans.setAsk(Math.round(Configuration.getMin_Ask()
							+ (double) (Math.random() * ((Configuration.getMax_Ask() - Configuration.getMin_Ask()) + 1))
									* 100.0)
							/ 100.0);

					trans.setBid(Math.round(Configuration.getMin_Bid()
							+ (double) (Math.random() * ((Configuration.getMax_Bid() - Configuration.getMin_Bid()) + 1))
									* 100.0)
							/ 100.0);

					trans.setQty(rand.nextInt(200));
					trans.setTIME_LIMIT(rand.nextInt(30));

					generateTransaction.put(trans);

					Thread.sleep(2);

				}

			} catch (Exception e) {

				logger.info("Error inside of GenerateTransactionsThread.class");
			}
		}

	}

}
