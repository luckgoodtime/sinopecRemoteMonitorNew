package com.lng.model.biz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


/**
 *从一个地址到另外一个地址的线路，包括距离，关键经过点
 *
 *从一个地址到另外一个地址可能不止一条线路
 * 
 */


@Entity
public class Route {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	private Integer id; // 主键自增
	
	private Integer addressId1;	
	
	private Integer addressId2;	
	
	private Integer distance;	
	
	private String routeMarking;//路线描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAddressId1() {
		return addressId1;
	}

	public void setAddressId1(Integer addressId1) {
		this.addressId1 = addressId1;
	}

	public Integer getAddressId2() {
		return addressId2;
	}

	public void setAddressId2(Integer addressId2) {
		this.addressId2 = addressId2;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getRouteMarking() {
		return routeMarking;
	}

	public void setRouteMarking(String routeMarking) {
		this.routeMarking = routeMarking;
	}
	
	

}
