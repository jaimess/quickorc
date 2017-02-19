package org.quickorc;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class DummyObject2 {
	private int intValue;
	private String string;
	private boolean booleanValue;
	private Date date;
	private Timestamp timestamp;
	private Double doubleValue;
	private BigDecimal bigDecimal;
	
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int tyres) {
		this.intValue = tyres;
	}
	public String getString() {
		return string;
	}
	public void setString(String model) {
		this.string = model;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(Double weight) {
		this.doubleValue = weight;
	}
	public boolean isBooleanValue() {
		return booleanValue;
	}
	public void setBooleanValue(boolean cabrio) {
		this.booleanValue = cabrio;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}
	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}
}
