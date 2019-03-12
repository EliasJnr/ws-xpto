package com.eliasjr.wsxpto.service;

import com.eliasjr.wsxpto.domain.Estado;
import com.eliasjr.wsxpto.domain.Cidade;
import org.springframework.data.domain.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ICidadeService extends IGenericService<Cidade, Long> {
   
	List<Cidade> carregarCidadesCsv(InputStream initialStream) throws IOException;
    List<Cidade> carregarCapitaisPorNome();
    List<Estado> retornaEstadoComMaioriaEMenosCidades();
    List<Estado> carregaEstadoComQuantidadeCidade();
    Cidade getCityByIbgeId(Example<Cidade> example);
    List<Cidade> getAllByUF(Example<Cidade> example);
    List<Cidade> searchContains(Cidade city);
    List<String> getCitiesNameByUF(Example<Cidade> example);
    int countAllCities();
    int countByColumnName(String name);
    List<Cidade> getTheTwoFarthestCities();
}
