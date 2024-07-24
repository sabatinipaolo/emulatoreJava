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

    public void registroHaCambiatoValoreIn(Registro registro, int valore) {
  //TODO: rimuovere
//        VistaRegistro v = vista.get(registro);
//        v.setValore(valore);
//        v.scritto();
//        utilizzati.add(registro);
    }

    public void registroHaAvutoAccesso(Registro registro) {
//TODO :rimuovere
//        if (vista.isNotEnabled()) return;
//
//        VistaRegistro v = vista.get(registro);
//        v.letto();
//
//        utilizzati.add(registro);

    }






    public String getStatoCpu(){
        return sistema.getStatoCpu();
    }

    public String getDecodifica() {
        return ( sistema.cpu.getDecodifica());

    }

    public void cpuAspettaUnCicloDiClock() {
        vista.aspettaComando( "", "inserisci un comando o invio per un ciclo di clock :" );
    }

    public void avviaSistema() {

        sistema.cpu.avvia();
    }
}
