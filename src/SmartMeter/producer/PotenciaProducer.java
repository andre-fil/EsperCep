package SmartMeter.producer;

import SmartMeter.event.PotenciaEvent;
import com.espertech.esper.runtime.client.EPRuntime;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PotenciaProducer extends Thread{
    EPRuntime runtime;

    public PotenciaProducer(EPRuntime rt) {
        runtime = rt;
    }



    @Override
    public void run() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("/home/barreto/IdeaProjects/CEEW.csv"));
            //reader = Files.newBufferedReader(Paths.get("/home/barreto/IdeaProjects/Esper-cep/src/SmartMeter/producer/input.csv"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        CsvToBean<CsvSmart> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CsvSmart.class)
                .build();

        List<CsvSmart> rotulos = csvToBean.parse();

        for (CsvSmart smartmeters : rotulos) {
            runtime.getEventService().sendEventBean(new PotenciaEvent(smartmeters.getPotencia(), smartmeters.getMeter()), "PotenciaEvent");

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



