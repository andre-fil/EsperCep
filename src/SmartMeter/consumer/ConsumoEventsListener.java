package SmartMeter.consumer;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import com.espertech.esper.runtime.client.UpdateListener;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ConsumoEventsListener implements UpdateListener {

    private List<Object> matchEvents = Collections.synchronizedList(new LinkedList<Object>());
//tensão = 230, frequencia 50

    @SneakyThrows
    @Override
    public void update(EventBean[] newData, EventBean[] oldEvents, EPStatement statement, EPRuntime runtime) {
    double consumo = (double) newData[0].get("consumo");
    System.out.print(String.format("Consumo (kw): %.2f ", consumo));
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
