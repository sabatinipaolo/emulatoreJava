public class Registro extends CPUEventFirer {
    private int valore;

    public Registro(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        fireRegistroRead(new RegistroReadEvent(this));

        return valore;
    }

    public void setValore(int valore) {

        this.valore = valore;
        fireRegistroChangedValue(new RegistroChangedEvent(this, this.valore));

    }

    public int muoviValoreIn ( Registro dest ){

        int valore = this.getValore();
        dest.setValore( valore );
        return valore;

    }

    public void inc(){
        setValore( ++ this.valore );

    }

    public void dec(){
        setValore( -- this.valore );

    }
    public void setToUndefined() {
        setValore(-1);
    }
}
