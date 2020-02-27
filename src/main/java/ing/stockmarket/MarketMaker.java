package ing.stockmarket;

import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;

// Publisher

public class MarketMaker {

	static Logger logger = Logger.getLogger(MarketMaker.class);
	// Fields
	
	private int quantity; 
	private TypeOfReading typeOfReading;
	
	//	Constructor with arguments 
	
	public MarketMaker(int quantity,TypeOfReading typeOfReading) {

		this.quantity=quantity;
		this.typeOfReading=typeOfReading;
	}
	
	// Getters and Setters
	
	public int getQuantity() {
		
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		
		this.quantity=quantity;
	}
	

	public TypeOfReading getTypeOfReading() {
		return typeOfReading;
	}

	public void setTypeOfReading(TypeOfReading typeOfReading) {
		this.typeOfReading = typeOfReading;
	}

	// Call the generator 

	public Queue<Transaction> generateTransaction(){
		
		logger.info("Starting to generate the transactions");
		
		Utils.writeCSVTransaction(Configuration.getPath(),Utils.generateRandomTransaction());
		
		return this.readTransaction(Configuration.getPath());
		
	}
	
	public Queue<Transaction> readTransaction(String filePath){
		
		logger.info("Start reading from CSV file!");
		return Utils.readCsv(filePath, quantity,typeOfReading);
	}
	

}
