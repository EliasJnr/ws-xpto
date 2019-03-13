package com.eliasjr.wsxpto.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliasjr.wsxpto.domain.Cidade;
import com.eliasjr.wsxpto.domain.CsvCidade;
import com.eliasjr.wsxpto.domain.Estado;
import com.eliasjr.wsxpto.exception.InvalidColumnNameException;
import com.eliasjr.wsxpto.repository.CidadeRepository;
import com.eliasjr.wsxpto.service.ICidadeService;
import com.eliasjr.wsxpto.utils.CsvUtils;
import com.eliasjr.wsxpto.utils.ParseUtils;

@Service("cidadeService")
@Transactional
public class CidadeService extends GenericService<Cidade, Long> implements ICidadeService {

	@Autowired
	private CidadeRepository repository;

	@Override
	public List<Cidade> carregarCidadesCsv(InputStream initialStream) throws IOException {
		List<CsvCidade> citiesToSave = CsvUtils.read(CsvCidade.class, initialStream);
		List<Cidade> cities = ParseUtils.parseListCsvCityToListCity(citiesToSave);
		return repository.saveAll(cities);
	}

	@Override
	public List<Cidade> carregarCapitaisPorNome() {
		Cidade city = new Cidade();
		city.setCapital(true);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnorePaths("id");
		Example<Cidade> example = Example.of(city, matcher);
		List<Cidade> all = repository.findAll(example);
		return all.stream().sorted(Comparator.comparing(Cidade::getUnnaccentName)).collect(Collectors.toList());
	}

	@Override
	public List<Estado> retornaEstadoComMaioriaEMenosCidades() {
		List<Estado> states = this.carregaEstadoComQuantidadeCidade();
		Estado mostCitiesState = Collections.max(states, Comparator.comparingInt(Estado::getCityCount));
		Estado lessCitiesState = Collections.min(states, Comparator.comparingInt(Estado::getCityCount));
		List<Estado> mostCitiesStateList = states.stream()
				.filter(state -> state.getCityCount() == mostCitiesState.getCityCount()).collect(Collectors.toList());
		List<Estado> lessCitiesStateList = states.stream()
				.filter(state -> state.getCityCount() == lessCitiesState.getCityCount()).collect(Collectors.toList());
		List<Estado> response = new ArrayList<>(mostCitiesStateList);
		response.addAll(lessCitiesStateList);
		return response;
	}

	@Override
	public List<Estado> carregaEstadoComQuantidadeCidade() {
		List<Object> ufListCountingCities = repository.getUFListCountingCities();
		ArrayList<Estado> states = new ArrayList<>();
		for (Object item : ufListCountingCities) {
			Object[] item1 = (Object[]) item;
			Estado state = new Estado();
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
		return this.getQuantidadeByColumnName(name);
	}

	@Override
	public List<Cidade> carregaDuasCidadesMaisDistantes() {
		// https://developers.google.com/maps/documentation/geocoding/intro
		return this.getAll();
	}
	
	private int getQuantidadeByColumnName(String name) {
		String attribute = CsvUtils.RELATION_CSV_ENTITY.get(name);
		if (attribute == null) {
			throw new InvalidColumnNameException("Coluna inválida: " + name);
		}

		int obj = 0;

		if (name.equalsIgnoreCase("name")) {
			obj = repository.countByColumnName();
		} else if (name.equalsIgnoreCase("ibge_id")) {
			obj = repository.countByColumnIbgeId();
		} else if (name.equalsIgnoreCase("uf")) {
			obj = repository.countByColumnUF();
		} else if (name.equalsIgnoreCase("capital")) {
			obj = repository.countByColumnCapital();
		} else if (name.equalsIgnoreCase("lon")) {
			obj = repository.countByColumnLon();
		} else if (name.equalsIgnoreCase("lat")) {
			obj = repository.countByColumnLat();
		} else if (name.equalsIgnoreCase("no_accents")) {
			obj = repository.countByColumnNoAccent();
		} else if (name.equalsIgnoreCase("alternative_names")) {
			obj = repository.countByColumnAlternativeNames();
		} else if (name.equalsIgnoreCase("microregion")) {
			obj = repository.countByColumnMicroRegion();
		} else if (name.equalsIgnoreCase("mesoregion")) {
			obj = repository.countByColumnMesoRegion();
		}

		return obj;
	}

}
