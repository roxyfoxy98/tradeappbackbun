package ing.stockmarket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import PublisherSubscriberPattern.Message;
import PublisherSubscriberPattern.OnMessage;

// Subscriber

public class Investor {

	static Logger logger = Logger.getLogger(Investor.class);

	private static int subscriberIDTracker = 0;

	private final int subscriberID;

	private String name;

	public Investor(String name) {

		subscriberID = ++subscriberIDTracker;

		logger.info("Subscriber[" + this.subscriberID + "] ");

		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void copyFileForTransactionChannel(Message message) throws IOException {

		List<File> files = new ArrayList<File>();

		files.add(new File(Configuration.getTRANSACTION_FILE_PATH()));
		files.add(new File(Configuration.getCUSTOMER_FILE_PATH()));
		files.add(new File(Configuration.getINSTRUMENT_FILE_PATH()));

		logger.info(message.message);

		new File(Configuration.getSUBSCRIBER_FILE_PATH() + subscriberID + " - " + name + " - " + message.message)
				.mkdir();

		for (File file : files) {
			Files.copy(
					file.toPath(), (new File(Configuration.getSUBSCRIBER_FILE_PATH() + subscriberID + " - " + name
							+ " - " + message.message + "\\" + file.getName())).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		}
	}

	public void copyFileForTestChannel(Message message) throws IOException {

		List<File> files = new ArrayList<File>();

		files.add(new File("C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Test.csv"));

		logger.info(message.message);

		new File(Configuration.getSUBSCRIBER_FILE_PATH() + subscriberID + " - " + name + " - " + message.message)
				.mkdir();

		for (File file : files) {
			Files.copy(
					file.toPath(), (new File(Configuration.getSUBSCRIBER_FILE_PATH() + subscriberID + " - " + name
							+ " - " + message.message + "\\" + file.getName())).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		}

	}

	// Methods: subscribe, unsubscribe, listener, print

	@OnMessage
	private void onMessage(Message message) throws IOException {

		if (message.message == "Transaction") {
			this.copyFileForTransactionChannel(message);
		} else if (message.message == "Test") {

			this.copyFileForTestChannel(message);
		} else {

			logger.info("None of this channel exists!");
		}

	}
}
