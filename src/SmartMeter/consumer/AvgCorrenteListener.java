package SmartMeter.consumer;


import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import com.espertech.esper.runtime.client.UpdateListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AvgCorrenteListener implements UpdateListener {

    private List<Object> matchEvents = Collections.synchronizedList(new LinkedList<Object>());


    @Override
    public void update(EventBean[] newData, EventBean[] oldEvents, EPStatement statement, EPRuntime runtime) {
        double avgCorrente = (double) newData[0].get("media");
        System.out.println(String.format("Média: %.2f A", avgCorrente));
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
