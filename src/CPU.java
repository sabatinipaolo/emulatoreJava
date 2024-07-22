public class CPU {
    public String stato;
    public int ciclo = 0;

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
        this.decodifica = "";

        stato = "power OFF";
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

    public void avvia() {

        while (true) {
            fetch();
            decode();
            execute();
        }
    }

    public void fetch() {
        stato = "FETCH";
        letturaDaMemoria(IP, IR);

    }

    public void decode() {
        stato = "DECODE";
        ciclo = 0;

        decodifica = instructionSet.decodifica(IR.getValore());

        int ipvalore = IP.getValore() + 1;
        IP.setValore(ipvalore++);

        controller.cpuHaFinitoCicloDiClock(stato);
        controller.cpuAspettaUnCicloDiClock();
    }

    public void execute() {

        stato = "EXECUTE";
        ciclo = 0;

        controller.cpuHaFinitoCicloDiClock(stato);
        controller.cpuAspettaUnCicloDiClock();
    }

    public void letturaDaMemoria(Registro registroIndirizzo, Registro registroDestinazione) {
        ciclo = 0;
        int indirizzo = registroIndirizzo.getValore();
        MAR.setValore(indirizzo);
        RW.setToUno();
        sistema.cpuVuoleLeggereDallaMemoria(indirizzo);

        ciclo = 1;
        //aspetta che la ram renda disponibile il dato
        sistema.leggeDallaMemoria();

        controller.cpuHaFinitoCicloDiClock(stato);
        controller.cpuAspettaUnCicloDiClock();

        ciclo = 2;

        sistema.cpuHalettoDallaMemoria();
        move(MDR, registroDestinazione);

        controller.cpuHaFinitoCicloDiClock(stato);
        controller.cpuAspettaUnCicloDiClock();
    }

    public String getStatoECiclo() {
        return stato + "-" + ciclo;
    }

    public String getDecodifica() {
        return decodifica;
    }

    private void move(Registro sorg, Registro dest) {
        dest.setValore(sorg.getValore());
    }


    public boolean isInDecodeOrExecute() {

        return stato.equals("DECODE") || stato.equals("EXECUTE");
    }
}
