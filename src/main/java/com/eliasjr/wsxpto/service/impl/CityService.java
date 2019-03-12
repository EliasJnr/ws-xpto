package com.eliasjr.wsxpto.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.eliasjr.wsxpto.domain.City;
import com.eliasjr.wsxpto.domain.State;
import com.eliasjr.wsxpto.service.ICityService;
import com.eliasjr.wsxpto.service.impl.GenericService;
import com.eliasjr.wsxpto.domain.CsvCity;
import com.eliasjr.wsxpto.repository.CityRepository;
import com.eliasjr.wsxpto.utils.CsvUtils;
import com.eliasjr.wsxpto.utils.ParseUtils;

@Service
public class CityService extends GenericService<City, Long> implements ICityService {

	@Autowired
	private CityRepository repository;

	@Override
	public List<City> loadCitiesFromCsv(InputStream initialStream) throws IOException {
		List<CsvCity> citiesToSave = CsvUtils.read(CsvCity.class, initialStream);
		List<City> cities = ParseUtils.parseListCsvCityToListCity(citiesToSave);
		return repository.saveAll(cities);

	}

	@Override
	public List<City> getAllCapitalsOrderedByName() {

		return null;
	}

	@Override
	public List<State> getStateWithMostAndLessCities() {

		return null;
	}

	@Override
	public List<State> getStateWithCityCount() {

		return null;
	}

	@Override
	public City getCityByIbgeId(Example<City> example) {

		return null;
	}

	@Override
	public List<City> getAllByUF(Example<City> example) {

		return null;
	}

	@Override
	public List<City> searchContains(City city) {

		return null;
	}

	@Override
	public List<String> getCitiesNameByUF(Example<City> example) {

		return null;
	}

	@Override
	public int countAllCities() {

		return 0;
	}

	@Override
	public int countByColumnName(String name) {

		return 0;
	}

	@Override
	public List<City> getTheTwoFarthestCities() {

		return null;
	}

}
