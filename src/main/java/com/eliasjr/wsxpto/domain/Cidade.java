package com.eliasjr.wsxpto.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "CIDADE")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ibge_id", nullable = false, length = 128)
	private Long ibgeId;

	@Column(name = "uf", nullable = false, length = 3)
	private String uf;

	@Column(name = "name", nullable = false, length = 128)
	private String name;

	@Column(name = "no_accents", nullable = false, length = 128)
	private String unnaccentName;

	@Column(name = "alternative_names", length = 256)
	private String alternativeNames;

	@Column(name = "microregion", length = 128)
	private String microregion;

	@Column(name = "mesoregion", length = 128)
	private String mesoregion;

	@Column(name = "capital")
	private boolean isCapital;

	@Column(name = "lat")
	private Float latitude;

	@Column(name = "lon")
	private Float longitude;

	@Column(name = "created_at")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	public Date createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	public Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnnaccentName() {
		return unnaccentName;
	}

	public void setUnnaccentName(String unnaccentName) {
		this.unnaccentName = unnaccentName;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}

	public boolean isCapital() {
		return isCapital;
	}

	public void setCapital(boolean isCapital) {
		this.isCapital = isCapital;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
