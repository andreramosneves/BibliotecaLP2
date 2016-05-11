package biblioteca.servlets.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FormatadorData {
    public static String format(LocalDate data) {
        SimpleDateFormat formatador;
        formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(Date.valueOf(data));
    }
    
    
}
