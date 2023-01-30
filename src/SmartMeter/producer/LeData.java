package SmartMeter.producer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeData {
    public static void main(String[] args) {

        String dado = "2023-02-07 00:39:00";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataFormatada;

        {
            try {
                dataFormatada = formato.parse(dado);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(dataFormatada);
        System.out.println(dataFormatada.getClass().getSimpleName());

    }

}
