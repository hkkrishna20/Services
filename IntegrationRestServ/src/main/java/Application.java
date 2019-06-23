
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = "com")
@SpringBootApplication
@EnableJpaRepositories("com")
@EnableJpaAuditing
@EntityScan("com")
@PropertySource("classpath:application.properties")
public class Application extends SpringBootServletInitializer
// implements CommandLineRunner {
{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

/*	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
		return hemf.getSessionFactory();
	}*/

	/*
	 * @Override public void run(String... args) throws Exception { //
	 * System.out.println("Our DataSource is = " + dataSource); Iterable<com.System>
	 * systemlist = systemRepository.findAll(); for(com.System
	 * systemmodel:systemlist){ System.println("Here is a system: " +
	 * systemmodel.toString()); }
	 */

	// }
}
