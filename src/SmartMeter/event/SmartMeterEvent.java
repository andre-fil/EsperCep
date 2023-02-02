package SmartMeter.event;
import java.util.Date;


public class SmartMeterEvent {
    //public String X_Timestamp;
    public Date data;
    public double potencia;
    public double voltagem;
    public double corrente;
    public double frequencia;
    public String meter;

    public String location;

    public SmartMeterEvent(Date data,double potencia, double voltagem, double corrente, double frequencia, String meter, String location) {
        this.data = data;
        this.potencia = potencia;
        this.voltagem = voltagem;
        this.corrente = corrente;
        this.frequencia = frequencia;
        this.meter = meter;
        this.location = location;
    }

    public Date getData(){
        return data;
    }

    public double getPotencia() {
        return potencia;
    }

    public double getCorrente() {
        return corrente;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public String getMeter() {
        return meter;
    }

    public double getVoltagem() {
        return voltagem;
    }

    public String getLocation(){
        return location;
    }
}