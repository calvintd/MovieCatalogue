package com.made.calvintd.favoritemoviecatalogue.converter;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    public static String convertDate(Context context, String inputDate) {
        Locale locale = context.getResources().getConfiguration().locale;
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputDateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
        String outputParsedDate = "";

        try {
            Date parsedDate = inputDateFormat.parse(inputDate);
            outputParsedDate = outputDateFormat.format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputParsedDate;
    }
}
