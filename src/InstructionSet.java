import java.util.HashMap;

public class InstructionSet {
    private CPU cpu;

    private HashMap<Integer, Istruzione> istruzioni;

    public InstructionSet(CPU cpu) {
        this.cpu = cpu;
        this.istruzioni = new HashMap<Integer, Istruzione>();

        String[] decodificaRegistroInStringa = {"A", "B", "C", "D"};
        Registro[] decodificaRegistro = {cpu.A, cpu.B, cpu.C, cpu.D};


        HashMap<Integer, String> reg = new HashMap<>();
        {
            reg.put(0, "A");
            reg.put(1, "B");
            reg.put(2, "C");
            reg.put(3, "D");
        }

        //MOV reg ,reg
        {
            int opCodeBase = 0;
            String istruzione = "MOV ";
            int opCode;

            for (int is = 0; is < 4; is++) {
                for (int id = 0; id < 4; id++) {
                    if (is == id) continue;
                    int opCodeSorgente = 4 * is;
                    int opCodeDestinazione = id;
                    String decodifica = istruzione + decodificaRegistroInStringa[is] + ", " + decodificaRegistroInStringa[id];
                    opCode = opCodeBase + opCodeSorgente + opCodeDestinazione;

                    Registro regSrc = decodificaRegistro[is];
                    Registro regDst = decodificaRegistro[id];
                    //System.out.println( is+ " " + id+"    > "+ opCode + " "+ decodifica );
                    istruzioni.put(opCode, (new Istruzione(opCode, decodifica) {
                        public void esegui() {
                            regSrc.muoviValoreIn(regDst);
                        }

                    }));

                }
            }
        }

        //ADD reg ,reg //TODO : completare con mod 256 e flag
        {
            int opCodeBase = 32;
            int opCode;
            String istruzione = "ADD ";

            for (int is = 0; is < 4; is++) {
                for (int id = 0; id < 4; id++) {
                    if (is == id) continue;
                    int opCodeSorgente = 4 * is;
                    int opCodeDestinazione = id;
                    String decodifica = istruzione + decodificaRegistroInStringa[is] + ", " + decodificaRegistroInStringa[id];
                    opCode = opCodeBase + opCodeSorgente + opCodeDestinazione;

                    Registro regSrc = decodificaRegistro[is];
                    Registro regDst = decodificaRegistro[id];
                    System.out.println(is + " " + id + "    > " + opCode + " " + decodifica);
                    istruzioni.put(opCode, (new Istruzione(opCode, decodifica) {
                        public void esegui() {
                            int valore1 = regSrc.getValore();
                            int valore2 = regDst.getValore();
                            regSrc.setValore(valore1 + valore2);


                        }

                    }));

                }
            }


        }

        //SUB reg,reg //TODO : completare con mod 256 e flag

    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        CPU cpu = sistema.cpu;
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
