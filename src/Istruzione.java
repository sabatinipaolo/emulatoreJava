public class Istruzione {
    private int opCode;
    private String decodifica;

    public Istruzione(int opCode, String decodifica) {
        this.opCode = opCode;
        this.decodifica = decodifica;
    }

    public String getDecodifica() {
        return decodifica;
    }

    public void esegui(Sistema sistema) {
        //TODO : un istruzione è della cpu ma viene eseguita su tutto il sistema
        //esempio quelle che coinvolgono letture/scritture su RAM o output ...

        System.out.println(" eseguo " + opCode + "  " + decodifica);

    }
}
