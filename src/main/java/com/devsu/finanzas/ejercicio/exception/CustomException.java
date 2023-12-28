package com.devsu.finanzas.ejercicio.exception;

import static com.devsu.finanzas.ejercicio.util.ListUtil.isNotEmpty;
import static com.devsu.finanzas.ejercicio.util.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = -3578909467357662003L;
	private String[] additionsalMessage;

	public CustomException(String error) {
		super(error);
	}

	public CustomException(Throwable error) {
		super(error);
	}
	
	public CustomException(Throwable error,String... additionalMessage) {
		super(error);
		if (isNotEmpty(additionalMessage)) {
			this.additionsalMessage = additionalMessage;
		}
	}

	public CustomException(String error, String... additionalMessage) {
		super(error);
		if (isNotEmpty(additionalMessage)) {
			this.additionsalMessage = additionalMessage;
		}
	}

	public List<String> getAdditionalMessages(){
		if(isNotEmpty(additionsalMessage)) {
			return Arrays.asList(additionsalMessage);
		}
		return new ArrayList<>();
	}

	public CustomException(String error, Exception e) {
		super(error);
		List<String> list = new ArrayList<>();
		if(e instanceof NullPointerException){
			Arrays.asList(e.getStackTrace()).stream().limit(5).map(value -> "NullpointerException::"
					+ value.getFileName() + "." + value.getMethodName() + ":" + value.getLineNumber())
					.forEach(list::add);
		}
		
		if(e.getCause() != null && e.getCause().getCause() != null && isNotBlank(e.getCause().getCause().getMessage())){
			list.add(e.getCause().getCause().getMessage());
		}
		if(e.getCause() != null && isNotBlank(e.getCause().getMessage())){
			list.add(e.getCause().getMessage());
		}
		if(isNotBlank(e.getMessage())){
			list.add(e.getMessage());
		}
		if(isNotEmpty(list)){
			this.additionsalMessage = list.stream().toArray(String[]::new);
		}
	}

}