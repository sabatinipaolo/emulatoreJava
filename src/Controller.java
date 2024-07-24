import java.util.ArrayList;

public class Controller {

    public static Sistema sistema;
    public static Vista vista;
    public static void main(String[] args) {

        sistema = new Sistema( );
        vista = new Vista();
        Controller controller = new Controller(vista, sistema);

        vista.setup(controller, sistema);

        sistema.addCPUListener(vista);
        sistema.cpu.addCPUListener(vista);

        vista.enable();
        vista.inizia();
    }

    public Controller (Vista v, Sistema s) {
        this.sistema = s;
        this.vista = v;
    }

}
