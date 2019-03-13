package com.eliasjr.wsxpto.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eliasjr.wsxpto.WsXptoApplication;
import com.eliasjr.wsxpto.domain.Cidade;
import com.eliasjr.wsxpto.repository.CidadeRepository;
import com.eliasjr.wsxpto.service.impl.CidadeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { WsXptoApplication.class })
public class CidadeServiceImplTest {

	@Autowired
	CidadeService service;


	@Autowired
	CidadeRepository repository;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void createCidadeSuccess() {
		Cidade city = new Cidade();
		city.setId(null);
		city.setIbgeId(1100049L);
		city.setUf("RO");
		city.setName("Cacoal");
		city.setUnnaccentName("Cacoal");
		city.setAlternativeNames("");
		city.setMicroregion("Cacoal");
		city.setMesoregion("Leste Rondoniense");
		city.setCapital(false);
		city.setLatitude(-11.4338650287F);
		city.setLongitude(-61.4429442118F);

		Cidade result = service.add(city);

		assertEquals(result.getName(), "Cacoal");
		assertEquals(result.getIbgeId(), city.getIbgeId());
		assertEquals(result.getUf(), city.getUf());

		service.removeById(city.getId());
	}

}
