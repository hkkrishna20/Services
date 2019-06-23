

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
@ComponentScan(basePackages = {"com","springboot"} )
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class CustomerOffersApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerOffersApplication.class, args);
	}
}
