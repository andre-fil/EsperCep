package SmartMeter.producer;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvSmart {

    private String X_Timestamp;
    private Date data;
    private double potencia;
    private double  voltagem;
    private double corrente;
    private double frequencia;
    private String meter;




    public double getPotencia() {
        return potencia * 1000;
    }

    public String getX_Timestamp() {
        X_Timestamp.toString();
        return X_Timestamp;
    }

    public void setX_Timestamp(String x_Timestamp) {
        X_Timestamp = x_Timestamp;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public double getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(double voltagem) {
        this.voltagem = voltagem;
    }

    public double getCorrente() {
        return corrente;
    }

    public void setCorrente(double corrente) {
        this.corrente = corrente;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public void setData(String x_Timestamp){
        String dado = x_Timestamp;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        {
            try {
               this.data= formato.parse(dado);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Date getData(){
        return data;
    }


    @Override
    public String toString() {
        return "CsvSmart{" +
                "X_Timestamp=" + X_Timestamp +
                "data" + data +
                ", potencia=" + potencia +
                ", voltagem=" + voltagem +
                ", corrente=" + corrente +
                ", frequencia=" + frequencia +
                ", meter='" + meter + '\'' +
                '}';
    }
}
