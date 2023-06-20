package com.muhammet.ilkproje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
/**
 * Controller -> MVC kulalnırken yazarız
 * RestController -> Restful API kullanırken yazarız
 */
@RestController
/**
 * http://localhost:8080 -> Spring Boot uygulamasının çalıştığı port
 * http://localhost:8080/api
 */
@RequestMapping("/api")
public class IlkprojeApplication {

	/**
	 * Gelen istğin türü ile doğrul olarak istekelr yakalanır. (GET,POST,PUT, DELETE)
	 * http://localhost:8080/api/merhaba
	 * @return
	 */
	@GetMapping("/merhaba")
	public String merhaba(){
		return "Merhaba Spring Boot";
	}

	/**
	 * http://localhost:8080/api/name
	 * @return
	 */
	@GetMapping("/name")
	public String getName(){
		return "Muhammet";
	}

	/**
	 * http://localhost:8080/api/names
	 * @return
	 */
	@GetMapping("/names")
	public List<String> getNames(){
		return Arrays.asList("Muhammet","Ahmet","Mehmet");
	}

	public static void main(String[] args) {
		SpringApplication.run(IlkprojeApplication.class, args);
	}

}
