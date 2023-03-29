package SmartMeter.event;

public class MeterEvent {
    double consumo ;
    int mes;
    int ano;
    String meter;
    String location;

    public MeterEvent(double consumo, int mes, int ano, String meter, String location) {
        this.consumo = consumo;
        this.mes = mes;
        this.ano = ano;
        this.meter = meter;
        this.location = location;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

