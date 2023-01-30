package SmartMeter.util;


public class EPLQueries {


    public static String select() {
        //*** Retorna todos os registros
        return "@Name('Select') select * from SmartMeterEvent where data = '2020-12'";

        //*** Verifica se uma rede está com queda ou alta variação de tensão
       // return "@Name('Select') select * from SmartMeterEvent where( (voltagem < 207 or voltagem > 253) and voltagem > 0) and meter = 'BR02' ";

        //*** É possível aplicar filtros ou cláusulas where que retornam todos os parâmetros
        //return "@Name('Select') select * from SmartMeterEvent where x_Timestamp = '2020-02-02 20:00:00'";

    }

    public static String countEvents(){
        //*** Retorna a quantidade de eventos que acontecem sob determinadas condições
        return "@Name('countEvents') select count(*) as cout from SmartMeterEvent where meter = 'BR02'";

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

        return "@Name('returnAtt') select corrente from SmartMeterEvent";
    }

    public static String avgPotencia(){
        //return "@Name('avgPotencia') select avg(potencia) as media from SmartMeterEvent where potencia > 0";

        return "@Name('avgPotencia') select avg(potencia) as media from SmartMeterEvent#length_batch(10000) where meter = 'BR02'";
    }

    public  static String checkOverload(){
        return "create context dangerOverload" +
                "@Name('checkOverload') context dangerOverload select * from where( (voltagem < 207 or voltagem > 253) and voltagem > 0) and meter = 'BR02'";
    }


}
    
