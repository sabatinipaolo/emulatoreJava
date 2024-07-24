public class Sistema implements  CPUListener {

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
        this.cpu.addCPUListener(controller.vista);

        this.RAM = new Memory(controller);
        this.addressBUS = new Registro( -1, controller);
        this.dataBUS = new Registro( -1, controller);
        this.controlRW = new Registro( -1, controller);
    }


    public void cpuVuoleLeggereDallaMemoria(int indirizzo) {
        controlRW.setToUno();
        addressBUS.setValore(indirizzo);
    }


    public void leggeDallaMemoria(int indirizzo) {
        addressBUS.setToUndefined();
        controlRW.setToUndefined();
        int dato = RAM.getValore(indirizzo);
        dataBUS.setValore(dato);
        cpu.MDR.setValore(dato);

    }

    public void cpuHalettoDallaMemoria() {

       // addressBUS.setToUndefined();
       // controlRW.setToUndefined();
        dataBUS.setToUndefined();
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

        System.out.println( " SISTEMA : ricevuto RegistroChanged  Event");

    }
}
