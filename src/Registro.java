public class Registro {
    private int valore;

    public Registro( int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {

        this.valore = valore;
    }

    public void setToUno() {
        setValore(1);
    }

    public void setToUndefined() {
        setValore(-1);
    }
}
