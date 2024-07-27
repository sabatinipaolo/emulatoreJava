public class CPU extends CPUEventFirer {
    public Registro A = new Registro(0);
    public Registro B = new Registro(0);
    public Registro C = new Registro(0);
    public Registro D = new Registro(0);
    public Registro IR = new Registro(0);
    public Registro IP = new Registro(0);
    public Registro SP = new Registro(255);
    public Registro FLAG = new Registro(0);
    public Registro MDR = new Registro(0);
    public Registro MAR = new Registro(0);
    public Registro RW = new Registro(0);
    private String stato = "Power OFF";
    private int ciclo = 0;
    private Sistema sistema;
    private InstructionSet instructionSet = new InstructionSet(this);

    private String decodifica = "";

    public CPU(Sistema sistema) {

        this.sistema = sistema;

    }

    public void avvia() {
        Istruzione istruzione = null;

        while (true) {

            istruzione = fetch();
            istruzione = decode();
            execute(istruzione);

        }
    }

    public Istruzione fetch() {
        stato = "FETCH";
        ciclo = 0;
        letturaDaMemoria(IP, IR);

        return instructionSet.getIstruzione(IR.getValore());  //OSS. non deve lanciare evento

    }

    public Istruzione decode() {
        stato = "DECODE";
        ciclo = 0;

        Istruzione istruzione = instructionSet.getIstruzione(IR.getValore());
        decodifica = istruzione.getDecodifica();

        IP.inc();

        finitoCicloDiClock();

        return istruzione;
    }

    public void execute(Istruzione istruzione) {

        stato = "EXECUTE";
        ciclo = 0;

        istruzione.esegui();

        finitoCicloDiClock();
    }

    public void letturaDaMemoria(Registro registroIndirizzo, Registro registroDestinazione) {
        //ciclo = 0;

        int indirizzo = registroIndirizzo.muoviValoreIn( MAR );

        RW.setValore(1);

        sistema.cpuVuoleLeggereDallaMemoria( indirizzo );

        finitoCicloDiClock();

        incCiclo();

        //ciclo = 1;
        int dato= sistema.laMemoriaMetteIlDatoNelBusDati();

        MDR.setValore( dato );

        finitoCicloDiClock();

        //ciclo = 2;
        incCiclo();

        MDR.muoviValoreIn( registroDestinazione );

        sistema.cpuHalettoDallaMemoria();

        finitoCicloDiClock();

        incCiclo();

    }

    public String getStatoECiclo() {
        return stato + "-" + ciclo;
    }

    public String getDecodifica() {
        return decodifica;
    }


    public boolean isInDecodeOrExecute() {

        return stato.equals("DECODE") || stato.equals("EXECUTE");
    }

    public void incCiclo() {
        ciclo++;
    }

    private void finitoCicloDiClock() {
        fireCpuHaFinitoCicloDiClockEvent(new CpuHaFinitoCicloDiClockEvent(this));
        fireCpuAspettaImpulsoDiClockEvent(new CpuAspettaImpulsoDiClockEvent(this));
    }

}
