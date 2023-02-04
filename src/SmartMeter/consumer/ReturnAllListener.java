package SmartMeter.consumer;


import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import com.espertech.esper.runtime.client.UpdateListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReturnAllListener  implements UpdateListener {

private List<Object> matchEvents = Collections.synchronizedList(new LinkedList<Object>());


@Override
public void update(EventBean[] newData, EventBean[] oldEvents, EPStatement statement, EPRuntime runtime) {
        //***Descomentar o atributo que se quer enviar
                // double frequencia = (double) newData[0].get("frequencia");
                //System.out.println(String.format("Frequencia: %.2f", frequencia));
                //double potencia = (double) newData[0].get("potencia");
                //System.out.println(String.format("Potência: %.2f", potencia));
                //double voltagem = (double) newData[0].get("voltagem");
                //System.out.println(String.format("Voltagem: %.2f", voltagem));
                double corrente = (double) newData[0].get("corrente");
                System.out.println(String.format("Corrente: %.2f", corrente));
                //String meter = (String) newData[0].get("meter");
                //System.out.println(String.format("Medidor: %.2f", meter));
                //String location = (String) newData[0].get("location");
                //System.out.println(String.format("Localização: %s ", location));
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
