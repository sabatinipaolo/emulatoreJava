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

    public void execute(Istruzione istruzione) {

        stato = "EXECUTE";
        ciclo = 0;

        istruzione.esegui(sistema);

        finitoCicloDiClock();
    }

    public void letturaDaMemoria(Registro registroIndirizzo, Registro registroDestinazione) {
        //ciclo = 0;

        move(MAR, registroIndirizzo.getValore());

        move(RW, 1);

        sistema.cpuVuoleLeggereDallaMemoria();

        finitoCicloDiClock();

        incCiclo();

        sistema.laMemoriaMetteIlDatoNelBusDati();

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

    private void finitoCicloDiClock() {
        fireCpuHaFinitoCicloDiClockEvent(new CpuHaFinitoCicloDiClockEvent(this));
        fireCpuAspettaImpulsoDiClockEvent(new CpuAspettaImpulsoDiClockEvent(this));
    }

}
