package com.devsu.finanzas.ejercicio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class UtilDate implements ConstantsUtil {

    public String dateToString(Date dia) {
        SimpleDateFormat frm = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return frm.format(dia);
    }

    public Date dateToFormat(Date date, String format) throws ParseException {
        SimpleDateFormat frm = new SimpleDateFormat(format);
        return frm.parse(frm.format(date));
    }

    public Date stringToDate(String dia) throws ParseException {
        SimpleDateFormat frm = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return frm.parse(dia);
    }

    public String formatDateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = null;
        sdf.setLenient(false);

        result = sdf.format(date);

        return result;
    }

    public Date formatStringToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date result = null;
        sdf.setLenient(false);

        try {
            result = sdf.parse(date);
        } catch (ParseException e) {
            result = new Date();
        }

        return result;
    }

}
