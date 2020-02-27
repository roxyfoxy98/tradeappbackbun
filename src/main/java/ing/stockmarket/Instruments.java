package ing.stockmarket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Instrument")
public class Instruments {

	@Id
	@Column(name = "INSTRUMENT_ID")
	private int symbol;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "NOMINAL_VALUE_OF_1_LOT", nullable = false)
	private int nominalValueOf1Lot;

	@Column(name = "PIP_VALUE", nullable = false)
	private double pipValue;

	@Column(name = "MIN_TRADE_VOLUME", nullable = false)
	private double minTradeVolume;

	@Column(name = "MAX_TRADE_VOLUME", nullable = false)
	private double maxTradeVolume;

	@Column(name = "MARGIN_RATE", nullable = false)
	private double marginRate;

	@Column(name = "TRADING_HOURS", nullable = false)
	private float tradingHours;

	public int getSymbol() {
		return symbol;
	}

	public void setSymbol(int symbol) {

		this.symbol = symbol;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;

	}

	public int getNominalValueOf1Lot() {

		return nominalValueOf1Lot;
	}

	public void setNominalValueOf1Lot(int nominalValueOf1Lot) {

		this.nominalValueOf1Lot = nominalValueOf1Lot;

	}

	public double getPipValue() {

		return pipValue;

	}

	public void setPipValue(double pipValue) {

		this.pipValue = pipValue;

	}

	public double getMinTradeVolume() {

		return minTradeVolume;

	}

	public void setMinTradeVolume(double minTradeVolume) {

		this.minTradeVolume = minTradeVolume;
	}

	public double getMaxTradeVolume() {

		return maxTradeVolume;
	}

	public void setMaxTradeVolume(double maxTradeVolume) {

		this.maxTradeVolume = maxTradeVolume;
	}

	public double getMarginRate() {

		return marginRate;
	}

	public void setMarginRate(double marginRate) {

		this.marginRate = marginRate;

	}

	public float getTradingHours() {

		return tradingHours;
	}

	public void setTradingHours(float tradingHours) {

		this.tradingHours = tradingHours;
	}

}
