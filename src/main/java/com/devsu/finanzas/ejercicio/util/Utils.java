package com.devsu.finanzas.ejercicio.util;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Utils {

    public String uuid(){
        return UUID.randomUUID().toString();
    }
    
    public String valorMapa(Map<String, String> params,String clave){
        if(params.containsKey(clave)){
           return params.get(clave);
        }else{
            return "";
        }
    }
    
    public boolean validateCriterios(String valor){
        if(valor==null || valor.isEmpty()){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }
    }

    public void printLogService(Object json){
        Gson gson = new Gson();
        log.info(gson.toJson(json));
    }

}
