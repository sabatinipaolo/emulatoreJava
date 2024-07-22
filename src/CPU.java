public class CPU {
    public String stato = "FETCH0";
    public Registro A;
    public Registro B;
    public Registro C;
    public Registro D;
    public Registro IR;
    public Registro IP;
    public Registro SP;
    public Registro FLAG;
    public Registro MDR;
    public Registro MAR;
    public Registro RW;
    private Sistema sistema;
    private Controller controller;
    private InstructionSet instructionSet = new InstructionSet(this);

    private String decodifica;

    public CPU(Controller controller) {
        this.controller = controller;
        this.sistema = controller.sistema;
        this.decodifica ="";

        stato = "FETCH0";
        A = new Registro(0, controller);
        B = new Registro(0, controller);
        C = new Registro(0, controller);
        D = new Registro(0, controller);
        IP = new Registro(0, controller);
        IR = new Registro(0, controller);
        SP = new Registro(255, controller);

        FLAG = new Registro(0, controller);
        MDR = new Registro(-1, controller);
        MAR = new Registro(-1, controller);
        RW = new Registro(-1, controller);
    }

    public void impulsoDiClock() {

        if (stato.equals("FETCH0")) {
            int indirizzo = IP.getValore();
            MAR.setValore(indirizzo);
            RW.setToUno();

            sistema.cpuVuoleLeggereDallaMemoria(indirizzo);
            controller.cpuHaFinitoCicloDiClock(stato);
            stato = "FETCH1";

        } else if (stato.equals("FETCH1")) {
            //aspetta che la ram renda disponibile il dato
            sistema.leggeDallaMemoria();

            controller.cpuHaFinitoCicloDiClock(stato);
            stato = "FETCH2";

        } else if (stato.equals("FETCH2")) {

            sistema.cpuHalettoDallaMemoria();
            move(MDR, IR);
            int ipvalore = IP.getValore() + 1;
            IP.setValore(ipvalore++);
            controller.cpuHaFinitoCicloDiClock(stato);
            stato = "DECODE0";

        } else if (stato.equals("DECODE0")) {

            decodifica = instructionSet.decodifica(IR.getValore());
            controller.cpuHaFinitoCicloDiClock(stato);
            stato = "EXECUTE0";

        } else if (stato.equals("EXECUTE0")) {


            controller.cpuHaFinitoCicloDiClock(stato);
            stato = "FETCH0";
        }
    }

    public String getStato(){
        return this.stato;
    }

    public String getDecodifica(){
        return decodifica;
    }
     private void move(Registro sorg, Registro dest) {
        dest.setValore(sorg.getValore());
    }


}
