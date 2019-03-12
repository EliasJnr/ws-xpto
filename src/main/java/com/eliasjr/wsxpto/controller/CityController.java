package com.eliasjr.wsxpto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eliasjr.wsxpto.domain.City;
import com.eliasjr.wsxpto.domain.State;
import com.eliasjr.wsxpto.service.impl.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "City controller", description = "Controller de cidades")
@RestController
@RequestMapping(value = "/api/city")
public class CityController {

	private final CityService service;

	@Autowired
	public CityController(CityService service) {
		this.service = service;
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	@ApiOperation("Get all cities.")
	public List<City> getAllCities() {
		return service.getAll();
	}

	@ApiOperation(value = "Create new City")
	@RequestMapping(method = RequestMethod.POST)
	public City create(@ApiParam(value = "City", required = true) @RequestBody City city) {
		return (service.add(city));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "City by Id")
	public City get(@ApiParam(value = "Id", required = true, example = "0", defaultValue = "0") @PathVariable Long id) {
		return (service.get(id));
	}

	@ApiOperation(value = "Update City")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public City update(@ApiParam(value = "City", required = true) @RequestBody @Valid City city,
			@ApiParam(value = "Id", required = true, example = "0", defaultValue = "0") @PathVariable() Long id) {
		return (service.update(id, city));
	}

	@ApiOperation(value = "Delete City by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(
			@ApiParam(value = "Id", required = true, example = "0", defaultValue = "0") @PathVariable Long id) {
		service.removeById(id);
	}

	@ApiOperation(value = "Import csv")
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "text/csv")
	public List<City> uploadSimple(@ApiParam(value = "Csv input stream", required = true) @RequestBody InputStream body)
			throws IOException {
		return service.loadCitiesFromCsv(body);
	}

	@ApiOperation(value = "Import csv from html form")
	@RequestMapping(value = "/upload-form", method = RequestMethod.POST, consumes = "multipart/form-data")
	public List<City> uploadMultipart(
			@ApiParam(value = "Form data", required = true) @RequestParam("file") MultipartFile file)
			throws IOException {
		return service.loadCitiesFromCsv(file.getInputStream());
	}

	@ApiOperation(value = "Get capitals")
	@RequestMapping(value = "/capitals", method = RequestMethod.GET)
	public List<City> getCapitals() {
		return service.getAllCapitalsOrderedByName();
	}

	@ApiOperation(value = "Get two states, one with the most cities and the one with the least cities.")
	@RequestMapping(value = "/most-less-state", method = RequestMethod.GET)
	public List<State> getStateWithMostAndLessCities() {
		return service.getStateWithMostAndLessCities();
	}

	@ApiOperation(value = "Get all states")
	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public List<State> getStateWithCityCount() {
		return service.getStateWithCityCount();
	}

	@RequestMapping(value = "/ibge/{ibgeId}", method = RequestMethod.GET)
	@ApiOperation(value = "City by ibgeId")
	public City getByIbgeId(
			@ApiParam(value = "ibgeId", required = true, example = "0", defaultValue = "0") @PathVariable Long ibgeId) {
		City city = new City();
		city.setIbgeId(ibgeId);
		return service.getCityByIbgeId(Example.of(city));
	}

	@RequestMapping(value = "/uf/{uf}", method = RequestMethod.GET)
	@ApiOperation(value = "Cities by UF")
	public List<City> getByUF(@ApiParam(value = "uf", required = true, example = "AM") @PathVariable String uf) {
		City city = new City();
		city.setUf(uf);
		return service.getAllByUF(Example.of(city));
	}

	@ApiOperation(value = "search")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<City> search(@ApiParam(value = "City", required = true) City city) {
		return (service.search(Example.of(city)));
	}

	@RequestMapping(value = "/uf/{uf}/names", method = RequestMethod.GET)
	@ApiOperation(value = "Cities names by UF")
	public List<String> getNamesByUF(@ApiParam(value = "uf", required = true, example = "AM") @PathVariable String uf) {
		City city = new City();
		city.setUf(uf);
		return service.getCitiesNameByUF(Example.of(city));
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ApiOperation(value = "Number of cities in db")
	public int countCities() {
		return service.countAllCities();
	}

	@RequestMapping(value = "/count/{column}", method = RequestMethod.GET)
	@ApiOperation(value = "Number registers of a given column")
	public int countByColumnName(
			@ApiParam(value = "column", required = true, example = "name") @PathVariable String column) {
		return service.countByColumnName(column);
	}

	@ApiOperation(value = "Get the two farthest cities in db")
	@RequestMapping(value = "/farthest", method = RequestMethod.GET)
	public List<City> getTheTwoFarthestCities() {
		return service.getTheTwoFarthestCities();
	}

}
