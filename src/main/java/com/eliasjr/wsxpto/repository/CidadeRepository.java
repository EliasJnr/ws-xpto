package com.eliasjr.wsxpto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eliasjr.wsxpto.domain.Cidade;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query(value = "select uf, count(*) as cityCount from CIDADE group by uf", nativeQuery = true)
	List<Object> getUFListCountingCities();

	@Query(value = "SELECT count(DISTINCT name) FROM CIDADE", nativeQuery = true)
	int countByColumnName();

	@Query(value = "SELECT count(DISTINCT ibge_id) FROM CIDADE", nativeQuery = true)
	int countByColumnIbgeId();

	@Query(value = "SELECT count(DISTINCT uf) FROM CIDADE", nativeQuery = true)
	int countByColumnUF();

	@Query(value = "SELECT count(DISTINCT capital) FROM CIDADE", nativeQuery = true)
	int countByColumnCapital();

	@Query(value = "SELECT count(DISTINCT lon) FROM CIDADE", nativeQuery = true)
	int countByColumnLon();

	@Query(value = "SELECT count(DISTINCT lat) FROM CIDADE", nativeQuery = true)
	int countByColumnLat();

	@Query(value = "SELECT count(DISTINCT no_accents) FROM CIDADE", nativeQuery = true)
	int countByColumnNoAccent();

	@Query(value = "SELECT count(DISTINCT alternative_names) FROM CIDADE", nativeQuery = true)
	int countByColumnAlternativeNames();

	@Query(value = "SELECT count(DISTINCT microregion) FROM CIDADE", nativeQuery = true)
	int countByColumnMicroRegion();

	@Query(value = "SELECT count(DISTINCT mesoregion) FROM CIDADE", nativeQuery = true)
	int countByColumnMesoRegion();
}
