package cst438hw2.service;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.anyString;

import cst438hw2.domain.*;
 
@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testCityFound() throws Exception {
		// TODO 
		City testCity = new City();
		testCity.setId(1);
		testCity.setName("livermore");
		testCity.setCountryCode("USA");
		testCity.setDistrict("California");
		testCity.setPopulation(1000);
		
		Country testCountry = new Country();
		testCountry.setCode("USA");
		testCountry.setName("United States");
		
		
		
		given(cityRepository.findByName("livermore").get(0)).willReturn(testCity);
		given(countryRepository.findByCode("USA")).willReturn(testCountry);
		given(weatherService.getTempAndTime("livermore")).willReturn(new TempAndTime(50.0, 1000, 200));
		
		CityInfo actual = cityService.getCityInfo("livermore");
		
		//assertThat(actual).isEqualTo(testCity.getId());
		
		
	}
	
	@Test 
	public void  testCityNotFound() {
		// TODO 
	}
	
	@Test 
	public void  testCityMultiple() {
		// TODO 
		
	}

}
