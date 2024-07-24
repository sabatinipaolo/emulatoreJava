public class CPU extends CPUEventFirer {
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
    private String stato;
    private int ciclo = 0;
    private Sistema sistema;
    private InstructionSet instructionSet = new InstructionSet(this);

    private String decodifica;

    public CPU(Sistema sistema) {
        //this.controller = controller;
        this.sistema = sistema;
        this.decodifica = "";

        stato = "power OFF";
        A = new Registro(0);
        B = new Registro(0);
        C = new Registro(0);
        D = new Registro(0);
        IP = new Registro(0);
        IR = new Registro(0);
        SP = new Registro(255);

        FLAG = new Registro(0);
        MDR = new Registro(-1);
        MAR = new Registro(-1);
        RW = new Registro(-1);
    }

    public void avvia() {
        Istruzione istruzione = null;

        while (true) {

            istruzione = fetch();
            decode(istruzione);
            execute(istruzione);

        }
    }

    public Istruzione fetch() {
        stato = "FETCH";
        ciclo = 0;
        letturaDaMemoria(IP, IR);

        return instructionSet.getIstruzione(IR.getValore());

    }

    public void decode(Istruzione istruzione) {
        stato = "DECODE";
        ciclo = 0;

        //opCode = getValore(IR);  //serve campo per passala ad execute senza rovinare l'estetica di avvia()...
        decodifica = istruzione.getDecodifica();

        inc(IP);

        finitoCicloDiClock();
    }

    private void finitoCicloDiClock() {
        fireCpuHaFinitoCicloDiClockEvent(new CpuHaFinitoCicloDiClockEvent(this));
        fireCpuAspettaImpulsoDiClockEvent(new CpuAspettaImpulsoDiClockEvent(this));
    }

    public void execute(Istruzione istruzione) {

        stato = "EXECUTE";
        ciclo = 0;

        istruzione.esegui(sistema);

        finitoCicloDiClock();
    }

    public void letturaDaMemoria(Registro registroIndirizzo, Registro registroDestinazione) {
        //ciclo = 0;
        int indirizzo = getValore(registroIndirizzo);

        move(MAR, registroIndirizzo.getValore());

        move(RW, 1);

        sistema.cpuVuoleLeggereDallaMemoria(indirizzo);

        finitoCicloDiClock();

        incCiclo();

        //aspetta che la ram renda disponibile il dato
        sistema.leggeDallaMemoria(indirizzo);

        finitoCicloDiClock();

        //ciclo = 2;
        incCiclo();

        sistema.cpuHalettoDallaMemoria();
        move(MDR, registroDestinazione);

        finitoCicloDiClock();

    }


    public String getStatoECiclo() {
        return stato + "-" + ciclo;
    }

    public String getDecodifica() {
        return decodifica;
    }

    private int getValore(Registro registro) {
        int valore = registro.getValore();
        fireRegistroRead(new RegistroReadEvent(registro));
        return valore;

    }

    public void move(Registro sorg, Registro dest) {

        dest.setValore(sorg.getValore());
        fireRegistroRead(new RegistroReadEvent(sorg));
        fireRegistroChangedValue(new RegistroChangedEvent(dest, dest.getValore()));


    }

    private void move(Registro registro, int valore) {

        registro.setValore(valore);
        fireRegistroChangedValue(new RegistroChangedEvent(registro, valore));

    }

    public void inc(Registro registro) {
        int valoreIncrementato = registro.getValore() + 1;
        move(registro, valoreIncrementato);
    }

    public void decRegistro(Registro registro) {
        int operando = registro.getValore() - 1;
        registro.setValore(operando);
    }


    public boolean isInDecodeOrExecute() {

        return stato.equals("DECODE") || stato.equals("EXECUTE");
    }

    public void incCiclo() {
        ciclo++;
    }
}
