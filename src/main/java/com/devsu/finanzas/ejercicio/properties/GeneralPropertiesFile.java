package com.devsu.finanzas.ejercicio.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
@PropertySource(value = "file:config/general.properties", encoding = "UTF-8")
@PropertySource(value = "file:config/${properties.services}", encoding = "UTF-8")
public class GeneralPropertiesFile {
    
    @Value("${timezone}")
    private String timezone;

    @Value("${msj.general.error}")
    private String msjError;

}
