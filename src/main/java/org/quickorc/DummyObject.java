package org.quickorc;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class DummyObject {
	private int tyres;
	private String model;
	private boolean cabrio;
	private Date date;
	private Timestamp timestamp;
	private Double weight;
	private BigDecimal bigDecimal;
	
	public int getTyres() {
		return tyres;
	}
	public void setTyres(int tyres) {
		this.tyres = tyres;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public boolean isCabrio() {
		return cabrio;
	}
	public void setCabrio(boolean cabrio) {
		this.cabrio = cabrio;
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
