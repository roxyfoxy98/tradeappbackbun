package ing.stockmarket;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TransactionResourcesLock {

	private AtomicBoolean transactionFlag = new AtomicBoolean(true);

	private AtomicBoolean shutDownTransaction = new AtomicBoolean(true);

	private static LinkedList<Transaction> transactionLinkedList = new LinkedList<Transaction>();

	private volatile int switchingTransactionFlag = 1;

	public int getSwitchingTransactionFlag() {
		return switchingTransactionFlag;
	}

	public void setSwitchingTransactionFlag(int switchingTransactionFlag) {
		this.switchingTransactionFlag = switchingTransactionFlag;
	}

	public AtomicBoolean getTransactionFlag() {
		return transactionFlag;
	}

	public void setTransactionFlag(AtomicBoolean transactionFlag) {
		this.transactionFlag = transactionFlag;
	}

	public AtomicBoolean getShutDownTransaction() {
		return shutDownTransaction;
	}

	public void setShutDownTransaction(AtomicBoolean shutDownTransaction) {
		this.shutDownTransaction = shutDownTransaction;
	}

	public static LinkedList<Transaction> getTransactionLinkedList() {
		return transactionLinkedList;
	}

	public static void setTransactionLinkedList(LinkedList<Transaction> transactionLinkedList) {
		TransactionResourcesLock.transactionLinkedList = transactionLinkedList;
	}

}
