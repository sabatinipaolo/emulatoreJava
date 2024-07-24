import java.util.EventListener;

public interface CPUListener extends EventListener {

    void onCPUEvent(CPUEvent event);

    void onRegistroChanged(RegistroChangedEvent event);

    void onRegistroRead(RegistroReadEvent event);

    void onCpuHaFinitoCicloDiClockEvent(CpuHaFinitoCicloDiClockEvent event);

    void onCpuAspettaImpulsoDiClockEvent(CpuAspettaImpulsoDiClockEvent event);
}

