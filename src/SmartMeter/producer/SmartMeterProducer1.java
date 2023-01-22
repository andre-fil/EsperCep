package SmartMeter.producer;

import SmartMeter.event.SmartMeterEvent;
import com.espertech.esper.runtime.client.EPRuntime;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SmartMeterProducer1 extends Thread{
        EPRuntime runtime;

    public SmartMeterProducer1(EPRuntime rt) {
        runtime = rt;
    }

    @Override
    public void run() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("/home/barreto/Downloads/CEEW.csv"));
            //reader = Files.newBufferedReader(Paths.get("/home/barreto/Área de Trabalho/CDPO/intellij-esper-examples/src/main/java/SmartMeter/producer/input.csv"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        CsvToBean<CsvSmart> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CsvSmart.class)
                .build();

        List<CsvSmart> rotulos = csvToBean.parse();
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        for (CsvSmart smartmeters : rotulos)
            runtime.getEventService().sendEventBean(new SmartMeterEvent(smartmeters.getX_Timestamp(), smartmeters.getPotencia(), smartmeters.getVoltagem(), smartmeters.getCorrente(), smartmeters.getFrequencia(), smartmeters.getMeter()), "SmartMeterEvent");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    }



            //runtime.getEventService().sendEventBean(new SmartMeterEvent(potencia, corrente + i, frequencia, meter), "SmartMeterEvent");



