package SmartMeter;

import SmartMeter.consumer.*;
import SmartMeter.event.SmartMeterEvent;
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

        CompilerArguments argse = new CompilerArguments(configuration);

        EPCompiled epCompiled;
        try {
            epCompiled = compiler.compile(EPLQueries.avgPotencia(), argse);
        }
        catch (EPCompileException ex) {
            // handle exception here
            System.out.println(ex.toString());
            throw new RuntimeException(ex);
        }

        EPRuntime runtime = EPRuntimeProvider.getDefaultRuntime(configuration);

        EPDeployment deployment;
        try {
            deployment = runtime.getDeploymentService().deploy(epCompiled);
        }
        catch (EPDeployException ex) {
            // handle exception here
            System.out.println(ex.toString());
            throw new RuntimeException(ex);
        }

        EPStatement statement = runtime.getDeploymentService().getStatement(deployment.getDeploymentId(), "avgPotencia");

        SmartMeterListener wls = new SmartMeterListener();
        CountEventsListener ce = new CountEventsListener();
        SumEventsListener se = new SumEventsListener();
        ReturnAllListener re = new ReturnAllListener();
        AvgPotenciaListener ap = new AvgPotenciaListener();
        statement.addListener(ap);

        SmartMeterProducer1 smartMeterProducer = new SmartMeterProducer1(runtime);
        System.out.println("Iniciando a produção de eventos...");
        smartMeterProducer.start();

    }

}
