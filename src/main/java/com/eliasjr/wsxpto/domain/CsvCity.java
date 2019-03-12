package com.eliasjr.wsxpto.domain;

public class CsvCity {

	private Long ibge_id;
	private String uf;
	private String name;
	private String no_accents;
	private String alternative_names;
	private String microregion;
	private String mesoregion;
	private boolean capital;
	private Float lat;
	private Float lon;

	public Long getIbge_id() {
		return ibge_id;
	}

	public void setIbge_id(Long ibge_id) {
		this.ibge_id = ibge_id;
	}

	public String getAlternative_names() {
		return alternative_names;
	}

	public void setAlternative_names(String alternative_names) {
		this.alternative_names = alternative_names;
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

	public String getNo_accents() {
		return no_accents;
	}

	public void setNo_accents(String no_accents) {
		this.no_accents = no_accents;
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
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "CsvCity [ibge_id=" + ibge_id + ", uf=" + uf + ", name=" + name + ", no_accents=" + no_accents
				+ ", alternative_names=" + alternative_names + ", microregion=" + microregion + ", mesoregion="
				+ mesoregion + ", capital=" + capital + ", lat=" + lat + ", lon=" + lon + "]";
	}

}
