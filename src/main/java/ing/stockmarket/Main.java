package ing.stockmarket;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;

public class Main {

	static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		logger.info("The date when the generator starts is the following: " + new Date());

		File f = new File(Configuration.getSCHEDULER_RECORDS_PATH() + "111.csv");
		File f1 = new File(Configuration.getSCHEDULER_RECORDS_PATH() + "112.csv");
		File f2 = new File(Configuration.getSCHEDULER_RECORDS_PATH() + "113.csv");

		if (f.exists()) {

			f.delete();
			f1.delete();
			f2.delete();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
