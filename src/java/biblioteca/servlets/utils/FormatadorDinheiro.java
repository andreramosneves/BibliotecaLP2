package biblioteca.servlets.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatadorDinheiro {
    public String format(BigDecimal valor) {
        DecimalFormat f;
        f = new DecimalFormat("#,###,##0.00");
        return "R$ " + f.format(valor);
    }
}
