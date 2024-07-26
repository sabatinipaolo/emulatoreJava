public class Sistema extends CPUEventFirer {
//TODO: è veramente necessario passare per gli eventi?
    public CPU cpu= new CPU(this);
    public Memory RAM= new Memory(this);
    public Registro controlRW=new Registro(-1);
    Registro addressBUS=new Registro(-1);
    Registro dataBUS=new Registro(-1);


    public void cpuVuoleLeggereDallaMemoria() {

        setToValore(controlRW, 1);

        int indirizzo = cpu.MAR.getValore();  //
        setToValore(addressBUS, indirizzo);
    }

    public void laMemoriaMetteIlDatoNelBusDati() {

        int dato = getValore(RAM.get( addressBUS.getValore()));

        setToValore(dataBUS, dato);
        setToValore(cpu.MDR, dato);


    }

    public void cpuHalettoDallaMemoria() {
        setToUndefined(addressBUS);
        setToUndefined(controlRW);
        setToUndefined(dataBUS);
    }

    private void setToValore(Registro registro, int valore) {
        registro.setValore(valore);
        fireRegistroChangedValue(new RegistroChangedEvent(registro, valore));
    }

    private int getValore(Registro registro) {
        fireRegistroRead(new RegistroReadEvent(registro));
        return registro.getValore();
    }

    private void setToUndefined(Registro registro) {
        setToValore(registro, -1); //TODO: defininire costante per UNDEFINED

    }

    public String getStatoCpu() {
        return cpu.getStatoECiclo();
    }

    public boolean isInDecodeOrExecute() {

        return (cpu.isInDecodeOrExecute());
    }

}
