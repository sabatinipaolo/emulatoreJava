import java.util.EventObject;

public class CPUEvent extends EventObject {

    public CPUEvent(Object source) {
        super(source);

    }
}
class RegistroChangedEvent extends CPUEvent {

    private Registro registro;
    public RegistroChangedEvent(Object source, Registro registro) {

        super(registro);
        this.registro = registro;

    }
}
