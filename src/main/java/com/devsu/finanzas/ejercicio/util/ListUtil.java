package com.devsu.finanzas.ejercicio.util;

import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class ListUtil {
	
	public static <O> boolean isEmpty(List<O> list) {
		return list == null || list.isEmpty() || list.size() == 0;
	}
	
	public static <O> boolean isEmpty(O... params) {
		return params == null || params.length == 0;
	}
	
	public static <O> boolean isNotEmpty(List<O> list) {
		return list != null && !list.isEmpty() && list.size() > 0;
	}
	
	public static <O> boolean isNotEmpty(O... params) {
		return params != null && params.length > 0;
	}
	
	public static <O> Integer size(List<O> list) {
		return list != null ? list.size() : 0;
	}
	
	public static <O> Integer size(O... params) {
		return params != null ? params.length : 0;
	}
	
	public static <T,V>boolean isNotEmpty(T obj, Function<T,V> method) {
		if(obj == null) {
			return false;
		}
		V value = method.apply(obj);
		return isNotEmpty(value);
	}
	
}
