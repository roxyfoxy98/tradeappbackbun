package ing.stockmarket;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class RunnableCustomerTask implements Runnable {

	static Logger logger = Logger.getLogger(RunnableCustomerTask.class);

	CustomerResourcesLock lock;

	BlockingQueue<Customers> generateCustomers;

	private String threadName;

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public RunnableCustomerTask(String name, CustomerResourcesLock lock, BlockingQueue<Customers> generateCustomers) {

		threadName = name;
		this.lock = lock;
		this.generateCustomers = generateCustomers;

	}

	public void run() {

		logger.info("Inside of duplication CustomerThread!");

		logger.info("The time is: " + new Date());

		int z = CustomerResourcesLock.getCustomersLinkedList().size();

		lock.setSwitchingCustomerFlag(2);

		synchronized (lock) {

			try {

				if (lock.getCustomerFlag().compareAndSet(true, false)) {

					if (z != 0 && lock.getShutDownCustomer().get()) {

						Customers customer = new Customers();

						customer.setID(GenerateCustomerThread.incrementCustomerID());
						customer.setName(CustomerResourcesLock.getCustomersLinkedList().getLast().getName());
						customer.setAge(CustomerResourcesLock.getCustomersLinkedList().getLast().getAge());
						customer.setAddress(CustomerResourcesLock.getCustomersLinkedList().getLast().getAddress());
						customer.setSex(CustomerResourcesLock.getCustomersLinkedList().getLast().getSex());
						customer.setPhone(CustomerResourcesLock.getCustomersLinkedList().getLast().getPhone());

						// Customers customer =
						// CustomerResourcesLock.getCustomersLinkedList().getLast();

						CustomerResourcesLock.getCustomersLinkedList().add(customer);
						try {
							generateCustomers.put(customer);

						} catch (InterruptedException e) {

							logger.error(e.getMessage());
						}

						logger.info("Start writing into file: " + Configuration.getCUSTOMERS_CSVFILE_PATH());

						logger.info("The duplicated transaction is: " + "\t ID:  " + customer.getID() + "\t Name:  "
								+ customer.getName() + "\t Address:  " + customer.getAddress() + "\t Phone:  "
								+ customer.getPhone() + "\t Sex:  " + customer.getSex() + "\t Age:  "
								+ customer.getAge());

					} else {

						logger.info("The execution of the generator has been done!");
					}

				}
			} catch (Exception e) {

				logger.error("Error arised from Customer Duplicator!");

			} finally {

				lock.getCustomerFlag().set(true);

				lock.setSwitchingCustomerFlag(1);

				lock.notifyAll();

			}
		}
	}
}
