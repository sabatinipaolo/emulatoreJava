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
                    sistema.cpu.incRegistro( sistema.cpu.A);
                }

            }));
        }
        // opcode 1
        {   int opCode = 1;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC B") {
                public void esegui(Sistema sistema) {
                    sistema.cpu.incRegistro( sistema.cpu.B);
                }

            }));
        }
        // opcode 2
        {   int opCode = 2;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC C") {
                public void esegui(Sistema sistema) {
                    sistema.cpu.incRegistro( sistema.cpu.C);
                }
            }));
        }
        // opcode 3
        {   int opCode = 3;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC D") {
                public void esegui(Sistema sistema) {
                    sistema.cpu.incRegistro( sistema.cpu.D);
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
