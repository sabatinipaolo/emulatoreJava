public class Memory {
    private Registro[] mem;

    private Sistema sistema;  //TODO: vedi se necessario
    public Memory(Sistema sistema) {

        mem = new Registro[16];

        mem[0] = new Registro( 0);
        mem[1] = new Registro( 8);
        mem[2] = new Registro( 15);
        mem[3] = new Registro( 200);
        mem[4] = new Registro( 0);
        mem[5] = new Registro( 1);
        mem[6] = new Registro( 2);
        mem[7] = new Registro( 3);
        mem[8] = new Registro( 0);
        mem[9] = new Registro( 0);
        mem[10] = new Registro( 0);
        mem[11] = new Registro( 0);
        mem[12] = new Registro( 0);
        mem[13] = new Registro( 0);
        mem[14] = new Registro( 0);
        mem[15] = new Registro( 33);

    }

    public int getValore(int indirizzo) {

        return mem[indirizzo].getValore();

    }

    public Registro get(int indirizzo) {
        return  mem[indirizzo];
    }
}
