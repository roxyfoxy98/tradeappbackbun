package ing.stockmarket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@Column(name = "TRANSACTION_ID")
	private int ID;

	@Column(name = "ASK", nullable = false)
	private double ASK;

	@Column(name = "BID", nullable = false)
	private double BID;

	@Column(name = "QTY", nullable = false)
	private int QTY;

	@Column(name = "TIME_LIMIT", nullable = false)
	private int TIME_LIMIT;

	@Column(name = "CUSTOMER_ID", nullable = false)
	private int customer_id;

	@Column(name = "INSTRUMENT_ID", nullable = false)
	private int instrument_id;

	public int getId() {
		return ID;
	}

	public void setId(int ID) {
		this.ID = ID;
	}

	public double getAsk() {
		return ASK;
	}

	public void setAsk(double ASK) {
		this.ASK = ASK;
	}

	public double getBid() {
		return BID;
	}

	public void setBid(double BID) {
		this.BID = BID;
	}

	public int getQty() {
		return QTY;
	}

	public void setQty(int newQty) {

		this.QTY = newQty;
	}

	public int getTIME_LIMIT() {
		return TIME_LIMIT;
	}

	public void setTIME_LIMIT(int TIME_LIMIT) {
		this.TIME_LIMIT = TIME_LIMIT;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getInstrument_id() {
		return instrument_id;
	}

	public void setInstrument_id(int instrument_id) {
		this.instrument_id = instrument_id;
	}

}
