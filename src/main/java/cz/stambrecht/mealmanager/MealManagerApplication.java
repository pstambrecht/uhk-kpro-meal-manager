package cz.stambrecht.mealmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MealManagerApplication extends SpringBootServletInitializer {
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(MealManagerApplication.class);
	    }
	
	/**
	 * Main method. Starts SpringApplication
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MealManagerApplication.class, args);
	}
}
