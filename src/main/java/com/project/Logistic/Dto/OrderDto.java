package com.project.Logistic.Dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
	private int id;
	private LocalDate dateOfOrder;
	private String orderStatus;
	private long freightCost;
	private String additionalInfo;

	private int carrierId;

	private int cargoId;

	private int loadingId;

	private int unloadingId;

	private int loadingUserId;

	private int unloadingUserId;

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

	public int getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

	public int getCargoId() {
		return cargoId;
	}

	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}

	public int getLoadingId() {
		return loadingId;
	}

	public void setLoadingId(int loadingId) {
		this.loadingId = loadingId;
	}

	public int getUnloadingId() {
		return unloadingId;
	}

	public void setUnloadingId(int unloadingId) {
		this.unloadingId = unloadingId;
	}

	public int getLoadingUserId() {
		return loadingUserId;
	}

	public void setLoadingUserId(int loadingUserId) {
		this.loadingUserId = loadingUserId;
	}

	public int getUnloadingUserId() {
		return unloadingUserId;
	}

	public void setUnloadingUserId(int unloadingUserId) {
		this.unloadingUserId = unloadingUserId;
	}

	
}
