package Classes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import DB.DataBase;



@Configuration
@ComponentScan
public class AppConfig {
	
	@Bean
	@Scope("singleton")
	public DataBase dataSource() {
		DataBase db = new DataBase();
		return db;
	}

}
