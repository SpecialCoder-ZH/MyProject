package com.augmentum.oes.converter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.augmentum.oes.util.StringUtil;

public class DateSerializer extends JsonDeserializer<Date>{

    @Override
    public Date deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String content = arg0.getText();
        Date date = null;
        if (!StringUtil.isEmpty(content)) {
            try {
                date = simpleDateFormat.parse(content);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }


}
