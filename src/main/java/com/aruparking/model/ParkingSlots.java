package com.aruparking.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "PARKING_SLOTS")
public class ParkingSlots {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "SLOT_NAME")
	private String slotName;

	@Column(name = "STATUS")
	private int status;

	@Column(name = "CREATED_ON", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(name = "UPDATED_ON")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@ManyToOne
	@JoinColumn(name = "ZONE_ID")
	private ParkingZones parkingZones;

	@OneToMany(mappedBy = "parkingSlots")
	private List<ParkingOrder> parkingOrder;

	public ParkingSlots() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public ParkingZones getParkingZones() {
		return parkingZones;
	}

	public void setParkingZones(ParkingZones parkingZones) {
		this.parkingZones = parkingZones;
	}

	public List<ParkingOrder> getParkingOrder() {
		return parkingOrder;
	}

	public void setParkingOrder(List<ParkingOrder> parkingOrder) {
		this.parkingOrder = parkingOrder;
	}

}
