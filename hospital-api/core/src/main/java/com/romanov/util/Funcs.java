package com.romanov.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Funcs {

    public static String camelCaseToSnakeCase(String original)
    {
        Matcher m = Pattern.compile("(?<=[a-z])[A-Z]").matcher(original);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "_"+m.group().toLowerCase());
        }
        m.appendTail(sb);

        return sb.toString();
    }

    public static <T> String objectToJson(T object) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        return objectWriter.writeValueAsString(object);
    }

    public static long dateDiff(Date startDate, Date endDate)
    {
        long timeDiff = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

}
