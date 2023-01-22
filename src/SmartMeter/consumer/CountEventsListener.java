package SmartMeter.consumer;


import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import com.espertech.esper.runtime.client.UpdateListener;

public class CountEventsListener implements UpdateListener {

    private List<Object> matchEvents = Collections.synchronizedList(new LinkedList<Object>());


    @Override
    public void update(EventBean[] newData, EventBean[] oldEvents, EPStatement statement, EPRuntime runtime) {
        long ocorrencia = (long)newData[0].get("cout");
        System.out.println(String.format ("Ocorrencias: %d", ocorrencia));


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
