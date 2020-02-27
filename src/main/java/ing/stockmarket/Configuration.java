package ing.stockmarket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {

	/* Get the logger for the actual class name to be printed on */

	static Logger logger = Logger.getLogger(Investor.class);

	private Configuration() {
	}

	private static int MIN_ASK;
	private static int MIN_BID;
	private static int MAX_ASK;
	private static int MAX_BID;
	private static int QTY;
	private static String GENERATOR_PATH;
	private static String RECORDS_PATH;
	private static String SCHEDULER_RECORDS_PATH;
	private static String TRANSACTION_CSVFILE_PATH;
	private static String CUSTOMERS_CSVFILE_PATH;
	private static String INSTRUMENTS_CSVFILE_PATH;

	private static long GENERATE_AT_20SECONDS;
	private static long GENERATE_AT_3MINUTES;
	private static long GENERATE_AT_3SECONDS;
	private static long DUPLICATION_AT_EVERY_30SECONDS = 30000;

	private static String TRANSACTION_FILE_PATH;
	private static String CUSTOMER_FILE_PATH;
	private static String INSTRUMENT_FILE_PATH;
	private static String SUBSCRIBER_FILE_PATH;
	private static String TEST_FILE_PATH;

	// Database configuration

	private static String JDBC_DRIVER;
	private static String DATABASE_URL;
	private static String USERNAME;
	private static String PASSWORD;

	private static final String PATH_OF_CONFIGURATION_FILE = "C:\\Users\\MH90UT\\eclipse-workspace\\ProjectPubSub\\src\\main\\resources\\properties.cfg";

	static {
		Configuration.readProperties();
	}

	public static String getTEST_FILE_PATH() {
		return TEST_FILE_PATH;
	}

	public static void setTEST_FILE_PATH(String tEST_FILE_PATH) {
		TEST_FILE_PATH = tEST_FILE_PATH;
	}

	public static String getJdbcDriver() {
		return JDBC_DRIVER;
	}

	public static String getDatabaseUrl() {
		return DATABASE_URL;
	}

	public static String getUsername() {
		return USERNAME;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static String getSUBSCRIBER_FILE_PATH() {
		return SUBSCRIBER_FILE_PATH;
	}

	public static void setSUBSCRIBER_FILE_PATH(String sUBSCRIBER_FILE_PATH) {
		SUBSCRIBER_FILE_PATH = sUBSCRIBER_FILE_PATH;
	}

	public static String getTRANSACTION_FILE_PATH() {
		return TRANSACTION_FILE_PATH;
	}

	public static void setTRANSACTION_FILE_PATH(String tRANSACTION_FILE_PATH) {
		TRANSACTION_FILE_PATH = tRANSACTION_FILE_PATH;
	}

	public static String getCUSTOMER_FILE_PATH() {
		return CUSTOMER_FILE_PATH;
	}

	public static void setCUSTOMER_FILE_PATH(String cUSTOMER_FILE_PATH) {
		CUSTOMER_FILE_PATH = cUSTOMER_FILE_PATH;
	}

	public static String getINSTRUMENT_FILE_PATH() {
		return INSTRUMENT_FILE_PATH;
	}

	public static void setINSTRUMENT_FILE_PATH(String iNSTRUMENT_FILE_PATH) {
		INSTRUMENT_FILE_PATH = iNSTRUMENT_FILE_PATH;
	}

	public static String getTRANSACTION_CSVFILE_PATH() {
		return TRANSACTION_CSVFILE_PATH;
	}

	public static void setTRANSACTION_CSVFILE_PATH(String tRANSACTION_CSVFILE_PATH) {
		TRANSACTION_CSVFILE_PATH = tRANSACTION_CSVFILE_PATH;
	}

	public static String getCUSTOMERS_CSVFILE_PATH() {
		return CUSTOMERS_CSVFILE_PATH;
	}

	public static void setCUSTOMERS_CSVFILE_PATH(String cUSTOMERS_CSVFILE_PATH) {
		CUSTOMERS_CSVFILE_PATH = cUSTOMERS_CSVFILE_PATH;
	}

	public static String getINSTRUMENTS_CSVFILE_PATH() {
		return INSTRUMENTS_CSVFILE_PATH;
	}

	public static void setINSTRUMENTS_CSVFILE_PATH(String iNSTRUMENTS_CSVFILE_PATH) {
		INSTRUMENTS_CSVFILE_PATH = iNSTRUMENTS_CSVFILE_PATH;
	}

	public static long getDUPLICATION_AT_EVERY_30SECONDS() {
		return DUPLICATION_AT_EVERY_30SECONDS;
	}

	public static void setDUPLICATION_AT_EVERY_30SECONDS(long dUPLICATION_AT_EVERY_30SECONDS) {
		DUPLICATION_AT_EVERY_30SECONDS = dUPLICATION_AT_EVERY_30SECONDS;
	}

	public static long getGENERATE_AT_20SECONDS() {
		return GENERATE_AT_20SECONDS;
	}

	public static void setGENERATE_AT_20SECONDS(long gENERATE_AT_20SECONDS) {
		GENERATE_AT_20SECONDS = gENERATE_AT_20SECONDS;
	}

	public static long getGENERATE_AT_3MINUTES() {
		return GENERATE_AT_3MINUTES;
	}

	public static void setGENERATE_AT_3MINUTES(long gENERATE_AT_3MINUTES) {
		GENERATE_AT_3MINUTES = gENERATE_AT_3MINUTES;
	}

	public static long getGENERATE_AT_3SECONDS() {
		return GENERATE_AT_3SECONDS;
	}

	public static void setGENERATE_AT_3SECONDS(long gENERATE_AT_3SECONDS) {
		GENERATE_AT_3SECONDS = gENERATE_AT_3SECONDS;
	}

	public static String getSCHEDULER_RECORDS_PATH() {
		return SCHEDULER_RECORDS_PATH;
	}

	public static void setSCHEDULER_RECORDS_PATH(String sCHEDULER_RECORDS_PATH) {
		SCHEDULER_RECORDS_PATH = sCHEDULER_RECORDS_PATH;
	}

	public static int getMin_Ask() {

		return MIN_ASK;
	}

	public static void setMin_Ask(int newMin_Ask) {

		MIN_ASK = newMin_Ask;
	}

	public static int getMin_Bid() {

		return MIN_BID;
	}

	public static void setMin_Bid(int newMin_Bid) {

		MIN_BID = newMin_Bid;
	}

	public static int getMax_Ask() {

		return MAX_ASK;
	}

	public static void setMax_Ask(int newMax_Ask) {

		MAX_ASK = newMax_Ask;
	}

	public static int getMax_Bid() {

		return MAX_BID;
	}

	public static String getPath() {

		return GENERATOR_PATH;
	}

	public static void setPath(String newPath) {

		GENERATOR_PATH = newPath;
	}

	public static int getQTY() {

		return QTY;
	}

	public static void setQty(int newQty) {

		QTY = newQty;
	}

	public static String getRecordsPath() {

		return RECORDS_PATH;
	}

	public static void setRecordsPath(String newRecordsPath) {

		RECORDS_PATH = newRecordsPath;

	}

	public static void initializeVariables() {

		MIN_ASK = ConstantProperties.MIN_ASK;
		MIN_BID = ConstantProperties.MIN_BID;
		MAX_ASK = ConstantProperties.MAX_ASK;
		MAX_BID = ConstantProperties.MAX_BID;
		QTY = ConstantProperties.QTY;
		GENERATOR_PATH = ConstantProperties.GENERATOR_PATH;
		RECORDS_PATH = ConstantProperties.RECORDS_PATH;
		SCHEDULER_RECORDS_PATH = ConstantProperties.SCHEDULER_RECORDS_PATH;
		GENERATE_AT_20SECONDS = ConstantProperties.GENERATE_AT_20SECONDS;
		GENERATE_AT_3MINUTES = ConstantProperties.GENERATE_AT_3MINUTES;
		GENERATE_AT_3SECONDS = ConstantProperties.GENERATE_AT_3SECONDS;
		DUPLICATION_AT_EVERY_30SECONDS = ConstantProperties.DUPLICATION_AT_EVERY_30SECONDS;
		INSTRUMENTS_CSVFILE_PATH = ConstantProperties.INSTRUMENTS_CSVFILE_PATH;
		CUSTOMERS_CSVFILE_PATH = ConstantProperties.CUSTOMERS_CSVFILE_PATH;
		TRANSACTION_CSVFILE_PATH = ConstantProperties.TRANSACTION_CSVFILE_PATH;
		TRANSACTION_FILE_PATH = ConstantProperties.TRANSACTION_FILE_PATH;
		CUSTOMER_FILE_PATH = ConstantProperties.CUSTOMER_FILE_PATH;
		INSTRUMENT_FILE_PATH = ConstantProperties.INSTRUMENT_FILE_PATH;
		SUBSCRIBER_FILE_PATH = ConstantProperties.SUBSCRIBER_FILE_PATH;
		JDBC_DRIVER = ConstantProperties.JDBC_DRIVER;
		DATABASE_URL = ConstantProperties.DATABASE_URL;
		USERNAME = ConstantProperties.USERNAME;
		PASSWORD = ConstantProperties.PASSWORD;
		TEST_FILE_PATH = ConstantProperties.TEST_FILE_PATH;

	}

	public static void readProperties() {

		Properties prop = new Properties();

		FileInputStream fileInputStream = null;

		try {

			fileInputStream = new FileInputStream(PATH_OF_CONFIGURATION_FILE);

			prop.load(fileInputStream);

			if (prop.getProperty("PATH1") == null) {

				prop.setProperty("PATH1", "C:\\Users\\MH90UT\\GeneratedFile\\generatedfile");
				SCHEDULER_RECORDS_PATH = prop.getProperty("PATH1");

			} else {

				SCHEDULER_RECORDS_PATH = prop.getProperty("PATH1");
			}

			if (prop.getProperty("TEST_FILE_PATH") == null) {

				prop.setProperty("TEST_FILE_PATH", "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Test.csv");
				TEST_FILE_PATH = prop.getProperty("TEST_FILE_PATH");

			} else {

				TEST_FILE_PATH = prop.getProperty("TEST_FILE_PATH");
			}

			if (prop.getProperty("JDBC_DRIVER") == null) {

				prop.setProperty("JDBC_DRIVER", "com.mysql.cj.jdbc.Driver");
				JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");

			} else {

				JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");
			}

			if (prop.getProperty("DATABASE_URL") == null) {

				prop.setProperty("DATABASE_URL", "jdbc:mysql://localhost:3306/TradingPlatform");
				DATABASE_URL = prop.getProperty("DATABASE_URL");

			} else {

				DATABASE_URL = prop.getProperty("DATABASE_URL");
			}

			if (prop.getProperty("USERNAME") == null) {

				prop.setProperty("USERNAME", "root");
				USERNAME = prop.getProperty("USERNAME");

			} else {

				USERNAME = prop.getProperty("USERNAME");
			}

			if (prop.getProperty("PASSWORD") == null) {

				prop.setProperty("PASSWORD", "manolached");
				PASSWORD = prop.getProperty("PASSWORD");

			} else {

				PASSWORD = prop.getProperty("PASSWORD");
			}

			if (prop.getProperty("SUBSCRIBER_FILE_PATH") == null) {

				prop.setProperty("SUBSCRIBER_FILE_PATH", "C:\\Users\\MH90UT\\GeneratedFile\\Subscriber\\Subscriber");
				SUBSCRIBER_FILE_PATH = prop.getProperty("SUBSCRIBER_FILE_PATH");

			} else {

				SUBSCRIBER_FILE_PATH = prop.getProperty("SUBSCRIBER_FILE_PATH");
			}

			if (prop.getProperty("TRANSACTION_FILE_PATH") == null) {

				prop.setProperty("TRANSACTION_FILE_PATH",
						"C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Transactions.csv");
				TRANSACTION_FILE_PATH = prop.getProperty("TRANSACTION_FILE_PATH");

			} else {

				TRANSACTION_FILE_PATH = prop.getProperty("TRANSACTION_FILE_PATH");
			}

			if (prop.getProperty("CUSTOMER_FILE_PATH") == null) {

				prop.setProperty("CUSTOMER_FILE_PATH", "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Customers.csv");
				CUSTOMER_FILE_PATH = prop.getProperty("CUSTOMER_FILE_PATH");

			} else {

				CUSTOMER_FILE_PATH = prop.getProperty("CUSTOMER_FILE_PATH");
			}

			if (prop.getProperty("INSTRUMENT_FILE_PATH") == null) {

				prop.setProperty("INSTRUMENT_FILE_PATH",
						"C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Instruments.csv");
				INSTRUMENT_FILE_PATH = prop.getProperty("INSTRUMENT_FILE_PATH");

			} else {

				INSTRUMENT_FILE_PATH = prop.getProperty("INSTRUMENT_FILE_PATH");
			}

			if (prop.getProperty("TRANSACTION_CSVFILE_PATH") == null) {

				prop.setProperty("TRANSACTION_CSVFILE_PATH",
						"C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Transactions.csv");
				TRANSACTION_CSVFILE_PATH = prop.getProperty("TRANSACTION_CSVFILE_PATH");

			} else {

				TRANSACTION_CSVFILE_PATH = prop.getProperty("TRANSACTION_CSVFILE_PATH");
			}

			if (prop.getProperty("CUSTOMERS_CSVFILE_PATH;") == null) {

				prop.setProperty("CUSTOMERS_CSVFILE_PATH;",
						"C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Customers.csv");
				CUSTOMERS_CSVFILE_PATH = prop.getProperty("CUSTOMERS_CSVFILE_PATH;");

			} else {

				CUSTOMERS_CSVFILE_PATH = prop.getProperty("CUSTOMERS_CSVFILE_PATH;");
			}

			if (prop.getProperty("INSTRUMENTS_CSVFILE_PATH;") == null) {

				prop.setProperty("INSTRUMENTS_CSVFILE_PATH;",
						"C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Instruments.csv");
				INSTRUMENTS_CSVFILE_PATH = prop.getProperty("INSTRUMENTS_CSVFILE_PATH;");

			} else {

				INSTRUMENTS_CSVFILE_PATH = prop.getProperty("INSTRUMENTS_CSVFILE_PATH;");
			}

			if (prop.getProperty("MIN_ASK") == null) {

				prop.setProperty("MIN_ASK", "1000");
				MIN_ASK = Integer.parseInt(prop.getProperty("MIN_ASK"));

			} else {

				MIN_ASK = Integer.parseInt(prop.getProperty("MIN_ASK"));
			}

			if (prop.getProperty("MIN_BID") == null) {

				prop.setProperty("MIN_BID", "1000");
				MIN_BID = Integer.parseInt(prop.getProperty("MIN_BID"));

			} else {

				MIN_BID = Integer.parseInt(prop.getProperty("MIN_BID"));
			}

			if (prop.getProperty("MAX_ASK") == null) {

				prop.setProperty("MAX_ASK", "10000");
				MAX_ASK = Integer.parseInt(prop.getProperty("MAX_ASK"));

			} else {

				MAX_ASK = Integer.parseInt(prop.getProperty("MAX_ASK"));
			}

			if (prop.getProperty("MAX_BID") == null) {

				prop.setProperty("MAX_BID", "9999");
				MAX_BID = Integer.parseInt(prop.getProperty("MAX_BID"));

			} else {

				MAX_BID = Integer.parseInt(prop.getProperty("MAX_BID"));
			}

			if (prop.getProperty("QTY") == null) {

				prop.setProperty("QTY", "100");
				QTY = Integer.parseInt(prop.getProperty("QTY"));

			} else {

				QTY = Integer.parseInt(prop.getProperty("QTY"));
			}

			if (prop.getProperty("PATH") == null) {

				prop.setProperty("PATH", "C:\\Users\\MH90UT\\GeneratedFile\\file.csv");
				GENERATOR_PATH = prop.getProperty("PATH");

			} else {

				GENERATOR_PATH = prop.getProperty("PATH");
			}

			if (prop.getProperty("RECORDS_PATH") == null) {

				prop.setProperty("RECORDS_PATH", "C:\\Users\\MH90UT\\GeneratedFile\\Records.csv");
				RECORDS_PATH = prop.getProperty("RECORDS_PATH");

			} else {

				RECORDS_PATH = prop.getProperty("RECORDS_PATH");
			}

			if (prop.getProperty("GENERATE_AT_20SECONDS") == null) {

				prop.setProperty("GENERATE_AT_20SECONDS", "20000");
				GENERATE_AT_20SECONDS = Integer.parseInt(prop.getProperty("GENERATE_AT_20SECONDS"));

			} else {

				GENERATE_AT_20SECONDS = Integer.parseInt(prop.getProperty("GENERATE_AT_20SECONDS"));
			}

			if (prop.getProperty("GENERATE_AT_3MINUTES") == null) {

				prop.setProperty("GENERATE_AT_3MINUTES", "300000");
				GENERATE_AT_3MINUTES = Integer.parseInt(prop.getProperty("GENERATE_AT_3MINUTES"));

			} else {

				GENERATE_AT_3MINUTES = Integer.parseInt(prop.getProperty("GENERATE_AT_3MINUTES"));
			}

			if (prop.getProperty("GENERATE_AT_3SECONDS") == null) {

				prop.setProperty("GENERATE_AT_3SECONDS", "3000");
				GENERATE_AT_3SECONDS = Integer.parseInt(prop.getProperty("GENERATE_AT_3SECONDS"));

			} else {

				GENERATE_AT_3SECONDS = Integer.parseInt(prop.getProperty("GENERATE_AT_3SECONDS"));
			}

			if (prop.getProperty("DUPLICATION_AT_EVERY_30SECONDS") == null) {

				prop.setProperty("DUPLICATION_AT_EVERY_30SECONDS", "30");
				DUPLICATION_AT_EVERY_30SECONDS = Integer.parseInt(prop.getProperty("DUPLICATION_AT_EVERY_30SECONDS"));

			} else {

				DUPLICATION_AT_EVERY_30SECONDS = Integer.parseInt(prop.getProperty("DUPLICATION_AT_EVERY_30SECONDS"));
			}

		} catch (FileNotFoundException e) {

			System.out.println("Inside of try /catch block!");

			Configuration.initializeVariables();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();

			Configuration.initializeVariables();
		}

	}
}
