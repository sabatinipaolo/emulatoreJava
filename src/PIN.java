public class PIN extends Registro {
    private int valore;
    private String nome;
    private Controller controller;

    public PIN(String nome, int valore, Controller controller) {
        super(nome, valore, controller);
    }

    public void setToZero() {
        setValore(0);
    }

    public void setToUno() {
        setValore(1);
    }

    public void setToUndefined() {
        setValore(-1);
    }

}
