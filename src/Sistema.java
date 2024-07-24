public class Sistema extends CPUEventFirer implements CPUListener {

    public CPU cpu= new CPU(this);
    public Memory RAM= new Memory(this);
    public Registro controlRW=new Registro(-1);
    Registro addressBUS=new Registro(-1);
    Registro dataBUS=new Registro(-1);

    public void Sistema() {

        this.cpu.addCPUListener(this);
    }


    public void cpuVuoleLeggereDallaMemoria(int indirizzo) {

        setToValore(controlRW, 1);
        setToValore(addressBUS, indirizzo);
    }

    private void setToValore(Registro registro, int valore) {
        registro.setValore(valore);
        fireRegistroChangedValue(new RegistroChangedEvent(registro, valore));
    }

    public void leggeDallaMemoria(int indirizzo) {

        setToUndefined(addressBUS);
        setToUndefined(controlRW);

        int dato = getValore(RAM.get(indirizzo));

        setToValore(dataBUS, dato);
        setToValore(cpu.MDR, dato);

    }

    private int getValore(Registro registro) {
        fireRegistroRead(new RegistroReadEvent(registro));
        return registro.getValore();
    }

    private void setToUndefined(Registro registro) {
        setToValore(registro, -1);

    }

    public void cpuHalettoDallaMemoria() {

        setToUndefined(dataBUS);
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
