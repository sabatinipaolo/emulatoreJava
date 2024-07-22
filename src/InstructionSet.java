import java.util.Map;

public class InstructionSet {
    private CPU cpu;

    private Map<Integer, Istruzione> decodifica;

    public InstructionSet(CPU cpu) {
        this.cpu = cpu;
    }
}
