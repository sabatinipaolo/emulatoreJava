import java.util.HashMap;
import java.util.Scanner;

public class Vista {
    private HashMap<Registro, VistaRegistro> viste;
    Controller controller;
    private boolean enable;
    private VistaRegistro vA,vB,vC,vD,vIP,vIR,vSP,vFLAG,vMDR,vMAR,vRW;
    private VistaRegistro vAddressBus, vDataBus,  vSisRW;

    public Vista (){
        enable=false;
    }
    public void setup(Controller controller) {
        this.controller = controller;

        viste = new HashMap<Registro, VistaRegistro>();

        viste.put(controller.sistema.cpu.MAR, new VistaRegistro(controller.sistema.cpu.MAR));
        vMAR = viste.get(controller.sistema.cpu.MAR);

        viste.put(controller.sistema.cpu.MDR, new VistaRegistro(controller.sistema.cpu.MDR));
        vMDR = viste.get(controller.sistema.cpu.MDR);

        viste.put(controller.sistema.cpu.IP, new VistaRegistro(controller.sistema.cpu.IP));
        vIP  = viste.get(controller.sistema.cpu.IP);

        viste.put(controller.sistema.cpu.IR, new VistaRegistro(controller.sistema.cpu.IR));
        vIR  = viste.get(controller.sistema.cpu.IR);

        viste.put(controller.sistema.cpu.RW, new VistaRegistro(controller.sistema.cpu.RW));
        vRW  = viste.get(controller.sistema.cpu.RW);


        viste.put(controller.sistema.addressBUS, new VistaRegistro(controller.sistema.addressBUS));
        vAddressBus=viste.get(controller.sistema.addressBUS);

        viste.put(controller.sistema.dataBUS, new VistaRegistro(controller.sistema.dataBUS));
        vDataBus=viste.get(controller.sistema.dataBUS);

        viste.put(controller.sistema.RW, new VistaRegistro(controller.sistema.RW));
        vSisRW =viste.get(controller.sistema.RW);

        for (int i =0 ; i < 16 ; i++){
            viste.put(controller.sistema.RAM.mem[i], new VistaRegistro(controller.sistema.RAM.mem[i]));
        };

    }

    public void inizia() {
        stampaTutto();
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            if (input.equals("")) {
                controller.notificaAlSistemaImpulsoDiClock();
            }

        } while (!input.equals("END"));

        scanner.close();
    }

    public VistaRegistro get(Registro r) {
        return (viste.get(r));
    }

    public void enable(){
        enable=true;
    }
    public boolean isNotEnabled() {
        return ( ! enable );
    }

    public void stampaTutto() {



        //System.out.println();
        //System.out.println("-------------------------------");
        vIP.stampa();
        System.out.println();
        //System.out.print( "              " );
        vIR.stampa();
        System.out.println();

//        System.out.print("\n----");

        vMAR.stampa();
        System.out.println();
//        System.out.print( " --------" );
        vMDR.stampa();
        System.out.println();
//        System.out.println("\n\n\n");

        vRW.stampa();
        System.out.println();
        System.out.println( " --------" );

        vAddressBus.stampa();
        System.out.println();

        vSisRW.stampa();
        System.out.println();

        vDataBus.stampa();
        System.out.println();
        System.out.println();
        System.out.println();

        for (int i =0 ; i < 16 ; i++){
            VistaRegistro vr = viste.get(controller.sistema.RAM.mem[i]);
            vr.stampa();
            System.out.print(" | ");
        }
        System.out.println();
    }
}

class VistaRegistro {
    private int valore;
    private String valoreAStampa;
    private String prefisso;
    private Registro registro;

    public VistaRegistro(Registro registro) {
        prefisso = " ";
        this.registro = registro;

        setValore(registro.getValore());
        aggiornaValoreAStampa();

    }

    public void letto( ){
        prefisso="r";
        aggiornaValoreAStampa();
    }

    public void scritto( ){
        prefisso="w";
        aggiornaValoreAStampa();
    }
    private void aggiornaValoreAStampa() {

        if (valore == -1 ){
            prefisso = " ";
            valoreAStampa = prefisso + " "+ registro.getNome() ;
        } else {
            valoreAStampa = prefisso + " " + registro.getNome() + "=" + String.valueOf(valore);
        }
    }
    public void setValore(int valore) {
            this.valore=valore;
            aggiornaValoreAStampa();
    }
    public void setNonUtilizzato(){
        prefisso = " ";
        aggiornaValoreAStampa();
    }
    public void stampa() {
        System.out.print(valoreAStampa);
    }
    public String getValoreAStampa(){
        return valoreAStampa;
    }
}