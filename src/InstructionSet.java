import java.util.HashMap;

public class InstructionSet {
    private CPU cpu;

    private HashMap<Integer, Istruzione> istruzioni;

    public InstructionSet(CPU cpu) {
        this.cpu = cpu;
        this.istruzioni = new HashMap<Integer,Istruzione>();

        {   int opCode = 0;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC A") {
                public void esegui(Sistema sistema) {
                    CPU cpu = sistema.cpu;

                    int operando = cpu.A.getValore() + 1;
                    cpu.A.setValore(operando);

                }

            }));
        }
        {   int opCode = 1;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC B") {
                public void esegui(Sistema sistema) {
                    CPU cpu = sistema.cpu;

                    int operando = cpu.B.getValore() + 1;
                    cpu.B.setValore(operando);

                }

            }));
        }
    }

    public String decodifica(int opCode){

        Istruzione istruzione = istruzioni.get(opCode);
        if (istruzione==null) return " NOP !!!" ;
        return istruzione.getDecodifica();

    }

    public void esegui(int opCode , Sistema sistema) {

        Istruzione istruzione = istruzioni.get(opCode);
        if (istruzione==null) return  ;

        istruzione.esegui( sistema );
    }
}
