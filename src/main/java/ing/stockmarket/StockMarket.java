package ing.stockmarket;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

// PubSub Server

public class StockMarket {

	static Logger logger = Logger.getLogger(StockMarket.class);

	private LinkedList<Transaction> buffer;

	private boolean notifyEnabled;

	private String topic;

	public StockMarket(String topic) {

		notifyEnabled = true;

		this.topic = topic;

	}

	// Getters and Setters

	public String getTopic() {

		return topic;
	}

	public void setTopic(String topic) {

		this.topic = topic;
	}

	public Queue<Transaction> getBufferList() {

		logger.info("Listener from investor!");
		return buffer;
	}

	// Listen

	public void connectToMarket(LinkedList<Transaction> transactionList) {

		if (notifyEnabled) {

			logger.info("Successfully connected to martketPalce!");
			if (transactionList != null) {

				buffer = transactionList;

				Utils.writeCSVTransaction(Configuration.getRecordsPath(), buffer);

			} else {

				logger.warn("Transaction list is empty");
			}
		} else {

			logger.warn("Stock market is closed!");

		}

	}

}
