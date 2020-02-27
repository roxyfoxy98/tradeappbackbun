package ing.stockmarket;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class GenerateInstrumentsThread implements Runnable {

	static Logger logger = Logger.getLogger(GenerateInstrumentsThread.class);

	InstrumentResourcesLock lock;

	BlockingQueue<Instruments> generateInstruments;

	String[] descriptionOfInstruments = new String[] { "Loan", "Bond", "Stock", "Fund", "Option", "Future", "Swap",
			"Currency" };

	private static int k = 0;

	private static int y = 0;

	public GenerateInstrumentsThread(InstrumentResourcesLock lock, BlockingQueue<Instruments> generateInstruments) {

		this.lock = lock;

		this.generateInstruments = generateInstruments;

	}

	public static int incrementInstrumentSymbol() {

		return ++k;
	}

	@Override
	public void run() {

		logger.info("Start generating a random array of instruments!");

		Random rand = new Random(47);
		synchronized (lock) {
			try {

				for (int j = 0; j < 10; j++) {

					while (lock.getSwitchingInstrumentFlag() != 1 || !lock.getInstrumentFlag().get()) {

						try {
							lock.wait();
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}

					if (j == 9) {
						lock.getShutDownInstrument().set(false);
					}

					Instruments instrument = new Instruments();

					instrument.setSymbol(++k);

					instrument.setDescription(descriptionOfInstruments[rand.nextInt(descriptionOfInstruments.length)]);

					instrument.setMarginRate(rand.nextInt(4) + 1);

					instrument.setMinTradeVolume(rand.nextDouble() + 1);

					instrument.setMaxTradeVolume(rand.nextDouble() + 4);

					instrument.setNominalValueOf1Lot(rand.nextInt(15));

					instrument.setPipValue(rand.nextDouble() + 1);

					instrument.setTradingHours(rand.nextInt(24) + 1);

					generateInstruments.put(instrument);

					Thread.sleep(15);

				}

			} catch (Exception e) {

				logger.error("Error inside of GenerateInstrumentsThread.class");
			}
		}

	}

}
