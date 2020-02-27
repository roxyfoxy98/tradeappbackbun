package ing.stockmarket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class Utils {

	static Logger logger = Logger.getLogger(Utils.class);

	public static int i = 0;
	public static int n = 0;
	public static int k = 0;

	private Utils() {
	}

	private static ScheduledExecutorService schedulerExecutorService = Executors.newScheduledThreadPool(
			Runtime.getRuntime().availableProcessors(), new MyThreadFactory("ScheduledExecutorService"));

	// Runtime.getRuntime().availableProcessors()

	private static ExecutorService executorService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new MyThreadFactory("ExecutorService"));

	public static ScheduledExecutorService getSchedulerExecutorService() {
		return schedulerExecutorService;
	}

	public static ExecutorService getExecutorService() {
		return executorService;
	}

	// Read from CSV file

	public static LinkedList<Transaction> readCsv(String path, int qty, TypeOfReading typeOfReading) {

		logger.info("Start reading generator from : " + path + " \n");
		BufferedReader reader = null;
		LinkedList<Transaction> transactionList = new LinkedList<Transaction>();
		try {

			String line = "";
			reader = new BufferedReader(new FileReader(path));

			switch (typeOfReading) {
			case LESS:

				while ((line = reader.readLine()) != null) {
					String[] fields = line.split(",");

					if (Integer.parseInt(fields[3]) < qty) {
						Transaction trans = new Transaction();
						trans.setId((Integer.parseInt(fields[0])));
						trans.setAsk(Double.parseDouble(fields[1]));
						trans.setBid(Double.parseDouble(fields[2]));
						trans.setQty(Integer.parseInt(fields[3]));
						transactionList.add(trans);
					}

				}
				break;

			case GREATER:

				while ((line = reader.readLine()) != null) {
					String[] fields = line.split(",");

					if (Integer.parseInt(fields[3]) > qty) {
						Transaction trans = new Transaction();
						trans.setId((Integer.parseInt(fields[0])));
						trans.setAsk(Double.parseDouble(fields[1]));
						trans.setBid(Double.parseDouble(fields[2]));
						trans.setQty(Integer.parseInt(fields[3]));
						transactionList.add(trans);
					}

				}

				break;

			case DEFAULT:

				while ((line = reader.readLine()) != null) {
					String[] fields = line.split(",");

					Transaction trans = new Transaction();
					trans.setId((Integer.parseInt(fields[0])));
					trans.setAsk(Double.parseDouble(fields[1]));
					trans.setBid(Double.parseDouble(fields[2]));
					trans.setQty(Integer.parseInt(fields[3]));
					transactionList.add(trans);

				}

				break;

			default:
				break;
			}

		} catch (Exception ex) {

			logger.info("The specified file wasn't found!");
			logger.error(ex.getMessage());

		} finally {

			try {
				reader.close();
			} catch (IOException e) {

				logger.info("Input/Output exception was thrown!");
				logger.error(e.getMessage());
			}
		}
		return transactionList;
	}

	// Write in a CSV file

	public static LinkedList<Transaction> writeCSVTransaction(String path, LinkedList<Transaction> list) {

		LinkedList<Transaction> randomTransaction = new LinkedList<Transaction>();

		logger.info("Starting write user.csv file: " + path);

		randomTransaction = list;

		FileWriter fileWriter = null;

		try {

			fileWriter = new FileWriter(path);

			for (Transaction randTrans : randomTransaction) {

				fileWriter.append(String.valueOf(randTrans.getId()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randTrans.getAsk()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randTrans.getBid()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randTrans.getQty()));
				fileWriter.append("\n");

			}
		} catch (Exception e) {

			logger.error(e.getMessage());
		} finally {

			try {

				fileWriter.flush();

				fileWriter.close();

				logger.info("This file was closed successful!\n");

			} catch (Exception ex) {

				logger.error(ex.getMessage());
			}
		}

		return randomTransaction;
	}

	public static LinkedList<Customers> writeCSVCustomers(String path, LinkedList<Customers> list) {

		LinkedList<Customers> randomCustomer = new LinkedList<Customers>();

		logger.info("Starting write user.csv file: " + path);

		randomCustomer = list;

		FileWriter fileWriter = null;

		try {

			fileWriter = new FileWriter(path);

			for (Customers randCustomer : randomCustomer) {

				fileWriter.append(String.valueOf(randCustomer.getID()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randCustomer.getName()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randCustomer.getAddress()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randCustomer.getPhone()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randCustomer.getSex()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randCustomer.getAge()));
				fileWriter.append("\n");

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {

			try {

				fileWriter.flush();

				fileWriter.close();

				logger.info("This file was closed successful!\n");

			} catch (Exception ex) {

				logger.error(ex.getMessage());
			}
		}

		return randomCustomer;
	}

	public static LinkedList<Instruments> writeCSVInstruments(String path, LinkedList<Instruments> list) {

		LinkedList<Instruments> randomInstruments = new LinkedList<Instruments>();

		logger.info("Starting write user.csv file: " + path);

		randomInstruments = list;

		FileWriter fileWriter = null;

		try {

			fileWriter = new FileWriter(path);

			for (Instruments randInst : randomInstruments) {

				fileWriter.append(String.valueOf(randInst.getSymbol()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randInst.getDescription()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randInst.getNominalValueOf1Lot()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randInst.getPipValue()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randInst.getMinTradeVolume()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randInst.getMaxTradeVolume()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randInst.getMarginRate()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(randInst.getTradingHours()));
				fileWriter.append("\n");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {

			try {

				fileWriter.flush();

				fileWriter.close();

				logger.info("This file was closed successful!\n");

			} catch (Exception ex) {

				logger.error(ex.getMessage());
			}
		}

		return randomInstruments;
	}

	// Generate a random array of numbers

	public static LinkedList<Transaction> generateRandomTransaction() {

		logger.info("Start to generate a random array of numbers!");

		LinkedList<Transaction> transactionQueue = new LinkedList<Transaction>();

		Random rand = new Random();

		for (int j = 0; j < 2000; j++) {

			Transaction trans = new Transaction();

			trans.setId(++i);

			trans.setAsk(Configuration.getMin_Ask()
					+ (double) (Math.random() * ((Configuration.getMax_Ask() - Configuration.getMin_Ask()) + 1)));

			trans.setBid(Configuration.getMin_Bid()
					+ (double) (Math.random() * ((Configuration.getMax_Bid() - Configuration.getMin_Bid()) + 1)));

			trans.setQty(rand.nextInt(200));

			transactionQueue.add(trans);

		}
		return transactionQueue;
	}

	public static LinkedList<Customers> generateRandomCustomers() {

		logger.info("Start to generate a random array of numbers!");

		LinkedList<Customers> customersQueue = new LinkedList<Customers>();

		Random rand = new Random();

		for (int j = 0; j < 100; j++) {

			Customers cust = new Customers();

			cust.setID(++n);

			cust.setAddress("Street" + n);

			cust.setAge(18 + (int) (Math.random() * ((64 - 18) + 1)));

			cust.setName("Name" + (n));

			cust.setPhone(223443 + (n));

			cust.setSex('M');

			customersQueue.add(cust);

		}
		return customersQueue;
	}

	public static LinkedList<Instruments> generateRandomInstruments() {

		logger.info("Start to generate a random array of numbers!");

		LinkedList<Instruments> instrumentsQueue = new LinkedList<Instruments>();

		Random rand = new Random(47);

		for (int j = 0; j < 10; j++) {

			Instruments instrument = new Instruments();

			instrument.setSymbol(++k);

			instrument.setDescription("Some description" + (k));

			instrument.setMarginRate(rand.nextInt(1));

			instrument.setMinTradeVolume(rand.nextDouble());

			instrument.setMaxTradeVolume(rand.nextDouble());

			instrument.setNominalValueOf1Lot(k);

			instrument.setPipValue(rand.nextDouble());

			instrument.setTradingHours(24);

			instrumentsQueue.add(instrument);

		}
		return instrumentsQueue;
	}

	public static void shutDownTheExecutor(ExecutorService executorService, String typeOfExecutor) throws Exception {

		try {

			logger.info("Gracefully shutting down " + typeOfExecutor + "!");

			executorService.shutdown();

			if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {

				// cancels currently executing tasks.
				logger.info(typeOfExecutor + " is still alive. Forcing executor thread pool to shut down");

				executorService.shutdownNow();

				// Wait a while for tasks to respond to being cancelled
				if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {

					logger.fatal(typeOfExecutor + " thread pool did not terminate");

					throw new Exception("Unable to shut down " + typeOfExecutor + " thread pool forcefully");

				}

				logger.info(typeOfExecutor + " shut down.");
			}

		} catch (Exception e) {

			logger.error("Exception shutting down " + typeOfExecutor, e);

			throw e;
		}
	}

}
