package com.devsu.finanzas.ejercicio;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.devsu.finanzas.ejercicio.properties.GeneralPropertiesFile;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EntityScan(basePackages = {"com.devsu.finanzas.ejercicio.model"})
public class EjercicioFinanzasDevsuApplication {

	@Autowired
	private GeneralPropertiesFile gPropertiesFile;

	public static void main(String[] args) {
		SpringApplication.run(EjercicioFinanzasDevsuApplication.class, args);
	}

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone(gPropertiesFile.getTimezone()));
	}

}
