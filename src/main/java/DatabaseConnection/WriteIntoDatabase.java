package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.apache.log4j.Logger;

import ing.stockmarket.Configuration;
import ing.stockmarket.CustomerResourcesLock;
import ing.stockmarket.Customers;
import ing.stockmarket.InstrumentResourcesLock;
import ing.stockmarket.Instruments;
import ing.stockmarket.Transaction;
import ing.stockmarket.TransactionResourcesLock;

public class WriteIntoDatabase implements Runnable {

	static Logger logger = Logger.getLogger(WriteIntoDatabase.class);

	Connection conn = null;
	Statement stmt = null;
	String sql;

	String sql1;
	String sql2;
	String sql3;
	String sql4;
	String sql5;
	String sql6;
	String sql7;

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try

		{

			// STEP 2: Register JDBC driver
			Class.forName(Configuration.getJdbcDriver()).newInstance();

			// STEP 3: Open a connection
			logger.info("Connecting to TradingPlatform database!");
			conn = DriverManager.getConnection(Configuration.getDatabaseUrl(), Configuration.getUsername(),
					Configuration.getPassword());
			stmt = conn.createStatement();

			logger.info("Delete all records from TradingPlatform database tables!");

			sql3 = "ALTER TABLE Transaction " + "DROP FOREIGN KEY Transaction_ibfk_1,"
					+ " DROP FOREIGN KEY Transaction_ibfk_2;";
			sql4 = "truncate table Transaction;";
			sql5 = "truncate table Instrument";
			sql6 = "truncate table Customer";

			sql7 = "ALTER TABLE Transaction" + "    ADD CONSTRAINT Transaction_ibfk_1"
					+ "        FOREIGN KEY (CUSTOMER_ID) " + "        REFERENCES Customer (CUSTOMER_ID)"
					+ "        ON UPDATE RESTRICT ON DELETE CASCADE," + "    ADD CONSTRAINT Transaction_ibfk_2"
					+ "        FOREIGN KEY (INSTRUMENT_ID) " + "        REFERENCES Instrument (INSTRUMENT_ID) "
					+ "        ON UPDATE RESTRICT ON DELETE CASCADE;";

			stmt.execute(sql3);
			stmt.execute(sql4);
			stmt.execute(sql5);
			stmt.execute(sql6);
			stmt.execute(sql7);

			logger.info("Tables'records was deleted!");

			for (Customers customer : CustomerResourcesLock.getCustomersLinkedList()) {

				// STEP 4: Execute a query
				logger.info("Creating the customer record!");

				sql = "Insert into Customer (CUSTOMER_ID,NAME,AGE,ADDRESS,SEX,PHONE) values (" + customer.getID() + ","
						+ "'" + customer.getName() + "'" + "," + customer.getAge() + "," + "'" + customer.getAddress()
						+ "'" + "," + "'" + customer.getSex() + "'" + "," + customer.getPhone() + ");";
				stmt.execute(sql);
				logger.info("Customer successfully inserted");
			}

			for (Instruments instrument : InstrumentResourcesLock.getInstrumentsLinkedList()) {

				// STEP 4: Execute a query
				logger.info("Creating the instrument record!");

				sql1 = "Insert into Instrument (INSTRUMENT_ID,DESCRIPTION,NOMINAL_VALUE_OF_1_LOT,PIP_VALUE,MIN_TRADE_VOLUME,MAX_TRADE_VOLUME,MARGIN_RATE,TRADING_HOURS) values ("
						+ instrument.getSymbol() + "," + "'" + instrument.getDescription() + "'" + ","
						+ instrument.getNominalValueOf1Lot() + "," + instrument.getPipValue() + ","
						+ instrument.getMinTradeVolume() + "," + instrument.getMaxTradeVolume() + ","
						+ instrument.getMarginRate() + "," + instrument.getTradingHours() + ");";
				stmt.execute(sql1);
				logger.info("Instrument successfully inserted");
			}

			int sizeOfInstrumentsList = InstrumentResourcesLock.getInstrumentsLinkedList().size();
			int sizeOfCustomersList = CustomerResourcesLock.getCustomersLinkedList().size();

			Random rand = new Random(47);

			for (Transaction transaction : TransactionResourcesLock.getTransactionLinkedList()) {

				Instruments instrument = InstrumentResourcesLock.getInstrumentsLinkedList()
						.get(rand.nextInt(sizeOfInstrumentsList));
				Customers customer = CustomerResourcesLock.getCustomersLinkedList()
						.get(rand.nextInt(sizeOfCustomersList));
				// STEP 4: Execute a query
				logger.info("Creating the transaction record!");

				sql2 = "Insert into Transaction (TRANSACTION_ID,ASK,BID,QTY,CUSTOMER_ID,INSTRUMENT_ID) values ("
						+ transaction.getId() + "," + transaction.getAsk() + "," + transaction.getBid() + ","
						+ transaction.getQty() + "," + customer.getID() + "," + instrument.getSymbol() + ");";
				stmt.execute(sql2);

				// stmt = conn.createStatement();
				/*
				 * String sql1; sql1 = "Update Transaction  " + "SET CUSTOMER_ID=" +
				 * instrument.getSymbol() + "," + "INSTRUMENT_ID=" + customer.getID() + ";";
				 * stmt.execute(sql1);
				 */

				logger.info("Transaction successfully inserted");
			}

		} catch (

		SQLException se) {
			// Handle errors for JDBC
			logger.error(se.getMessage());

		} catch (Exception e) {

			// Handle errors for Class.forName
			e.printStackTrace();

			this.closeConnection();
		} finally {
			// finally block used to close resources

			this.closeConnection();

		} // end try
		logger.info("The connection to database was closed!");
	}

	public void closeConnection() {

		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		} // nothing we can do
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {

			logger.error(se.getMessage());
		} // end finally try
	}

}
