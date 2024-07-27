public class Memory {
    private Registro[] mem;

    private Sistema sistema;  //TODO: vedi se necessario

    public Memory(Sistema sistema) {

        mem = new Registro[16];

        mem[0] = new Registro(33);
        mem[1] = new Registro(34);
        mem[2] = new Registro(35);
        mem[3] = new Registro(36);
        mem[4] = new Registro(38);
        mem[5] = new Registro(39);
        mem[6] = new Registro(6);
        mem[7] = new Registro(7);
        mem[8] = new Registro(8);
        mem[9] = new Registro(9);
        mem[10] = new Registro(10);
        mem[11] = new Registro(11);
        mem[12] = new Registro(12);
        mem[13] = new Registro(13);
        mem[14] = new Registro(14);
        mem[15] = new Registro(15);

    }

    public int getValore(int indirizzo) {

        return mem[indirizzo].getValore();

    }

    public Registro get(int indirizzo) {
        return mem[indirizzo];
    }
}
