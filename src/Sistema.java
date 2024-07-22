public class Sistema {

    public CPU cpu;
    BUS addressBUS;
    BUS dataBUS;
    private Controller controller;
    public Memory RAM;
    public PIN RW;

    public Sistema() {

    }

    public void impulsoDiClok() {

        cpu.impulsoDiClock();

    }

    public void cpuVuoleLeggereDallaMemoria( int indirizzo ) {
        RW.setToUno();
        addressBUS.setValore(indirizzo);
    }


    public void leggeDallaMemoria() {
        int indirizzo = addressBUS.getValore();
        int dato = RAM.getValore(indirizzo);
        dataBUS.setValore(dato);
        cpu.MDR.setValore(dato);

    }

    public void cpuHalettoDallaMemoria() {

        addressBUS.setUndefined();

        RW.setToUndefined();


    }

    public void setup(Controller controller) {
        this.controller = controller;
        this.cpu = new CPU(controller);
        this.RAM = new Memory(controller);
        this.addressBUS = new BUS("address Bus", -1, controller);
        this.dataBUS = new BUS("data Bus", -1, controller);
        this.RW = new PIN("RW control", -1, controller);
    }
}
