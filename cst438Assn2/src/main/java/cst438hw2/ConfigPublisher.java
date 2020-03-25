package cst438hw2;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//ConfigPublisher class
@Configuration
public class ConfigPublisher {
	
	//Method: fanout()
	//@Param: none
	//@Return: FanoutExchange
	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("city-reservation");
	}
}
