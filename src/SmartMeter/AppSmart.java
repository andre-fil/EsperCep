package SmartMeter;

import SmartMeter.consumer.*;
import SmartMeter.event.SmartMeterEvent;
import SmartMeter.event.PotenciaEvent;
import SmartMeter.producer.PotenciaProducer;
import SmartMeter.producer.SmartMeterProducer1;
import SmartMeter.util.EPLQueries;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompileException;
import com.espertech.esper.compiler.client.EPCompiler;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.EPDeployException;
import com.espertech.esper.runtime.client.EPDeployment;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPRuntimeProvider;
import com.espertech.esper.runtime.client.EPStatement;


public class AppSmart {

    public static void main(String [] args) {

        EPCompiler compiler = EPCompilerProvider.getCompiler();

        Configuration configuration = new Configuration();
        configuration.getCommon().addEventType(SmartMeterEvent.class);
        configuration.getCommon().addEventType(PotenciaEvent.class);


        CompilerArguments argse = new CompilerArguments(configuration);

        EPCompiled epCompiled;
        try {
            epCompiled = compiler.compile(EPLQueries.select(), argse);
        }
        catch (EPCompileException ex) {
            // handle exception here
            System.out.println(ex.toString());
            throw new RuntimeException(ex);
        }
        //EPCompiled epCompiled2;
        //try {
          //  epCompiled2 = compiler.compile(EPLQueries.TestEvent(), argse);
      //  }
        //catch (EPCompileException ex) {
            // handle exception here
          //  System.out.println(ex.toString());
            //throw new RuntimeException(ex);
        //}

        EPRuntime runtime = EPRuntimeProvider.getDefaultRuntime(configuration);

        EPDeployment deployment;
        EPDeployment deployment2;
        try {
            deployment = runtime.getDeploymentService().deploy(epCompiled);
            //deployment= runtime.getDeploymentService().deploy(epCompiled2);
        }
        catch (EPDeployException ex) {
            // handle exception here
            System.out.println(ex.toString());
            throw new RuntimeException(ex);
        }


        EPStatement statement = runtime.getDeploymentService().getStatement(deployment.getDeploymentId(), "Select");
        //EPStatement statement2 = runtime.getDeploymentService().getStatement(deployment2.getDeploymentId(), "TestEvent");


        SmartMeterListener sm = new SmartMeterListener();
        CountEventsListener ce = new CountEventsListener();
        SumEventsListener se = new SumEventsListener();
        ReturnAllListener re = new ReturnAllListener();
        AvgCorrenteListener ac = new AvgCorrenteListener();
        ConsumoEventsListener cl = new ConsumoEventsListener();
        statement.addListener(sm);
        //statement2.addListener(sm);


        SmartMeterProducer1 smartMeterProducer = new SmartMeterProducer1(runtime);
        PotenciaProducer potenciaProducer = new PotenciaProducer(runtime);
        System.out.println("Iniciando a produção de eventos...");
        smartMeterProducer.start();
        //potenciaProducer.start();


    }

}
