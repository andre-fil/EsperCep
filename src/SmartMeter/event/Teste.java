package SmartMeter.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Teste {
    public static void main(String[] args) throws ParseException {
        /*
        String data = "2023-03-16 00:00:00";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat formato2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dataFormatada;
        //Date d2;
        dataFormatada = formato.parse(data);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataFormatada);
     int hora = cal.get();
     */

            String dataString = "2023-03-16 21:15:00";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date data = sdf.parse(dataString);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(data);
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = calendario.get(Calendar.MONTH + 1);
            int ano = calendario.get(Calendar.YEAR);

            System.out.println(hora + ":" + minutos);
            SimpleDateFormat sdfFormatado = new SimpleDateFormat("dd-MM-yyyy");
            String dataFormatada = sdfFormatado.format(calendario.getTime());

            System.out.println(dataFormatada);


    }
}
