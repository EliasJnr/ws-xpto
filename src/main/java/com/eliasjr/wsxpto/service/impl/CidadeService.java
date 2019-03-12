package com.eliasjr.wsxpto.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.eliasjr.wsxpto.domain.Cidade;
import com.eliasjr.wsxpto.domain.CsvCidade;
import com.eliasjr.wsxpto.domain.State;
import com.eliasjr.wsxpto.repository.CidadeRepository;
import com.eliasjr.wsxpto.service.ICidadeService;
import com.eliasjr.wsxpto.utils.CityUtils;
import com.eliasjr.wsxpto.utils.CsvUtils;
import com.eliasjr.wsxpto.utils.ParseUtils;

@Service
public class CidadeService extends GenericService<Cidade, Long> implements ICidadeService {

	@Autowired
	private CidadeRepository repository;

	@Override
	public List<Cidade> loadCitiesFromCsv(InputStream initialStream) throws IOException {
		List<CsvCidade> citiesToSave = CsvUtils.read(CsvCidade.class, initialStream);
		List<Cidade> cities = ParseUtils.parseListCsvCityToListCity(citiesToSave);
		return repository.saveAll(cities);
	}

	@Override
	public List<Cidade> getAllCapitalsOrderedByName() {
		Cidade city = new Cidade();
		city.setCapital(true);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnorePaths("id");
		Example<Cidade> example = Example.of(city, matcher);
		List<Cidade> all = repository.findAll(example);
		return all.stream().sorted(Comparator.comparing(Cidade::getUnnaccentName)).collect(Collectors.toList());
	}

	@Override
	public List<State> getStateWithMostAndLessCities() {
		List<State> states = this.getStateWithCityCount();
		State mostCitiesState = Collections.max(states, Comparator.comparingInt(State::getCityCount));
		State lessCitiesState = Collections.min(states, Comparator.comparingInt(State::getCityCount));
		List<State> mostCitiesStateList = states.stream()
				.filter(state -> state.getCityCount() == mostCitiesState.getCityCount()).collect(Collectors.toList());
		List<State> lessCitiesStateList = states.stream()
				.filter(state -> state.getCityCount() == lessCitiesState.getCityCount()).collect(Collectors.toList());
		List<State> response = new ArrayList<>(mostCitiesStateList);
		response.addAll(lessCitiesStateList);
		return response;
	}

	@Override
	public List<State> getStateWithCityCount() {
		List<Object> ufListCountingCities = repository.getUFListCountingCities();
		ArrayList<State> states = new ArrayList<>();
		for (Object item : ufListCountingCities) {
			Object[] item1 = (Object[]) item;
			State state = new State();
			state.setUf(String.valueOf(item1[0]));
			state.setCityCount(Integer.parseInt(String.valueOf(item1[1])));
			states.add(state);
		}
		return states;
	}

	@Override
	public Cidade getCityByIbgeId(Example<Cidade> example) {
		List<Cidade> search = this.search(example);
		if (search.size() > 0)
			return search.get(0);
		else
			throw new EntityNotFoundException("Não encontrada.");
	}

	@Override
	public List<Cidade> getAllByUF(Example<Cidade> example) {
		return this.search(example);
	}

	@Override
	public List<Cidade> searchContains(Cidade city) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnorePaths("id");
		return this.search(Example.of(city, matcher));
	}

	@Override
	public List<String> getCitiesNameByUF(Example<Cidade> example) {
		List<Cidade> allByUF = this.getAllByUF(example);
		return allByUF.stream().sorted(Comparator.comparing(Cidade::getUnnaccentName)).map(Cidade::getName)
				.collect(Collectors.toList());
	}

	@Override
	public int countAllCities() {
		return this.getAll().size();
	}

	@Override
	public int countByColumnName(String name) {
		// String attribute = RELATION_CSV_ENTITY.get(name);
		// if (attribute == null) {
		// throw new InvalidColumnNameException("Coluna inválida: " + name);
		// }
		int obj = 0;
		switch (name) {
		case "name":
			obj = repository.countByColumnName();
			break;
		case "ibge_id":
			obj = repository.countByColumnIbgeId();
			break;
		case "uf":
			obj = repository.countByColumnUF();
			break;
		case "capital":
			obj = repository.countByColumnCapital();
			break;
		case "lon":
			obj = repository.countByColumnLon();
			break;
		case "lat":
			obj = repository.countByColumnLat();
			break;
		case "no_accents":
			obj = repository.countByColumnNoAccent();
			break;
		case "alternative_names":
			obj = repository.countByColumnAlternativeNames();
			break;
		case "microregion":
			obj = repository.countByColumnMicroRegion();
			break;
		case "mesoregion":
			obj = repository.countByColumnMesoRegion();
			break;
		default:
			break;
		}
		return obj;
	}

	@Override
	public List<Cidade> getTheTwoFarthestCities() {
		List<Cidade> cities = this.getAll();
		Cidade city1 = new Cidade();
		Cidade city2 = new Cidade();
		double maxDistance = 0;
		if (cities.size() > 1) {
			for (int i = 0; i < cities.size() - 1; i++) {
				for (int j = i + 1; j < cities.size(); j++) {
					float[] cityData1 = new float[2];
					Arrays.fill(cityData1, cities.get(i).getLatitude());
					Arrays.fill(cityData1, cities.get(i).getLongitude());
					float[] cityData2 = new float[2];
					Arrays.fill(cityData2, cities.get(j).getLatitude());
					Arrays.fill(cityData2, cities.get(j).getLongitude());
					double newMaxDistance = Math.max(CityUtils.getDistance(cityData1, cityData2), maxDistance);
					if (newMaxDistance > maxDistance) {
						city1 = cities.get(i);
						city2 = cities.get(j);
						maxDistance = newMaxDistance;
					}
				}
			}
			return Arrays.asList(city1, city2);
		}
		return Collections.emptyList();
	}

}
