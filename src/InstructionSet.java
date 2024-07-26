import java.util.HashMap;

public class InstructionSet {
    private CPU cpu;

    private HashMap<Integer, Istruzione> istruzioni;

    public InstructionSet(CPU cpu) {
        this.cpu = cpu;
        this.istruzioni = new HashMap<Integer, Istruzione>();
        // opcode 0
        {
            int opCode = 0;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC A") {
                public void esegui() {
                    cpu.A.inc();
                }

            }));
        }
        // opcode 1
        {
            int opCode = 1;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC B") {
                public void esegui() {
                    cpu.B.inc();
                }

            }));
        }
        // opcode 2
        {
            int opCode = 2;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC C") {
                public void esegui() {
                    cpu.C.inc();
                }
            }));
        }
        // opcode 3
        {
            int opCode = 3;
            istruzioni.put(opCode, (new Istruzione(opCode, "INC D") {
                public void esegui() {
                    cpu.D.inc();
                }
            }));
        }

        // opcode 4
        {
            int opCode = 4;
            istruzioni.put(opCode, (new Istruzione(opCode, "DEC A") {
                public void esegui() {
                    cpu.A.dec();
                }

            }));
        }
        // opcode 5
        {
            int opCode = 5;
            istruzioni.put(opCode, (new Istruzione(opCode, "DEC B") {
                public void esegui() {
                    cpu.B.dec();
                }

            }));
        }
        // opcode 6
        {
            int opCode = 6;
            istruzioni.put(opCode, (new Istruzione(opCode, "DEC C") {
                public void esegui() {
                    cpu.C.dec();
                }
            }));
        }
        // opcode 7
        {
            int opCode = 7;
            istruzioni.put(opCode, (new Istruzione(opCode, "DEC D") {
                public void esegui() {
                    cpu.D.dec();
                }
            }));
        }
        // opcode 8
        {
            int opCode = 8;
            istruzioni.put(opCode, (new Istruzione(opCode, "MOV A, [ ind ]") {
                public void esegui() {

                    cpu.letturaDaMemoria(cpu.IP, cpu.MAR); //legge operando

                    cpu.incCiclo();
                    cpu.letturaDaMemoria(cpu.MAR, cpu.A); // [operando] -> A

                    cpu.incCiclo();
                    cpu.IP.inc();


                }
            }));
        }

        // opcode 17
        {
            int opCode = 17;
            istruzioni.put(opCode, (new Istruzione(opCode, "MOV A, B") {
                public void esegui() {
                    cpu.A.muoviValoreIn(cpu.B);
                }
            }));
        }
        // opcode 18
        {
            int opCode = 18;
            istruzioni.put(opCode, (new Istruzione(opCode, "MOV A, C") {
                public void esegui() {
                    cpu.A.muoviValoreIn(cpu.B);
                }
            }));
        }
        // opcode 19
        {
            int opCode = 19;
            istruzioni.put(opCode, (new Istruzione(opCode, "MOV A, D") {
                public void esegui() {
                    cpu.A.muoviValoreIn(cpu.D);

                }
            }));
        }


    }

    public Istruzione getIstruzione(int opCode) {
        Istruzione istruzione = istruzioni.get(opCode);
        if (istruzione == null) return (new Istruzione(opCode, "NOP ") {
            public void esegui() {
            }
        });
        return istruzioni.get(opCode);

    }
}
