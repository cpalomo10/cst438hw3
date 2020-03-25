package cst438hw2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438hw2.domain.*;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
		
		Date d = new Date();
		d.setTime(weatherService.getTempAndTime(cityName).time*1000);
		SimpleDateFormat theTime = new SimpleDateFormat("HH:mm");
		String timeStr = theTime.format(d);
		
		List<City> cityResults = cityRepository.findByName(cityName);
		Country countryResults = countryRepository.findByCode(cityResults.get(0).getCountryCode());
		return new CityInfo(cityResults.get(0).getId(), cityResults.get(0).getName(), cityResults.get(0).getCountryCode(),
				countryResults.getName(), cityResults.get(0).getDistrict(), cityResults.get(0).getPopulation(),
				weatherService.getTempAndTime(cityName).temp, timeStr);
	}
	
	//Long.toString(weatherService.getTempAndTime(cityName).time)
	
    @Autowired
    private RabbitTemplate rabbitTemplate;
	
    @Autowired
    private FanoutExchange fanout;

    public void requestReservation( 
                   String cityName, 
                   String level, 
                   String email) {
		String msg  = "{\"cityName\": \""+ cityName + 
               "\" \"level\": \""+level+
               "\" \"email\": \""+email+"\"}" ;
		System.out.println("Sending message:"+msg);
		rabbitTemplate.convertSendAndReceive(
                fanout.getName(), 
                "",   // routing key none.
                msg);
	}


}
