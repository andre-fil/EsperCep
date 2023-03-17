package SmartMeter.consumer;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Collections;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import com.espertech.esper.runtime.client.UpdateListener;
import lombok.SneakyThrows;

public class SmartMeterListener implements UpdateListener {

    private List<Object> matchEvents = Collections.synchronizedList(new LinkedList<Object>());
//tensão = 230, frequencia 50

    @SneakyThrows
    @Override
    public void update(EventBean[] newData, EventBean[] oldEvents, EPStatement statement, EPRuntime runtime) {
        int hora = (int) newData[0].get("hora");
        int minuto = (int) newData[0].get("minutos");
        int dia = (int) newData[0].get("dia");
        int mes = (int) newData[0].get("mes");
        int ano = (int) newData[0].get("ano");
        if (hora < 10)
            System.out.println("Timestamp: " + dia + "/" + mes + "/" + ano +" - " +"0"+ hora + ":" + minuto + ":" + 0 +0);
        else
            System.out.println("Timestamp: " + dia + "/" + mes + "/" + ano +" - " + hora + ":" + minuto + ":" + 0 +0);
        double potencias = (double )newData[0].get("potencia");
        System.out.printf(String.format ("Potência: %.2f ", potencias));
        double voltagem = (double )newData[0].get("voltagem");
        System.out.printf(String.format ("Voltagem: %.2f ", voltagem));
        double correntes = (double) newData[0].get("corrente");
        System.out.printf(String.format("Corrente: %.2f ", correntes));
        double frequencias = (double) newData[0].get("frequencia");
        System.out.printf(String.format("Frequência: %.2f ", frequencias));
        String meters = (String) newData[0].get("meter");
        System.out.println(String.format("Medidor: %s ", meters));
        String location = (String) newData[0].get("location");
        System.out.println(String.format("Localização: %s ", location));

    }


    public int getSize() {
        return matchEvents.size();
    }

    public List getMatchEvents() {
        return matchEvents;
    }

    public void clearMatched() {
        matchEvents.clear();
    }

}
