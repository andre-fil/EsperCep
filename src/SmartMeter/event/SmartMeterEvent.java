package SmartMeter.event;
import java.util.Date;


public class SmartMeterEvent {
    public String X_Timestamp;
    public double potencia;
    public double voltagem;
    public double corrente;
    public double frequencia;
    public String meter;

    public SmartMeterEvent(String X_Timestamp,double potencia, double voltagem, double corrente, double frequencia, String meter) {
        this.X_Timestamp = X_Timestamp;
        this.potencia = potencia;
        this.voltagem = voltagem;
        this.corrente = corrente;
        this.frequencia = frequencia;
        this.meter = meter;
    }

    public String getX_Timestamp() {
        X_Timestamp.toString();
        return X_Timestamp;
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
}