import java.util.ArrayList;
import java.util.List;

public class CPUEventFirer {
    private List<CPUListener> listeners;

    public CPUEventFirer() {
        this.listeners = new ArrayList<>();
    }

    public void addCPUListener(CPUListener listener) {
        listeners.add(listener);
    }

    public void removeCPUListener(CPUListener listener) {
        listeners.remove(listener);
    }

    public void fireCpuEvent(CPUEvent event) {
        for (CPUListener listener : listeners) {
            listener.onCPUEvent(event);
        }
    }

    public void fireRegistroChangedValue(RegistroChangedEvent event) {
        for (CPUListener listener : listeners) {
            listener.onRegistroChanged(event);
        }
    }

    public void fireRegistroRead(RegistroReadEvent event) {
        for (CPUListener listener : listeners) {
            listener.onRegistroRead(event);
        }
    }

    public void fireCpuHaFinitoCicloDiClockEvent(CpuHaFinitoCicloDiClockEvent event) {
        for (CPUListener listener : listeners) {
            listener.onCpuHaFinitoCicloDiClockEvent(event);
        }
    }

    public void fireCpuAspettaImpulsoDiClockEvent(CpuAspettaImpulsoDiClockEvent event) {
        for (CPUListener listener : listeners) {
            listener.onCpuAspettaImpulsoDiClockEvent(event);
        }
    }
}
