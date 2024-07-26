public class Sistema extends CPUEventFirer implements CPUListener {
//TODO: è veramente necessario passare per gli eventi?
    public CPU cpu= new CPU(this);
    public Memory RAM= new Memory(this);
    public Registro controlRW=new Registro(-1);
    Registro addressBUS=new Registro(-1);
    Registro dataBUS=new Registro(-1);

    public void Sistema() {

       // this.cpu.addCPUListener(this);
    }

    public void cpuVuoleLeggereDallaMemoria() {

        setToValore(controlRW, 1);

        int indirizzo = cpu.MAR.getValore();  //
        setToValore(addressBUS, indirizzo);
    }

    public void laMemoriaMetteIlDatoNelBusDati() {

        int dato = getValore(RAM.get( addressBUS.getValore()));

        setToValore(dataBUS, dato);
        setToValore(cpu.MDR, dato);

        setToUndefined(addressBUS);
        setToUndefined(controlRW);
    }

    public void cpuHalettoDallaMemoria() {

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

    @Override
    public void onCPUEvent(CPUEvent event) {
        System.out.println(" SISTEMA : ricevuto CPU Event");
    }

    @Override
    public void onRegistroChanged(RegistroChangedEvent event) {

        System.out.println(" SISTEMA : ricevuto Registro CHANGED Event");

    }

    @Override
    public void onRegistroRead(RegistroReadEvent event) {
        System.out.println(" SISTEMA : ricevuto Registro READ   Event");

    }

    @Override
    public void onCpuHaFinitoCicloDiClockEvent(CpuHaFinitoCicloDiClockEvent event) {
        // non deve fare niente
    }

    @Override
    public void onCpuAspettaImpulsoDiClockEvent(CpuAspettaImpulsoDiClockEvent event) {
        // non deve fare niente
    }
}
