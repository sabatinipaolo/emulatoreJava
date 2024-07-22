import java.util.HashMap;

public class InstructionSet {
    private CPU cpu;

    private HashMap<Integer, Istruzione> istruzioni;

    public InstructionSet(CPU cpu) {
        this.cpu = cpu;
        this.istruzioni = new HashMap<Integer,Istruzione>();
        // opcode 0
        {   int opCode = 0;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC A") {
                public void esegui(Sistema sistema) {
                    CPU cpu = sistema.cpu;

                    int operando = cpu.A.getValore() + 1;
                    cpu.A.setValore(operando);

                }

            }));
        }
        // opcode 1
        {   int opCode = 1;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC B") {
                public void esegui(Sistema sistema) {
                    CPU cpu = sistema.cpu;

                    int operando = cpu.B.getValore() + 1;
                    cpu.B.setValore(operando);

                }

            }));
        }
        // opcode 2
        {   int opCode = 2;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC C") {
                public void esegui(Sistema sistema) {
                    CPU cpu = sistema.cpu;

                    int operando = cpu.C.getValore() + 1;
                    cpu.C.setValore(operando);
                }
            }));
        }
        // opcode 3
        {   int opCode = 3;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC D") {
                public void esegui(Sistema sistema) {
                    CPU cpu = sistema.cpu;

                    int operando = cpu.D.getValore() + 1;
                    cpu.D.setValore(operando);
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
