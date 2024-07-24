public class CPU extends CPUEventFirer {
    private String stato;
    private int ciclo = 0;

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
    private int opCode;

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
        ciclo=0;
        letturaDaMemoria(IP, IR);

    }

    public void decode() {
        stato = "DECODE";
        ciclo = 0;
        opCode = IR.getValore();
        decodifica = instructionSet.decodifica(opCode);

        int ipvalore = IP.getValore() + 1;
        IP.setValore(ipvalore++);

        //controller.cpuHaFinitoCicloDiClock();
        fireCpuHaFinitoCicloDiClockEvent(new CpuHaFinitoCicloDiClockEvent(this));
        controller.cpuAspettaUnCicloDiClock();
    }

    public void execute() {

        stato = "EXECUTE";
        ciclo = 0;

        instructionSet.esegui(opCode, controller.sistema );

        //controller.cpuHaFinitoCicloDiClock();
        fireCpuHaFinitoCicloDiClockEvent(new CpuHaFinitoCicloDiClockEvent(this));
        controller.cpuAspettaUnCicloDiClock();
    }

    public void letturaDaMemoria(Registro registroIndirizzo, Registro registroDestinazione) {
        //ciclo = 0;
        int indirizzo = getValore( registroIndirizzo );

        move(MAR, registroIndirizzo.getValore());

        move(RW , 1) ;

        sistema.cpuVuoleLeggereDallaMemoria(indirizzo);

        //controller.cpuHaFinitoCicloDiClock();
        fireCpuHaFinitoCicloDiClockEvent(new CpuHaFinitoCicloDiClockEvent(this));
        controller.cpuAspettaUnCicloDiClock();

        //ciclo = 1;
        incCiclo();
        //aspetta che la ram renda disponibile il dato
        sistema.leggeDallaMemoria(indirizzo);

        //controller.cpuHaFinitoCicloDiClock();
        fireCpuHaFinitoCicloDiClockEvent(new CpuHaFinitoCicloDiClockEvent(this));
        controller.cpuAspettaUnCicloDiClock();

        //ciclo = 2;
        incCiclo();

        sistema.cpuHalettoDallaMemoria();
        move(MDR, registroDestinazione);

        //controller.cpuHaFinitoCicloDiClock();
        fireCpuHaFinitoCicloDiClockEvent(new CpuHaFinitoCicloDiClockEvent(this));
        controller.cpuAspettaUnCicloDiClock();
    }

    private int getValore(Registro registro) {
        int valore = registro.getValore();
        fireRegistroRead( new RegistroReadEvent(registro));
        return valore;

    }

    public String getStatoECiclo() {
        return stato + "-" + ciclo;
    }

    public String getDecodifica() {
        return decodifica;
    }

    public void move(Registro sorg, Registro dest) {

        dest.setValore(sorg.getValore());
        fireRegistroRead(new RegistroReadEvent(sorg));
        fireRegistroChangedValue(new RegistroChangedEvent(dest,dest.getValore()));


    }
    private void move (Registro registro , int valore){

        registro.setValore(valore);
        fireRegistroChangedValue(new RegistroChangedEvent(registro,valore));

    }
    public void incRegistro(Registro registro){
        int operando = registro.getValore() + 1;
        registro.setValore(operando);
    }
    public void decRegistro(Registro registro){
        int operando = registro.getValore() - 1;
        registro.setValore(operando);
    }


    public boolean isInDecodeOrExecute() {

        return stato.equals("DECODE") || stato.equals("EXECUTE");
    }

    public void incCiclo() { ciclo ++;
    }
}
