package org.quickorc;

import java.util.Date;

public class Car {
	private int tyres;
	private String model;
	private boolean cabrio;
	private Date date;
	private Double weight;
	
	
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
}
