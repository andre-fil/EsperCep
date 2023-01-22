package SmartMeter.util;

public class EPLQueries {


    public static String select() {
        //*** Retorna todos os registros
        //*** É possível aplicar filtros ou cláusulas where que retornam todos os parâmetros
        //return "@Name('Select') select * from SmartMeterEvent";
        return "@Name('Select') select * from SmartMeterEvent where meter='BR05' and frequencia > 51";
        //return "@Name('Select') select * from SmartMeterEvent where x_Timestamp = '2020-02-02 20:00:00'";

    }

    public static String countEvents(){
        //*** Retorna a quantidade de eventos que acontecem sob determinadas condições
        return "@Name('countEvents') select count(*) as cout from SmartMeterEvent where meter = 'BR04' and frequencia > 51";

    }

    public static String sumEvent(){
        return "@Name('sumEvent') select sum(potencia) as sout from SmartMeterEvent where meter = 'BR02'";
        //return "create context SumaDay start @now end after 60 min;" +
                //"@Name('sumEvent') context SumaDay select sum(potencia) as sout from SmartMeterEvent";
        //return "@Name('sumEvent') select sum(voltagem) as sout from SmartMeterEvent output last every 20 seconds;";
        //return "context part partition by meter from SmartMeterEvent;" +
                //"@Name('sumEvent') context particao select sum(potencia) as sout from SmartMeterEvent;";
    }

    public static String returnAtribute(){
        return "@Name('returnAtt') select potencia from SmartMeterEvent";
    }


}
    
