package ing.stockmarket;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomerResourcesLock {

	private AtomicBoolean customerFlag = new AtomicBoolean(true);

	private AtomicBoolean shutDownCustomer = new AtomicBoolean(true);

	private static LinkedList<Customers> customersLinkedList = new LinkedList<Customers>();

	private volatile int switchingCustomerFlag = 1;

	public int getSwitchingCustomerFlag() {
		return switchingCustomerFlag;
	}

	public void setSwitchingCustomerFlag(int switchingCustomerFlag) {
		this.switchingCustomerFlag = switchingCustomerFlag;
	}

	public AtomicBoolean getCustomerFlag() {
		return customerFlag;
	}

	public void setCustomerFlag(AtomicBoolean customerFlag) {
		this.customerFlag = customerFlag;
	}

	public AtomicBoolean getShutDownCustomer() {
		return shutDownCustomer;
	}

	public void setShutDownCustomer(AtomicBoolean shutDownCustomer) {
		this.shutDownCustomer = shutDownCustomer;
	}

	public static LinkedList<Customers> getCustomersLinkedList() {
		return customersLinkedList;
	}

	public static void setCustomersLinkedList(LinkedList<Customers> customersLinkedList) {
		CustomerResourcesLock.customersLinkedList = customersLinkedList;
	}
}
