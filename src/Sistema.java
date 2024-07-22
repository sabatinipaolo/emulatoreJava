public class Sistema {

    public CPU cpu;
    public Memory RAM;
    public Registro RW;
    Registro addressBUS;
    Registro dataBUS;
    private Controller controller;

    public void setup(Controller controller) {
        this.controller = controller;
        this.cpu = new CPU(controller);
        this.RAM = new Memory(controller);
        this.addressBUS = new Registro("address Bus", -1, controller);
        this.dataBUS = new Registro("data Bus", -1, controller);
        this.RW = new Registro("RW cntl Bus", -1, controller);
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

        addressBUS.setToUndefined();
        dataBUS.setToUndefined();
        RW.setToUndefined();


    }


    public String getStatoCpu(){
        return cpu.getStato();
    }

}
