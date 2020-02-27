package ing.stockmarket;

public abstract class ConstantProperties {

	public static final int MIN_ASK = 1000;
	public static final int MIN_BID = 1000;
	public static final int MAX_ASK = 10000;
	public static final int MAX_BID = 9999;
	public static final int QTY = 100;
	public static final String GENERATOR_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\file.csv";
	public static final String RECORDS_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Records.csv";
	public static final String SCHEDULER_RECORDS_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\generatedfile";
	public static final String INSTRUMENTS_CSVFILE_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Instruments.csv";
	public static final String CUSTOMERS_CSVFILE_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Customers.csv";
	public static final String TRANSACTION_CSVFILE_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Transactions.csv";
	public static final String SUBSCRIBER_FILE_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Subscriber\\Subscriber";
	public static final long GENERATE_AT_20SECONDS = 20000;
	public static final long GENERATE_AT_3MINUTES = 300000;
	public static final long GENERATE_AT_3SECONDS = 3000;
	public static final long DUPLICATION_AT_EVERY_30SECONDS = 30;

	public static String TRANSACTION_FILE_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Transactions.csv";
	public static String CUSTOMER_FILE_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Customers.csv";
	public static String INSTRUMENT_FILE_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Instruments.csv";
	public static String TEST_FILE_PATH = "C:\\Users\\MH90UT\\GeneratedFile\\Publisher\\Test.csv";

	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/TradingPlatform";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "manolached";

}
