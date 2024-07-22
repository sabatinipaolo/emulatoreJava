public class BUS extends Registro {
    public BUS(String nome, int valore, Controller controller) {
        super(nome, valore, controller);
    }

    public void setUndefined() {
        setValore(-1);
    }
}
