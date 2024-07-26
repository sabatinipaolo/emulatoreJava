public class Sistema extends CPUEventFirer {
//TODO: è veramente necessario passare per gli eventi?
    public CPU cpu= new CPU(this);
    public Memory RAM= new Memory(this);
    public Registro controlRW=new Registro(-1);
    Registro addressBUS=new Registro(-1);
    Registro dataBUS=new Registro(-1);


    public void cpuVuoleLeggereDallaMemoria(int indirizzo ) {

        controlRW.setValore(1);

        addressBUS.setValore(indirizzo);
    }

    public int  laMemoriaMetteIlDatoNelBusDati() {
        int indirizzo = addressBUS.getValore();
        int dato = RAM.get( indirizzo ).getValore() ;

        dataBUS.setValore( dato );
        return dato;

    }

    public void cpuHalettoDallaMemoria() {
        addressBUS.setToUndefined();
        controlRW.setToUndefined();
        dataBUS.setToUndefined();
    }


    public String getStatoCpu() {
        return cpu.getStatoECiclo();
    }

    public boolean isInDecodeOrExecute() {

        return (cpu.isInDecodeOrExecute());
    }

}
