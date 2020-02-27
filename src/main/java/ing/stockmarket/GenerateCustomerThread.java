package ing.stockmarket;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class GenerateCustomerThread implements Runnable {

	static Logger logger = Logger.getLogger(GenerateCustomerThread.class);

	CustomerResourcesLock lock;

	BlockingQueue<Customers> generateCustomers;

	String[] address = new String[] { "3 Westminster St.Anderson, SC 29621", "290 Parker Street Valparaiso, IN 46383",
			"596 South Lookout Rd.Watertown, MA 02472", "9 South Primrose Street Huntersville, NC 28078",
			"7168 Lees Creek Avenue\r\n" + "Ypsilanti, MI 48197", "7147 Maple St.Unit 858", "Roy UT 84067" };

	String[] name = new String[] { "Haley Ellithorpe", "Robert Carr", "Hector Niday", "Eneida Wyllie",
			"Russell Desalvo", "Timothy Ramsden", "Preston Jaycox" };

	char[] sex = new char[] { 'M', 'F' };

	private static int n = 0;

	public GenerateCustomerThread(CustomerResourcesLock lock, BlockingQueue<Customers> generateCustomers) {

		this.lock = lock;

		this.generateCustomers = generateCustomers;

	}

	public static int incrementCustomerID() {

		return ++n;
	}

	@Override
	public void run() {

		logger.info("Start generating a random array of customers!");

		Random rand = new Random();

		synchronized (lock) {
			try {

				for (int j = 0; j < 100; j++) {

					while (lock.getSwitchingCustomerFlag() != 1 && lock.getCustomerFlag().get()) {

						try {
							lock.wait();
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}

					if (j == 99) {
						lock.getShutDownCustomer().set(false);
					}

					Customers customer = new Customers();

					customer.setID(++n);

					customer.setAddress(address[rand.nextInt(address.length)]);

					customer.setAge(18 + (int) (Math.random() * ((64 - 18) + 1)));

					customer.setName(name[rand.nextInt(name.length)]);

					customer.setPhone(223443 + n);

					customer.setSex(sex[rand.nextInt(sex.length)]);

					generateCustomers.put(customer);

					Thread.sleep(15);

				}

			} catch (Exception e) {

				logger.error("Error inside of GenerateCustomersThread.class");
			}
		}

	}

}
