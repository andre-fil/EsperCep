
/*package SmartMeter.event;
import java.util.Date;


public class copysecure {
    //public String X_Timestamp;
    public Date data;
    public double potencia;
    public double voltagem;
    public double corrente;
    public double frequencia;
    public String meter;

    public String location;
    public String Mes;

    public copysecure(Date data, double potencia, double voltagem, double corrente, double frequencia, String meter) {
        this.data = data;
        this.potencia = potencia;
        this.voltagem = voltagem;
        this.corrente = corrente;
        this.frequencia = frequencia;
        this.meter = meter;
        this.setLocation();
        this.setMes();
    }

    public void setLocation() {
        int num = Integer.parseInt(this.getMeter().substring(2));

        if(num <= 17){
            this.location = "Turu";
        } else if (num <= 34) {
            this.location = "Cohab";
        } else{
            this.location = "Angelim";
        }


    }

    public void setMes() {
        Date dataformatada = this.data;
        String mes = dataformatada.toString();
        String num = mes.substring(4, 7);
        if (num.equals("Jan")) {
            this.Mes = "Janeiro";
        } else if (num.equals("Feb")) {
            this.Mes = "Fevereiro";
        } else if (num.equals("Mar")) {
            this.Mes = "Março";
        } else if (num.equals("Abr")) {
            this.Mes = "Abril";
        } else if (num.equals("Mai")) {
            this.Mes = "Maio";
        }
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

    public String getMes() {
        return Mes;
    }
}
*/
