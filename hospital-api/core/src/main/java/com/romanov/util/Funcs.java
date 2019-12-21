package com.romanov.util;

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

}
