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
        return "@Name('consumo') select sum(potencia) as consumo, mes, ano, meter, location from SmartMeterEvent where mes = 2 and meter = 'BR02'";
    }

    public static String subtensao(){
        //Verifica quando há queda de tensão superior a 4% (NBR:5410)
        return "@Name('subtensao') select * from SmartMeterEvent(voltagem < 230  - (230 * 0.05)  and meter = 'BR05')";
    }

    public static String avgCorrenteOrigin(){
        return "@Name('media') select avg(corrente) as media from SmartMeterEvent#length(2) where meter = 'BR02' and corrente > 0";
    }

    public static String avgCorrente(){
       /* return "@Name('media') select avg(corrente) as media from SmartMeterEvent#length(2) where meter = 'BR02' and corrente > 0"; */
        return"create context AvgbyMeter partition by meter,mes from SmartMeterEvent;\n" +
                "@Name('media')\n" +
                "context AvgbyMeter select avg(corrente) as media, dia, mes, meter from SmartMeterEvent where  mes = 2 group by meter"
                ;
    }



    public static String ConsumobyMeter(){
        return "create context ConsumoMeter partition by meter,mes from SmartMeterEvent;\n" +
                " @Name('consumo')\n" +
                "context ConsumoMeter select sum(potencia) as consumo, mes, ano, meter, location\n" +
                "from SmartMeterEvent \n" +
                "group by meter ;";
    }





















    //EDGE --> casa a casa
    public static String lackOfEnergy() {
        //retorna as casas que estão .
        return "@Name('lackOfEnergy') select * from SmartMeterEvent where (potencia = 0) and (voltagem > 0) and (meter = 'BR02') and ( mes = 1 and dia = 1)";

        /*
        return "@Name('lackOfEnergy') create window lackOfEnergy insert into lackOfEnergy" +
                " select *" +
                " from SmartMeterEvent where (potencia = 0) and (voltagem > 0) and (meter = 'BR02')";

                return "@Name('lackOfEnergy') select * from pattern [SmartMeterEvent(potencia = 0)]";
*/
    }

    public static String noEnergy(){
        //Retorna quando a casa está sem energia --> sem transmissão da concessionária.

        return "@Name('noEnergy') select * from SmartMeterEvent where (potencia = 0 and voltagem = 0 and corrente = 0 and meter = 'BR02') and ( mes = 1 and dia = 1)";

    }



    public  static String checkOverload(){
        return "create context dangerOverload" +
                "@Name('checkOverload') context dangerOverload select * from where( (voltagem < 207 or voltagem > 253) and voltagem > 0) and meter = 'BR02'";
    }


    public static String select() {

        return"@Name('Select') select * from SmartMeterEvent where mes = 12 and dia = 21 and meter = 'BR02'";

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

    public static String padrao_sobrecarga(){
        return "@Name('padrao') SELECT * FROM SmartMeterEvent\n" +
                "MATCH_RECOGNIZE (\n" +
                "  PARTITION BY meter\n" +
                "  MEASURES\n" +
                "    avg(potencia) as avgpotencia,\n" +
                "    avg(corrente) as avgcorrente\n" +
                "  PATTERN (overload)\n" +
                "  DEFINE\n" +
                "    overload as avgcorrente > 15 and avgpotencia > 5000\n" +
                ")\n";
    }






}


//*** Retorna todos os registros

//return "@Name('Select')create window Test Event" + " insert into TestEvent select * from SmartMeterEvent";
//return "@Name('Select') select smart.* from pattern [every smart=SmartMeterEvent(potencia = 0) -> smart=SmartMeterEvent(potencia >0)]";
//return "@Name('Select') select * from SmartMeterEvent output last every 5 events";
//return "@Name('Select') @Audit select *,avg(corrente) from SmartMeterEvent(corrente > avg(corrente))";
//return "@Name('Select') select * from SmartMeterEvent#time_batch(1 min)";


/*MATCH_RECOGNIZE (
      PARTITION BY location
      ORDER BY hora, dia, mes
      MEASURES
         LAST(voltagem) AS last_voltagem,
         LAST(corrente) AS last_corrente
      PATTERN (abnormal_drop)
      DEFINE
         abnormal_drop AS last_voltagem < 100 AND last_corrente < 10
   )```*/