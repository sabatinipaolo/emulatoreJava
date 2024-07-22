public class Memory {
    Registro[] mem;
    private Controller controller;

    public Memory(Controller controller) {

        mem = new Registro[16];

        mem[0] = new Registro( 8, controller);
        mem[1] = new Registro( 15, controller);
        mem[2] = new Registro( 18, controller);
        mem[3] = new Registro( 19, controller);
        mem[4] = new Registro( 0, controller);
        mem[5] = new Registro( 1, controller);
        mem[6] = new Registro( 2, controller);
        mem[7] = new Registro( 3, controller);
        mem[8] = new Registro( 0, controller);
        mem[9] = new Registro( 0, controller);
        mem[10] = new Registro( 0, controller);
        mem[11] = new Registro( 0, controller);
        mem[12] = new Registro( 0, controller);
        mem[13] = new Registro( 0, controller);
        mem[14] = new Registro( 0, controller);
        mem[15] = new Registro( 33, controller);

    }

    public int getValore(int indirizzo) {

        return mem[indirizzo].getValore();

    }
}
