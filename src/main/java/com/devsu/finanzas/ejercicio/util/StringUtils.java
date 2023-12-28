package com.devsu.finanzas.ejercicio.util;

import static com.devsu.finanzas.ejercicio.util.ListUtil.isNotEmpty;

import java.util.Arrays;

public class StringUtils {
	
	public static final boolean isNotBlank(String valor) {
		boolean validar = true;
		validar &= org.apache.commons.lang3.StringUtils.isNotBlank(valor);
		validar &= valor != null && valor.trim() != null && !valor.trim().toUpperCase().contentEquals("NULL");
		return validar;
	}
	
	public static final boolean isBlank(String valor){
		return org.apache.commons.lang3.StringUtils.isBlank(valor) ||
				valor != null && valor.trim() != null && valor.trim().toUpperCase().contentEquals("NULL");
	}
	
	public static final boolean isBlankSome(String... values) {
		if(ListUtil.isEmpty(values)) {
			return true;
		}
		return Arrays.asList(values).stream().anyMatch(value->isBlank(value));
	}
	
	public static final boolean contentSomeEquals(String valor1,String... valor2) {
		if(isNotBlank(valor1) && isNotEmpty(valor2)) {
			return Arrays.asList(valor2).stream().anyMatch( valor -> isNotBlank(valor) && valor.contentEquals(valor1) );
		}
		return false;
	}
	
	public static final boolean noContentSomeEquals(String valor1,String... valor2) {
		return !contentSomeEquals(valor1, valor2);
	}
	
	public static final boolean contentEquals(String valor1,String valor2) {
		if((valor1 == null && valor2 == null) || (isBlank(valor1) && isBlank(valor2))) {
			return true;
		}
		if(valor1 == null || valor2 == null || isBlank(valor1) || isBlank(valor2)) {
			return false;
		}
		if(isNotBlank(valor1) && isNotBlank(valor2)) {
			return valor1.toUpperCase().contentEquals(valor2.toUpperCase());
		}
		return false;
	}
	
	public static final boolean noContentEquals(String valor1,String valor2) {
		return !contentEquals(valor1, valor2);
	}
	
	
	public static final boolean contains(String valor1,String valor2) {
		if((valor1 == null && valor2 == null) || (isBlank(valor1) && isBlank(valor2))) {
			return true;
		}
		if(valor1 == null || valor2 == null || isBlank(valor1) || isBlank(valor2)) {
			return false;
		}
		if(isNotBlank(valor1) && isNotBlank(valor2)) {
			return valor1.toUpperCase().contains(valor2.toUpperCase());
		}
		return false;
	}
	
	public static final String[] toArray(String message) {
		String[] response = {message};
		return response;
	}
}
