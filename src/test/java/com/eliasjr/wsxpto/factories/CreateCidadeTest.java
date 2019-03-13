package com.eliasjr.wsxpto.factories;

import com.eliasjr.wsxpto.domain.Cidade;

public class CreateCidadeTest {
	 public static Cidade get(Long id, 
			 				Long ibgeId, 
			 				String uf, 
			 				String name, 
			 				String unnaccentName, 
			 				String alternativeNames, 
			 				String microregion, 
			 				String mesoregion,
			 				boolean isCapital,
			 				Float latitude,
			 				Float longitude){
	        return new Cidade(){{
	            setId(id);
	            setIbgeId(ibgeId);
	            setUf(uf);
	            setName(name);
	            setUnnaccentName(unnaccentName);
	            setAlternativeNames(alternativeNames);
	            setMicroregion(microregion);
	            setMesoregion(mesoregion);
	            setCapital(isCapital);
	            setLatitude(latitude);
	            setLongitude(longitude);
	        }};
	    }
}
