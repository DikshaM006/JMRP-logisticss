package com.project.Logistic.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate dateOfOrder;
	private String orderStatus;
	private long freightCost;
	private String additionalInfo;
	@ManyToOne
	private Carrier carrier;
	@OneToOne
	private Cargo cargo;
	@OneToOne
	private Loading loading;
	@OneToOne
	private Unloading unloading;
	@ManyToMany
	private List<User> loadingUser;
	@ManyToMany
	private List<User> unloadingUser;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public long getFreightCost() {
		return freightCost;
	}
	public void setFreightCost(long freightCost) {
		this.freightCost = freightCost;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Loading getLoading() {
		return loading;
	}
	public void setLoading(Loading loading) {
		this.loading = loading;
	}
	public Unloading getUnloading() {
		return unloading;
	}
	public void setUnloading(Unloading unloading) {
		this.unloading = unloading;
	}
	public List<User> getLoadingUser() {
		return loadingUser;
	}
	public void setLoadingUser(List<User> loadingUser) {
		this.loadingUser = loadingUser;
	}
	public List<User> getUnloadingUser() {
		return unloadingUser;
	}
	public void setUnloadingUser(List<User> unloadingUser) {
		this.unloadingUser = unloadingUser;
	}
	public Order() {
		
	}
}
