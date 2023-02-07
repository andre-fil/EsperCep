package SmartMeter.producer;

import SmartMeter.event.SmartMeterEvent;
import com.espertech.esper.runtime.client.EPRuntime;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SmartMeterProducer1 extends Thread{
        EPRuntime runtime;

    public SmartMeterProducer1(EPRuntime rt) {
        runtime = rt;
    }

    @Override
    public void run() {
        Reader reader = null;
        try {
           // reader = Files.newBufferedReader(Paths.get("/home/barreto/IdeaProjects/CEEW.csv"));
           reader = Files.newBufferedReader(Paths.get("/home/barreto/IdeaProjects/Esper-cep/src/SmartMeter/producer/input.csv"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        CsvToBean<CsvSmart> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CsvSmart.class)
                .build();

        List<CsvSmart> rotulos = csvToBean.parse();

        for (CsvSmart smartmeters : rotulos) {
            String date;
            String data = smartmeters.getX_Timestamp();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat formato2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dataFormatada;
            //Date d2;
            try {
                dataFormatada = formato.parse(data);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            runtime.getEventService().sendEventBean(new SmartMeterEvent(dataFormatada, smartmeters.getPotencia(), smartmeters.getVoltagem(), smartmeters.getCorrente(), smartmeters.getFrequencia(), smartmeters.getMeter()), "SmartMeterEvent");

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    }



            //runtime.getEventService().sendEventBean(new SmartMeterEvent(potencia, corrente + i, frequencia, meter), "SmartMeterEvent");



