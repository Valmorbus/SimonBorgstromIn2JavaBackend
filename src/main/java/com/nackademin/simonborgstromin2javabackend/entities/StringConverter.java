/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nackademin.simonborgstromin2javabackend.entities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author borgs_000
 */
@Converter
public class StringConverter implements AttributeConverter<String, Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(String stringDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dtt = null;
        try {
            dtt = (Date) df.parse(stringDate);
        } catch (ParseException ex) {
            Logger.getLogger(StringConverter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error");
        }
        java.sql.Date ds = new java.sql.Date(dtt.getTime());
        return ds;
    }

    @Override
    public String convertToEntityAttribute(Date dateDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = df.format(dateDate);
        return stringDate;
       
    }

}
