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

    public void esegui() {

    }
}
