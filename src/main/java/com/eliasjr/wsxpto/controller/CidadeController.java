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

import com.eliasjr.wsxpto.domain.Cidade;
import com.eliasjr.wsxpto.domain.Estado;
import com.eliasjr.wsxpto.service.impl.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Cidade controller", description = "Controller de cidades")
@RestController
@RequestMapping(value = "/api/cidade")
public class CidadeController {

	private final CidadeService service;

	@Autowired
	public CidadeController(CidadeService service) {
		this.service = service;
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	@ApiOperation("Retorna todas as cidades.")
	public List<Cidade> getAllCities() {
		return service.getAll();
	}

	@ApiOperation(value = "Criar nova cidade")
	@RequestMapping(method = RequestMethod.POST)
	public Cidade create(@ApiParam(value = "Cidade", required = true) @RequestBody Cidade cidade) {
		return (service.add(cidade));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Cidade por Id")
	public Cidade get(@ApiParam(value = "Id", required = true, example = "0", defaultValue = "0") @PathVariable Long id) {
		return (service.get(id));
	}

	@ApiOperation(value = "Atualizar cidade")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Cidade update(@ApiParam(value = "City", required = true) @RequestBody @Valid Cidade cidade,
			@ApiParam(value = "Id", required = true, example = "0", defaultValue = "0") @PathVariable() Long id) {
		return (service.update(id, cidade));
	}

	@ApiOperation(value = "Excluir cidade por ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(
			@ApiParam(value = "Id", required = true, example = "0", defaultValue = "0") @PathVariable Long id) {
		service.removeById(id);
	}

	@ApiOperation(value = "Importação csv")
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "text/csv")
	public List<Cidade> uploadSimple(@ApiParam(value = "Csv input stream", required = true) @RequestBody InputStream body)
			throws IOException {
		return service.carregarCidadesCsv(body);
	}

	@ApiOperation(value = "Importar csv do formulário html")
	@RequestMapping(value = "/upload-form", method = RequestMethod.POST, consumes = "multipart/form-data")
	public List<Cidade> uploadMultipart(
			@ApiParam(value = "Form data", required = true) @RequestParam("file") MultipartFile file)
			throws IOException {
		return service.carregarCidadesCsv(file.getInputStream());
	}

	@ApiOperation(value = "Retorna capitais")
	@RequestMapping(value = "/capitais", method = RequestMethod.GET)
	public List<Cidade> getCapitals() {
		return service.carregarCapitaisPorNome();
	}

	@ApiOperation(value = "Retorna dois estados, um com mais cidades e o outro com menos cidades.")
	@RequestMapping(value = "/most-less-state", method = RequestMethod.GET)
	public List<Estado> getStateWithMostAndLessCities() {
		return service.retornaEstadoComMaioriaEMenosCidades();
	}

	@ApiOperation(value = "Retorna todos os estados")
	@RequestMapping(value = "/estados", method = RequestMethod.GET)
	public List<Estado> getStateWithCityCount() {
		return service.carregaEstadoComQuantidadeCidade();
	}

	@RequestMapping(value = "/ibge/{ibgeId}", method = RequestMethod.GET)
	@ApiOperation(value = "Cidade por ibgeId")
	public Cidade getByIbgeId(
			@ApiParam(value = "ibgeId", required = true, example = "0", defaultValue = "0") @PathVariable Long ibgeId) {
		Cidade city = new Cidade();
		city.setIbgeId(ibgeId);
		return service.getCityByIbgeId(Example.of(city));
	}

	@RequestMapping(value = "/uf/{uf}", method = RequestMethod.GET)
	@ApiOperation(value = "Cidades por UF")
	public List<Cidade> getByUF(@ApiParam(value = "uf", required = true, example = "AM") @PathVariable String uf) {
		Cidade city = new Cidade();
		city.setUf(uf);
		return service.getAllByUF(Example.of(city));
	}

	@ApiOperation(value = "Pesquisar")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Cidade> search(@ApiParam(value = "City", required = true) Cidade city) {
		return (service.search(Example.of(city)));
	}

	@RequestMapping(value = "/uf/{uf}/names", method = RequestMethod.GET)
	@ApiOperation(value = "Nomes de cidades por UF")
	public List<String> getNamesByUF(@ApiParam(value = "uf", required = true, example = "AM") @PathVariable String uf) {
		Cidade city = new Cidade();
		city.setUf(uf);
		return service.getCitiesNameByUF(Example.of(city));
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ApiOperation(value = "Número de cidades em db")
	public int countCities() {
		return service.countAllCities();
	}

	@RequestMapping(value = "/count/{column}", method = RequestMethod.GET)
	@ApiOperation(value = "Número de registros de uma determinada coluna")
	public int countByColumnName(
			@ApiParam(value = "coluna", required = true, example = "name") @PathVariable String coluna) {
		return service.countByColumnName(coluna);
	}

	@ApiOperation(value = "Retorna as duas cidades mais distantes no db")
	@RequestMapping(value = "/maisDistante", method = RequestMethod.GET)
	public List<Cidade> getTheTwoFarthestCities() {
		return service.getTheTwoFarthestCities();
	}

}
