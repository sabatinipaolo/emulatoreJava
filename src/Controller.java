import java.util.ArrayList;

public class Controller {

    public  static Sistema sistema;
    public static Vista vista;

    private ArrayList<Registro> utilizzati = new ArrayList<Registro>();

    public static void main(String[] args) {

        Controller controller = new Controller();
        sistema = new Sistema();
        vista = new Vista();

        controller.setVistaEModello(vista, sistema);



        sistema.setup(controller);
        vista.setup(controller,sistema);

        sistema.addCPUListener(vista);
        sistema.cpu.addCPUListener(vista);

        vista.enable();
        vista.inizia();
    }

    public void setVistaEModello(Vista v, Sistema s) {
        this.sistema = s;
        this.vista = v;
    }

    public String getStatoCpu(){
        return sistema.getStatoCpu();
    }

    public String getDecodifica() {
        return ( sistema.cpu.getDecodifica());

    }

    public void avviaSistema() {

        sistema.cpu.avvia();
    }
}
