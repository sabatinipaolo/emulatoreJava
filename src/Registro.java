public class Registro {
    private int valore;
    private Controller controller;

    public Registro( int valore, Controller controller) {
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

    public void setToUno() {
        setValore(1);
    }

    public void setToUndefined() {
        setValore(-1);
    }
}
