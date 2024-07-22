public class Sistema {

    public CPU cpu;
    public Memory RAM;
    public PIN RW;
    BUS addressBUS;
    BUS dataBUS;
    private Controller controller;

    public void setup(Controller controller) {
        this.controller = controller;
        this.cpu = new CPU(controller);
        this.RAM = new Memory(controller);
        this.addressBUS = new BUS("address Bus", -1, controller);
        this.dataBUS = new BUS("data Bus", -1, controller);
        this.RW = new PIN("RW cntl Bus", -1, controller);
    }

    public void impulsoDiClok() {

        cpu.impulsoDiClock();

    }

    public void cpuVuoleLeggereDallaMemoria(int indirizzo) {
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


    public String getStatoCpu(){
        return cpu.getStato();
    }

}
