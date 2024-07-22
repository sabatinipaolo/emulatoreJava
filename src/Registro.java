public class Registro {
    private int valore;
    private String nome;
    private Controller controller;

    public Registro(String nome, int valore, Controller controller) {
        this.nome = nome;
        this.valore = valore;
        this.controller = controller;
    }

    public int getValore() {
        controller.registroHaAvutoAccesso(this);
        return valore;
    }

    public void setValore(int valore) {

        this.valore = valore;
        controller.registroHaCambiatoValoreIn(this, valore);
    }

    public String getNome() {
        return nome;
    }
}
