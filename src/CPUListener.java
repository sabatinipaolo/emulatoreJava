import java.util.EventListener;

public interface CPUListener extends EventListener {

    void onCPUEvent(CPUEvent event);

    void onRegistroChanged ( RegistroChangedEvent event);
}

