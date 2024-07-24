public class Sistema extends  CPUEventFirer implements  CPUListener {

    public CPU cpu;
    public Memory RAM;
    public Registro controlRW;
    Registro addressBUS;
    Registro dataBUS;
    public Controller controller;

    public void setup(Controller controller) {
        this.controller = controller;
        this.cpu = new CPU(controller);

        this.cpu.addCPUListener(this);


        this.RAM = new Memory(controller);
        this.addressBUS = new Registro( -1);
        this.dataBUS = new Registro( -1);
        this.controlRW = new Registro( -1);
    }


    public void cpuVuoleLeggereDallaMemoria(int indirizzo) {

        setToUno( controlRW );
        setToValore ( addressBUS , indirizzo);
    }

    private void setToValore(Registro registro, int valore) {
        registro.setValore(0);
        fireRegistroChangedValue( new RegistroChangedEvent(registro , valore));
    }

    private void setToUno(Registro registro) {
        registro.setToUno();
        fireRegistroChangedValue( new RegistroChangedEvent(registro , 1));
    }


    public void leggeDallaMemoria(int indirizzo) {
        //addressBUS.setToUndefined();
        setToUndefined (addressBUS);
        //controlRW.setToUndefined();
        setToUndefined(controlRW);

        //int dato = RAM.getValore(indirizzo);

        int dato=getValore(RAM.mem[indirizzo]);

        //dataBUS.setValore(dato);
        //cpu.MDR.setValore(dato);

        setToValore(dataBUS,dato);
        setToValore(cpu.MDR,dato);

    }

    private int getValore( Registro registro) {
        fireRegistroRead( new RegistroReadEvent(registro) );
        return registro.getValore();
    }

    private void setToUndefined(Registro registro) {
        setToValore( registro,-1 );

    }

    public void cpuHalettoDallaMemoria() {

        setToUndefined(dataBUS);
        //dataBUS.setToUndefined();
    }


    public String getStatoCpu(){
        return cpu.getStatoECiclo();
    }

    public boolean isInDecodeOrExecute() {

        return (cpu.isInDecodeOrExecute());
    }

    @Override
    public void onCPUEvent(CPUEvent event) {
        System.out.println( " SISTEMA : ricevuto CPU Event");
    }

    @Override
    public void onRegistroChanged(RegistroChangedEvent event) {

        System.out.println( " SISTEMA : ricevuto Registro CHANGED Event");

    }

    @Override
    public void onRegistroRead(RegistroReadEvent event) {
        System.out.println( " SISTEMA : ricevuto Registro READ   Event");

    }

    @Override
    public void onCpuHaFinitoCicloDiClockEvent(CpuHaFinitoCicloDiClockEvent event) {
            // non deve fare niente
    }
}
