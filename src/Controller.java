import java.util.ArrayList;

public class Controller {

    public static Sistema sistema;
    public static Vista vista;
    public static void main(String[] args) {

        Sistema sistema= new Sistema( );
        Vista vista= new Vista( sistema );

        sistema.addCPUListener(vista);
        sistema.cpu.addCPUListener(vista);

        vista.inizia();
    }

}
