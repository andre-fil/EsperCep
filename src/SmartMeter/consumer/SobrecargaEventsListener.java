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

public class SobrecargaEventsListener implements UpdateListener {
    private List<Object> matchEvents = Collections.synchronizedList(new LinkedList<Object>());
//tensão = 230, frequencia 50

    @SneakyThrows
    @Override
    public void update(EventBean[] newData, EventBean[] oldEvents, EPStatement statement, EPRuntime runtime) {
        double media_potencias = (double )newData[0].get("avgpotencia");
        System.out.printf(String.format (" Potência: %.2f ", media_potencias));
        double media_corrente = (double )newData[0].get("avgcorrente");
        System.out.printf(String.format ("Voltagem: %.2f ", media_corrente));

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


