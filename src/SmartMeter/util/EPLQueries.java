package SmartMeter.util;

/*

{
			"name": "lackOfEnergy",
			"description": "Verificação de queda de energia por falha interna",
			"qos": "AT_MOST_ONCE",
			"level": "EDGE",
			"target": "EDGE",
			"tagFilter": "",
			"definition": "select * from SmartMeterEvent where (potencia = 0) and (voltagem > 0) and (meter = 'BR02')",
			"inputEventTypes": ["SmartMeterEvents"],
			"attrs": {

			}
		}
		**/


public class EPLQueries {



    public static String ConsumoEnergia(){
        return "@Name('consumo') select sum(potencia) as consumo,meter,location from PotenciaEvent#length_batch(12000) where meter = 'BR02'";
    }

    public static String subtensao(){
        //Verifica quando há queda de tensão superior a 4% (NBR:5410)
        return "@Name('subtensao') select * from SmartMeterEvent(voltagem < 230  - (230 * 0.05)  and meter = 'BR05')";
    }

    public static String avgCorrente(){

        return "@Name('avgCorrente') select avg(corrente) as media from SmartMeterEvent#length_batch(480) where meter = 'BR02' and corrente > 0";
    }


    public static String ConsumobyMeter(){
        return "create context ConsumoMeter partition by meter from PotenciaProducer "
                + "@Name('consumoMeters') context ConsumoMeter select sum(potencia) as consumo, meter, location from PotenciaEvent#length_batch(10) group by meter";
    }





















    //EDGE --> casa a casa
    public static String lackOfEnergy() {
        //retorna as casas que estão .
        return "@Name('lackOfEnergy') select * from SmartMeterEvent where (potencia = 0) and (voltagem > 0) and (meter = 'BR02')";

        /*
        return "@Name('lackOfEnergy') create window lackOfEnergy insert into lackOfEnergy" +
                " select *" +
                " from SmartMeterEvent where (potencia = 0) and (voltagem > 0) and (meter = 'BR02')";

                return "@Name('lackOfEnergy') select * from pattern [SmartMeterEvent(potencia = 0)]";
*/
    }

    public static String noEnergy(){
        //Retorna quando a casa está sem energia --> sem transmissão da concessionária.

        return "@Name('noEnergy') select * from SmartMeterEvent(potencia = 0 and voltagem = 0 and corrente = 0 and meter = 'BR02')";

    }



    public  static String checkOverload(){
        return "create context dangerOverload" +
                "@Name('checkOverload') context dangerOverload select * from where( (voltagem < 207 or voltagem > 253) and voltagem > 0) and meter = 'BR02'";
    }


    public static String select() {

        return"@Name('Select') select * from SmartMeterEvent where mes = 6 and meter = 'BR02'";

    }

    public static String TestEvent(){
        return "@Name('TestEvent') select * from TestEvent";

    }


    public static String countEvents(){
        //*** Retorna a quantidade de eventos que acontecem sob determinadas condições

        return "@Name('countEvent') select count(*) as cout from SmartMeterEvent where meter = 'BR04'";

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

        //return "@Name('returnAtt') select corrente from SmartMeterEvent";
        //return "@Name('select') select * from pattern [every erro = SmartMeterEvent(potencia > 0)]";

        //return "@Name('returnAtt') select * from SmartMeterEvent";
        return "@Name('returnAtt') select * from pattern [SmartMeterEvent(potencia = 0)]";


    }






}


//*** Retorna todos os registros

//return "@Name('Select')create window Test Event" + " insert into TestEvent select * from SmartMeterEvent";
//return "@Name('Select') select smart.* from pattern [every smart=SmartMeterEvent(potencia = 0) -> smart=SmartMeterEvent(potencia >0)]";
//return "@Name('Select') select * from SmartMeterEvent output last every 5 events";
//return "@Name('Select') @Audit select *,avg(corrente) from SmartMeterEvent(corrente > avg(corrente))";
//return "@Name('Select') select * from SmartMeterEvent#time_batch(1 min)";
