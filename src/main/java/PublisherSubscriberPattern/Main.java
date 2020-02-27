package PublisherSubscriberPattern;

import ing.stockmarket.Investor;

public class Main {

	public static void main(String[] args) throws Exception {

		Investor subscriber1 = new Investor("John");
		Investor subscriber2 = new Investor("Snow");
		Investor subscriber3 = new Investor("John");
		Investor subscriber4 = new Investor("Marian");
		Investor subscriber5 = new Investor("John");
		Investor subscriber6 = new Investor("Marian");

		Message message1 = new Message("Test");
		Message message = new Message("Transaction");

		Event.stockMarketReference.subscribe("Transaction", subscriber1);
		Event.stockMarketReference.subscribe("Transaction", subscriber2);
		Event.stockMarketReference.subscribe("Transaction", subscriber3);
		Event.stockMarketReference.subscribe("Transaction", subscriber4);
		Event.stockMarketReference.subscribe("Test", subscriber5);
		Event.stockMarketReference.subscribe("Test", subscriber6);
		Event.stockMarketReference.subscribe("Transaction", subscriber6);

		Event.stockMarketReference.publish("Transaction", message);
		Event.stockMarketReference.publish("Test", message1);

	}
}
