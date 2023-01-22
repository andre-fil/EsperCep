package SmartMeter.consumer;


import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import com.espertech.esper.runtime.client.UpdateListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SumEventsListener implements UpdateListener {

    private List<Object> matchEvents = Collections.synchronizedList(new LinkedList<Object>());

    int i = 1;
    @Override
    public void update(EventBean[] newData, EventBean[] oldEvents, EPStatement statement, EPRuntime runtime) {
        double soma = (double)newData[0].get("sout");

        //if (matchEvents.size() = )
        System.out.println(String.format ("Soma: %.2f", soma));

        System.out.println(i);
        i = i+1;



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
