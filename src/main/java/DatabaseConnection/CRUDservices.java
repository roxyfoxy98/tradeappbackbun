package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import ing.stockmarket.Configuration;
import ing.stockmarket.CustomerResourcesLock;
import ing.stockmarket.Customers;
import ing.stockmarket.InstrumentResourcesLock;
import ing.stockmarket.Instruments;
import ing.stockmarket.Transaction;
import ing.stockmarket.TransactionResourcesLock;

public class CRUDservices implements Runnable {

	static Logger logger = Logger.getLogger(CRUDservices.class);

	EntityManagerFactory entityManagerFact = Persistence.createEntityManagerFactory("TradingPlatformApp");
	EntityManager entityManager = entityManagerFact.createEntityManager();

	public void eraseRecordsFromTables() {

		Connection conn = null;
		Statement stmt = null;

		String sql1;
		String sql2;
		String sql3;
		String sql4;
		String sql5;
		String sql6;
		String sql7;
		String sql8;
		String sql9;
		String sql10;
		String sql11;
		String sql12;
		String sql13;
		String sql14;

		// STEP 2: Register JDBC driver
		try {
			Class.forName(Configuration.getJdbcDriver()).newInstance();

			// STEP 3: Open a connection
			logger.info("Connecting to TradingPlatform database!");
			conn = DriverManager.getConnection(Configuration.getDatabaseUrl(), Configuration.getUsername(),
					Configuration.getPassword());
			stmt = conn.createStatement();

			logger.info("Delete all records from TradingPlatform database tables!");

			sql1 = "ALTER TABLE Transaction " + "DROP FOREIGN KEY Transaction_ibfk_1,"
					+ " DROP FOREIGN KEY Transaction_ibfk_2;";

			sql2 = "ALTER TABLE Accepted " + "DROP FOREIGN KEY Accepted_ibfk_1," + " DROP FOREIGN KEY Accepted_ibfk_2;";

			sql3 = "ALTER TABLE Rejected " + "DROP FOREIGN KEY Rejected_ibfk_1," + " DROP FOREIGN KEY Rejected_ibfk_2;";

			sql4 = "ALTER TABLE Error " + "DROP FOREIGN KEY Error_ibfk_1," + " DROP FOREIGN KEY Error_ibfk_2;";

			sql5 = "truncate table Transaction;";
			sql6 = "truncate table Instrument";
			sql7 = "truncate table Customer";
			sql8 = "truncate table Rejected;";
			sql9 = "truncate table Accepted";
			sql10 = "truncate table Error";

			sql11 = "ALTER TABLE Transaction" + "    ADD CONSTRAINT Transaction_ibfk_1"
					+ "        FOREIGN KEY (CUSTOMER_ID) " + "        REFERENCES Customer (CUSTOMER_ID)"
					+ "        ON UPDATE RESTRICT ON DELETE CASCADE," + "    ADD CONSTRAINT Transaction_ibfk_2"
					+ "        FOREIGN KEY (INSTRUMENT_ID) " + "        REFERENCES Instrument (INSTRUMENT_ID) "
					+ "        ON UPDATE RESTRICT ON DELETE CASCADE;";

			sql12 = "ALTER TABLE Accepted" + "    ADD CONSTRAINT Accepted_ibfk_1" + "        FOREIGN KEY (CUSTOMER_ID) "
					+ "        REFERENCES Customer (CUSTOMER_ID)" + "        ON UPDATE RESTRICT ON DELETE CASCADE,"
					+ "    ADD CONSTRAINT Accepted_ibfk_2" + "        FOREIGN KEY (INSTRUMENT_ID) "
					+ "        REFERENCES Instrument (INSTRUMENT_ID) "
					+ "        ON UPDATE RESTRICT ON DELETE CASCADE;";

			sql13 = "ALTER TABLE Rejected" + "    ADD CONSTRAINT Rejected_ibfk_1" + "        FOREIGN KEY (CUSTOMER_ID) "
					+ "        REFERENCES Customer (CUSTOMER_ID)" + "        ON UPDATE RESTRICT ON DELETE CASCADE,"
					+ "    ADD CONSTRAINT Rejected_ibfk_2" + "        FOREIGN KEY (INSTRUMENT_ID) "
					+ "        REFERENCES Instrument (INSTRUMENT_ID) "
					+ "        ON UPDATE RESTRICT ON DELETE CASCADE;";

			sql14 = "ALTER TABLE Error" + "    ADD CONSTRAINT Error_ibfk_1" + "        FOREIGN KEY (CUSTOMER_ID) "
					+ "        REFERENCES Customer (CUSTOMER_ID)" + "        ON UPDATE RESTRICT ON DELETE CASCADE,"
					+ "    ADD CONSTRAINT Error_ibfk_2" + "        FOREIGN KEY (INSTRUMENT_ID) "
					+ "        REFERENCES Instrument (INSTRUMENT_ID) "
					+ "        ON UPDATE RESTRICT ON DELETE CASCADE;";

			stmt.execute(sql1);
			stmt.execute(sql2);
			stmt.execute(sql3);
			stmt.execute(sql4);
			stmt.execute(sql5);
			stmt.execute(sql6);
			stmt.execute(sql7);
			stmt.execute(sql8);
			stmt.execute(sql9);
			stmt.execute(sql10);
			stmt.execute(sql11);
			stmt.execute(sql12);
			stmt.execute(sql13);
			stmt.execute(sql14);

			logger.info("Tables'records was deleted!");

		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

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

	public void filterForRecords() {

		Connection conn = null;
		Statement stmt = null;
		String sql1;
		String sql2;
		String sql3;

		// STEP 2: Register JDBC driver
		try {
			Class.forName(Configuration.getJdbcDriver()).newInstance();

			// STEP 3: Open a connection
			logger.info("Connecting to TradingPlatform database!");
			conn = DriverManager.getConnection(Configuration.getDatabaseUrl(), Configuration.getUsername(),
					Configuration.getPassword());
			stmt = conn.createStatement();

			logger.info("Filter records from database!");

			sql1 = "insert into Rejected (TRANSACTION_ID, ASK ,BID ,QTY,TIME_LIMIT,CUSTOMER_ID,INSTRUMENT_ID) select TRANSACTION_ID, ASK ,BID ,QTY,TIME_LIMIT,CUSTOMER_ID,INSTRUMENT_ID from Transaction WHERE TIME_LIMIT>10;";
			sql2 = "insert into Accepted(TRANSACTION_ID, ASK ,BID ,QTY,TIME_LIMIT,CUSTOMER_ID,INSTRUMENT_ID) select TRANSACTION_ID, ASK ,BID ,QTY,TIME_LIMIT,CUSTOMER_ID,INSTRUMENT_ID from Transaction WHERE TIME_LIMIT<=10;";
			sql3 = "insert into Error(TRANSACTION_ID,ASK ,BID ,QTY,TIME_LIMIT,CUSTOMER_ID,INSTRUMENT_ID) select TRANSACTION_ID, ASK ,BID ,QTY,TIME_LIMIT,CUSTOMER_ID,INSTRUMENT_ID from Transaction WHERE BID=ASK;";

			stmt.execute(sql1);
			stmt.execute(sql2);
			stmt.execute(sql3);

			logger.info("Tables'records has been modified!");

		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

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

	Random rand = new Random();

	public void createTransaction(Transaction trans) {

		entityManager.getTransaction().begin();
		trans.setInstrument_id(rand.nextInt(9) + 1);
		trans.setCustomer_id(rand.nextInt(99) + 1);
		entityManager.persist(trans);

		entityManager.getTransaction().commit();
	}

	public void createCustomer(Customers cust) {

		entityManager.getTransaction().begin();

		entityManager.persist(cust);

		entityManager.getTransaction().commit();

	}

	public void createInstrument(Instruments inst) {

		entityManager.getTransaction().begin();

		entityManager.persist(inst);

		entityManager.getTransaction().commit();

	}

	@Override
	public void run() {

		this.eraseRecordsFromTables();

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entityManager.getTransaction().begin();

		for (Customers customer : CustomerResourcesLock.getCustomersLinkedList()) {

			entityManager.persist(customer);

		}

		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		for (Instruments instrument : InstrumentResourcesLock.getInstrumentsLinkedList()) {

			// STEP 4: Execute a query
			logger.info("Creating the instrument record!");

			entityManager.persist(instrument);

			logger.info("Instrument successfully inserted");
		}

		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		for (Transaction transaction : TransactionResourcesLock.getTransactionLinkedList()) {

			// STEP 4: Execute a query
			logger.info("Creating the transaction record!");
			transaction.setInstrument_id(rand.nextInt(9) + 1);
			transaction.setCustomer_id(rand.nextInt(99) + 1);
			entityManager.persist(transaction);

			logger.info("Transaction successfully inserted");

		}
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFact.close();

		this.filterForRecords();
	}

}