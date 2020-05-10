package com.engine.specs.api.entity.factory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateDeserializer extends StdDeserializer<Date> {

    private static final long serialVersionUID = -1L;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(final JsonParser jsonparser, final DeserializationContext context) throws IOException, 
    																							  	  JsonProcessingException {

    	String date = "";
        
    	while(!jsonparser.isClosed()){
    	    JsonToken jsonToken = jsonparser.nextToken();
    	    
    	    if(JsonToken.START_OBJECT.equals(jsonToken)){
    	        String fieldName = jsonparser.getCurrentName();

    	        jsonToken = jsonparser.nextToken();

    	        if("date_added".equals(fieldName)){
    	            date = jsonparser.getValueAsString();
    	        }
    	    }
    	}
        
        try {
            return formatter.parse(date);
        } catch (final ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
