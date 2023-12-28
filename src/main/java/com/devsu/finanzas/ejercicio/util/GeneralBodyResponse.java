package com.devsu.finanzas.ejercicio.util;

import org.springframework.validation.ObjectError;

import java.util.Collection;

public class GeneralBodyResponse<T> {
    private T data;
    private String message;
    private Collection<ObjectError> errors;
    private Integer code;

    public GeneralBodyResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public GeneralBodyResponse(T data, Integer code) {
        this.data = data;
        this.code = code;
    }

    public GeneralBodyResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public GeneralBodyResponse(T data, String message, Collection<ObjectError> errors) {
        this.data = data;
        this.message = message;
        this.errors = errors;
    }

    public GeneralBodyResponse(T data, String message, Collection<ObjectError> errors, Integer code) {
        this.data = data;
        this.message = message;
        this.errors = errors;
        this.code = code;
    }

    public GeneralBodyResponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ObjectError> errors) {
        this.errors = errors;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
