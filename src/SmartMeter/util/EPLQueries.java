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

    public static String voltageDrop(){
        //Verifica quando há queda de tensão superior a 4% (NBR:5410)
        return "@Name('voltageDrop') select * from SmartMeterEvent(voltagem < 230  - (230 * 0.05)  and meter = 'BR05')";
    }

    public  static String checkOverload(){
        return "create context dangerOverload" +
                "@Name('checkOverload') context dangerOverload select * from where( (voltagem < 207 or voltagem > 253) and voltagem > 0) and meter = 'BR02'";
    }
















    public static String select() {
        //*** Retorna todos os registros
        //return "@Name('Select') select * from SmartMeterEvent where location = 'Turu' and potencia > 0 ";

        return "@Name('Select') select * from SmartMeterEvent";




        //*** Verifica se uma rede está com queda ou alta variação de tensão
       // return "@Name('Select') select * from SmartMeterEvent where( (voltagem < 207 or voltagem > 253) and voltagem > 0) and meter = 'BR02' ";

        //*** É possível aplicar filtros ou cláusulas where que retornam todos os parâmetros
        //return "@Name('Select') select * from SmartMeterEvent where x_Timestamp = '2020-02-02 20:00:00'";

    }


    public static String countEvents(){
        //*** Retorna a quantidade de eventos que acontecem sob determinadas condições

        //return "@Name('countEvents') select count(*) as cout from SmartMeterEvent where meter = 'BR02'";

        return "@Name('Select') insert into potenciaZero "
                + "select * " +
                "from SmartMeterEvent(potencia = 2000)";

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

    public static String avgPotencia(){
        //return "@Name('avgPotencia') select avg(potencia) as media from SmartMeterEvent where potencia > 0";

        return "@Name('avgPotencia') select avg(potencia) as media from SmartMeterEvent#length_batch(10000) where meter = 'BR02'";
    }




}
    
