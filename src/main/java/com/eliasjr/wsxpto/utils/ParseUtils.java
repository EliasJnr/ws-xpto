package com.eliasjr.wsxpto.utils;

import com.google.common.collect.ImmutableList;
import com.eliasjr.wsxpto.domain.CsvCidade;
import com.eliasjr.wsxpto.domain.Cidade;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {

	private ParseUtils() {
	}

	public static List<Cidade> parseListCsvCityToListCity(List<CsvCidade> cities) {
		List<Cidade> cityList = new ArrayList<>(ImmutableList.of());
		cities.forEach(city -> {
			Cidade newCity = new Cidade();
			newCity.setName(city.getName());
			newCity.setCapital(city.isCapital());
			newCity.setMicroregion(city.getMicroregion());
			newCity.setAlternativeNames(city.getAlternative_names());
			newCity.setMesoregion(city.getMesoregion());
			newCity.setUf(city.getUf());
			newCity.setIbgeId(city.getIbge_id());
			newCity.setUnnaccentName(city.getNo_accents());
			newCity.setLatitude(city.getLat());
			newCity.setLongitude(city.getLon());
			cityList.add(newCity);
		});
		return cityList;
	}
}
