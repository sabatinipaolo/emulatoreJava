import java.util.EventObject;

public class CPUEvent extends EventObject {
    public Registro registro;

    public CPUEvent(Object source) {
        super(source);

    }
}

class RegistroChangedEvent extends CPUEvent {
    public int valore;

    public RegistroChangedEvent(Object source, int valore) {

        super(source);
        this.registro = (Registro) source;
        this.valore = valore;

    }
}

class RegistroReadEvent extends CPUEvent {
    public RegistroReadEvent(Object source) {

        super(source);
        this.registro = (Registro) source;

    }
}

class CpuHaFinitoCicloDiClockEvent extends CPUEvent {
    public CpuHaFinitoCicloDiClockEvent(Object source) {

        super(source);
    }
}

class CpuAspettaImpulsoDiClockEvent extends CPUEvent {
    public CpuAspettaImpulsoDiClockEvent(Object source) {

        super(source);
    }
}