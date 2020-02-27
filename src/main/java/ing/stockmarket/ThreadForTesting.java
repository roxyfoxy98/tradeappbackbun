package ing.stockmarket;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class ThreadForTesting implements Runnable {

	static Logger logger = Logger.getLogger(ThreadForTesting.class);

	@Override
	public void run() {

		logger.info("The date when the data is written in the CSV file is :" + new Date());

		PrintWriter printWriter = null;

		try {

			Thread.sleep(40);

			List<Integer> integerList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

			FileWriter fileWriter = new FileWriter(Configuration.getTEST_FILE_PATH(), true);

			printWriter = new PrintWriter(fileWriter);

			logger.info("Start writing into file: " + Configuration.getTEST_FILE_PATH());

			for (Integer integ : integerList) {

				printWriter.append(String.valueOf(integ));
				printWriter.append("\n");

			}
			printWriter.flush();

			printWriter.close();

			logger.info("This file was closed successful!\n");

		} catch (Exception ex) {

			logger.error("Forcefully shutdown \"Test.csv file\" ");
			logger.error(ex.getMessage());

			try {

				printWriter.flush();

				printWriter.close();

				logger.info("This file was closed successful!\n");

			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}
	}

}
