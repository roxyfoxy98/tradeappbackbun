package ing.stockmarket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class WriteCustomersInCSVFile implements Runnable {

	static Logger logger = Logger.getLogger(WriteCustomersInCSVFile.class);

	CustomerResourcesLock lock;

	BlockingQueue<Customers> generateCustomers;

	int counter = 0;

	public WriteCustomersInCSVFile(CustomerResourcesLock lock, BlockingQueue<Customers> generateCustomers) {

		this.lock = lock;
		this.generateCustomers = generateCustomers;

	}

	public void run() {

		logger.info("The date when the data is written in the CSV file is :" + new Date());

		PrintWriter printWriter = null;

		try {

			Thread.sleep(40);
			FileWriter fileWriter = new FileWriter(Configuration.getCUSTOMERS_CSVFILE_PATH(), true);
			printWriter = new PrintWriter(fileWriter);

			while (true) {

				if (lock.getShutDownCustomer().get()) {

					Customers customer = generateCustomers.take();

					CustomerResourcesLock.getCustomersLinkedList().add(customer);

					logger.info("Start writing into file: " + Configuration.getCUSTOMERS_CSVFILE_PATH());
					fileWriter.append(String.valueOf(customer.getID()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(customer.getName()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(customer.getAddress()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(customer.getPhone()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(customer.getSex()));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(customer.getAge()));
					fileWriter.append("\n");

					++counter;

				} else {

					try {

						printWriter.flush();

						printWriter.close();

						logger.info("This file was closed successful!\n");

						lock.getShutDownCustomer().set(true);

						return;

					} catch (Exception ex) {

						logger.info("Forcefully shutdown \"Customer.csv\" ");
						logger.error(ex.getMessage());
					}

				}

			}

		} catch (InterruptedException | IOException e) {

			logger.error(e.getMessage());

			printWriter.flush();

			printWriter.close();

			logger.info("This file was closed successful!\n");

			lock.getShutDownCustomer().set(true);

		}
	}

}