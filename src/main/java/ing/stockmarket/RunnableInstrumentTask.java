package ing.stockmarket;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class RunnableInstrumentTask implements Runnable {

	static Logger logger = Logger.getLogger(RunnableInstrumentTask.class);

	InstrumentResourcesLock lock;

	BlockingQueue<Instruments> generateInstruments;

	private String threadName;

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public RunnableInstrumentTask(String name, InstrumentResourcesLock lock,
			BlockingQueue<Instruments> generateInstruments) {

		threadName = name;
		this.lock = lock;
		this.generateInstruments = generateInstruments;

	}

	public void run() {

		logger.info("Inside of duplication InstrumentThread!");

		logger.info("The time is: " + new Date());

		int z = InstrumentResourcesLock.getInstrumentsLinkedList().size();

		lock.setSwitchingInstrumentFlag(2);

		synchronized (lock) {
			try {

				if (lock.getInstrumentFlag().compareAndSet(true, false)) {

					if (z != 0 && lock.getShutDownInstrument().get()) {

						Instruments instrument = new Instruments();

						instrument.setSymbol(GenerateInstrumentsThread.incrementInstrumentSymbol());
						instrument.setDescription(
								InstrumentResourcesLock.getInstrumentsLinkedList().getLast().getDescription());
						instrument.setNominalValueOf1Lot(
								InstrumentResourcesLock.getInstrumentsLinkedList().getLast().getNominalValueOf1Lot());
						instrument.setPipValue(
								InstrumentResourcesLock.getInstrumentsLinkedList().getLast().getPipValue());
						instrument.setMinTradeVolume(
								InstrumentResourcesLock.getInstrumentsLinkedList().getLast().getMinTradeVolume());
						instrument.setMaxTradeVolume(
								InstrumentResourcesLock.getInstrumentsLinkedList().getLast().getMaxTradeVolume());
						instrument.setMarginRate(
								InstrumentResourcesLock.getInstrumentsLinkedList().getLast().getMarginRate());
						instrument.setTradingHours(
								InstrumentResourcesLock.getInstrumentsLinkedList().getLast().getTradingHours());

						// Instruments instrument =
						// InstrumentResourcesLock.getInstrumentsLinkedList().getLast();

						InstrumentResourcesLock.getInstrumentsLinkedList().add(instrument);

						try {
							generateInstruments.put(instrument);

						} catch (InterruptedException e) {

							logger.error(e.getMessage());
						}
						logger.info("Start writing into file: " + Configuration.getINSTRUMENTS_CSVFILE_PATH());
						logger.info("The duplicated transaction is: " + "\t SYMBOL:  " + instrument.getSymbol()
								+ "\t DESCRIPTION:  " + instrument.getDescription() + "\t NominalValueOf1Lot:  "
								+ instrument.getNominalValueOf1Lot() + "\t PipValue:  " + instrument.getPipValue()
								+ "\t MinTradeVolume:  " + instrument.getMinTradeVolume() + "\t MaxTradeVolume:  "
								+ instrument.getMaxTradeVolume() + "\t MarginRate:  " + instrument.getMarginRate()
								+ "\t TradingHours:  " + instrument.getTradingHours());

					} else {

						logger.info("The execution of the generator has been done!");
					}

				}

			} catch (Exception e) {

				logger.info("Error arised from Instrument Duplicator!");

				logger.error(e.getMessage());

			} finally {

				lock.getInstrumentFlag().set(true);

				lock.setSwitchingInstrumentFlag(1);

				lock.notifyAll();
			}
		}
	}

}
