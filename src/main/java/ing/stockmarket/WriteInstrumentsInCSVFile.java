package ing.stockmarket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class WriteInstrumentsInCSVFile implements Runnable {

	static Logger logger = Logger.getLogger(WriteInstrumentsInCSVFile.class);

	InstrumentResourcesLock lock;

	BlockingQueue<Instruments> generateInstruments;

	public WriteInstrumentsInCSVFile(InstrumentResourcesLock lock, BlockingQueue<Instruments> generateInstruments) {

		this.lock = lock;

		this.generateInstruments = generateInstruments;
	}

	public void run() {

		logger.info("The date when the data is written in the CSV file is :" + new Date());

		PrintWriter printWriter = null;

		try {

			Thread.sleep(40);

			FileWriter fileWriter = new FileWriter(Configuration.getINSTRUMENTS_CSVFILE_PATH(), true);

			printWriter = new PrintWriter(fileWriter);

			while (true) {

				if (lock.getShutDownInstrument().get()) {

					Instruments instrument = generateInstruments.take();

					InstrumentResourcesLock.getInstrumentsLinkedList().add(instrument);

					logger.info("Start writing into file: " + Configuration.getINSTRUMENTS_CSVFILE_PATH());

					fileWriter.append(String.valueOf(instrument.getSymbol()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(instrument.getDescription()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(instrument.getNominalValueOf1Lot()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(instrument.getPipValue()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(instrument.getMinTradeVolume()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(instrument.getMaxTradeVolume()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(instrument.getMarginRate()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(instrument.getTradingHours()));
					fileWriter.append("\n");

				} else {

					try {

						printWriter.flush();

						printWriter.close();

						logger.info("This file was closed successful!\n");

						lock.getShutDownInstrument().set(true);

						return;

					} catch (Exception ex) {

						logger.error("Forcefully shutdown \"Instrument.csv\" ");
					}

				}

			}

		} catch (InterruptedException | IOException e) {

			e.printStackTrace();

			printWriter.flush();

			printWriter.close();

			logger.info("This file was closed successful!\n");
			lock.getShutDownInstrument().set(true);

		}
	}
}
