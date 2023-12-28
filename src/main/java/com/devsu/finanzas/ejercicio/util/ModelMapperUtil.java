package com.devsu.finanzas.ejercicio.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.devsu.finanzas.ejercicio.exception.CustomException;

@Component
public class ModelMapperUtil {

    private final ModelMapper modelMapper;

    public ModelMapperUtil() {
        this.modelMapper = new ModelMapper();
    }

    public <T, U> U convertToDto(T entity, Class<U> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <T, U> T convertToEntity(U dto, Class<T> entityClass) {
        try {
            return modelMapper.map(dto, entityClass);
        } catch (Exception e) {
            throw new CustomException("Error al convertir DTO a entidad", e);
        }
    }
}
