package cucumber.utils;

import io.cucumber.cucumberexpressions.Transformer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Transformer<Date> {

    @Override
    public Date transform(String arg) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(arg);
        } catch (ParseException ex) {
            return null;
        }
    }
}
