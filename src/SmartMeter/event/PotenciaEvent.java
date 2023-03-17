package SmartMeter.event;


public class PotenciaEvent {
    public double potencia;
    public String meter;
    public String location;
    //public String mes;

    public PotenciaEvent(double potencia, String meter) {
        this.potencia = potencia;
        this.meter = meter;
        this.setLocation();
      //  this.setMes();
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

   // public void setMes(){
     //   this.mes = "janeiro";
    //}


    public double getPotencia() {
        return potencia;
    }

    public String getMeter() {
        return meter;
    }

    public String getLocation(){
        return location;
    }
}