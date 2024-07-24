import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Vista implements CPUListener {
    Controller controller;
    private HashMap<Registro, VistaRegistro> viste;
    private ArrayList<Registro> utilizzati = new ArrayList<Registro>();
    private boolean enable;


    public Vista() {
        enable = false;
    }

    public void setup(Controller controller, Sistema sistema) {
        this.controller = controller;


        viste = new HashMap<Registro, VistaRegistro>();
        viste.put(controller.sistema.cpu.MAR, new VistaRegistro("MAR", controller.sistema.cpu.MAR));
        viste.put(controller.sistema.cpu.MDR, new VistaRegistro("MDR",controller.sistema.cpu.MDR));
        viste.put(controller.sistema.cpu.IP, new VistaRegistro("IP",controller.sistema.cpu.IP));
        viste.put(controller.sistema.cpu.IR, new VistaRegistro("IR",controller.sistema.cpu.IR));
        viste.put(controller.sistema.cpu.A, new VistaRegistro("A",controller.sistema.cpu.A));
        viste.put(controller.sistema.cpu.B, new VistaRegistro("B",controller.sistema.cpu.B));
        viste.put(controller.sistema.cpu.C, new VistaRegistro("C",controller.sistema.cpu.C));
        viste.put(controller.sistema.cpu.D, new VistaRegistro("D",controller.sistema.cpu.D));
        viste.put(controller.sistema.cpu.RW, new VistaRegistro("RW",controller.sistema.cpu.RW));
        viste.put(controller.sistema.addressBUS, new VistaRegistro("address Bus",controller.sistema.addressBUS));
        viste.put(controller.sistema.dataBUS, new VistaRegistro("data Bus",controller.sistema.dataBUS));
        viste.put(controller.sistema.controlRW, new VistaRegistro("RW cntl Bus",controller.sistema.controlRW));

        for (int i = 0; i < 16; i++) {
            viste.put(controller.sistema.RAM.mem[i], new VistaRegistro("["+i+"]",controller.sistema.RAM.mem[i]));
        }
        ;

    }

    public void inizia() {
        stampaTutto();
        aspettaComando("" , "inserisci un comando o invio per AVVIARE :");
        controller.avviaSistema();
    }
    public void aspettaComando( String comando ,String messaggio ){
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.print(messaggio);
        input = scanner.nextLine();

        while (!(input.equals("END")  ) ){
            if (input.equals(comando)) {
                return;
            }
            System.out.print(messaggio);
            input = scanner.nextLine();
        } ;

        scanner.close();
        System.exit(0);
    }

    public VistaRegistro get(Registro r) {
        return (viste.get(r));
    }

    public void enable() {
        enable = true;
    }

    public boolean isNotEnabled() {
        return (!enable);
    }

    public void stampaTutto() {

        VistaRegistro vA, vB, vC, vD, vIP, vIR, vSP, vFLAG, vMDR, vMAR, vRW;
        VistaRegistro vAddressBus, vDataBus, vSisRW;

        vA = viste.get(controller.sistema.cpu.A);
        vB = viste.get(controller.sistema.cpu.B);
        vC = viste.get(controller.sistema.cpu.C);
        vD = viste.get(controller.sistema.cpu.D);

        vMAR = viste.get(controller.sistema.cpu.MAR);
        vMDR = viste.get(controller.sistema.cpu.MDR);
        vIP = viste.get(controller.sistema.cpu.IP);
        vIR = viste.get(controller.sistema.cpu.IR);
        vRW = viste.get(controller.sistema.cpu.RW);

        vAddressBus = viste.get(controller.sistema.addressBUS);
        vDataBus = viste.get(controller.sistema.dataBUS);
        vSisRW = viste.get(controller.sistema.controlRW);

        //riga
        {
            System.out.println("------------------------------------------------------------------------------------");
        }
        //riga vuota cpu
        {
            System.out.print("|                                                                                  |\n");
        }
        //riga
        {
            System.out.print("|  ");
            vA.stampa();
            System.out.print("     ");
            vB.stampa();
            System.out.print("     ");
            vC.stampa();
            System.out.print("     ");
            vD.stampa();
            System.out.println("                             |");
        }
        //riga vuota cpu
        {
            System.out.print("|                                                                                  |\n");
        }
        //riga  IP   IR
        {
            System.out.print("|  ");
            vIP.stampa();
            System.out.print("                                ");
            vIR.stampa();
            System.out.println("                            |");
        }
        //riga vuota cpu
        {
            System.out.print("|                                                                                  |\n");
        }
        //riga  MAR RW MDR
        {
            System.out.print("--------");
            vMAR.stampa();
            System.out.print("  --------------");
            vRW.stampa();
            System.out.print(" -------------");
            vMDR.stampa();
            System.out.println(" -------------");
        }
        //riga
        {
            System.out.println("             |      |                       |                   /      \\");
            System.out.println("             |      |                       |                  /_      _\\");
            System.out.println("             |      |                       |                   |      | ");

        }
        //riga
        {
            vAddressBus.stampa();
            System.out.print(" |     ");

            vSisRW.stampa();
            System.out.print("         ");

            vDataBus.stampa();
            System.out.println(" |");
        }
        //riga
        {
            System.out.println("            _|      |_                      |                  _|      |_");
            System.out.println("            \\        /                      |                  \\        /");
            System.out.println("             \\      /                       |                   \\      /");

        }
        //riga
        {
            for (int i = 0; i < 16; i++) {
                System.out.print("|-------");
            }
            System.out.println("|");
        }
        //riga
        {
            for (int i = 0; i < 16; i++) {
                VistaRegistro vr = viste.get(controller.sistema.RAM.mem[i]);
                System.out.print("| ");
                vr.stampaValore();
                System.out.print(" ");
            }
            System.out.println("|");
        }

        //riga
        {
            for (int i = 0; i < 16; i++) {
                System.out.print("|-------");
            }
            System.out.println("|");
        }

        //riga
        {
            for (int i = 0; i < 16; i++) {
                VistaRegistro vr = viste.get(controller.sistema.RAM.mem[i]);
                System.out.print("  ");
                System.out.print( String.format("%1$3s",i));
                System.out.print("   ");

            }
            ;
        }

        System.out.println();
        System.out.println("STATO CPU = "+controller.getStatoCpu());
        System.out.println();

        if(controller.sistema.isInDecodeOrExecute() )
            System.out.println("istruzione ="+ controller.getDecodifica());
        else System.out.println();
        System.out.println();
    }


    @Override
    public void onCPUEvent(CPUEvent event) {

    }

    @Override
    public void onRegistroChanged(RegistroChangedEvent event) {

        Registro registro = (Registro) event.getSource();

        System.out.print ( "##### MODIFICATO "+ event.valore );
        viste.get(registro).stampa();

        System.out.println();

        VistaRegistro v = viste.get(registro);
        v.setValore(event.valore);
        v.scritto();
        utilizzati.add(registro);

    }

    @Override
    public void onRegistroRead(RegistroReadEvent event) {
        Registro registro = (Registro) event.getSource();

        System.out.print ( "##### LETTO      " );

        viste.get(registro).stampa();

        System.out.println();

        if ( isNotEnabled()) return;

        //VistaRegistro v = vista.get(registro);

        VistaRegistro v = viste.get(registro);

        v.letto();

        utilizzati.add(registro);



    }

    @Override
    public void onCpuHaFinitoCicloDiClockEvent(CpuHaFinitoCicloDiClockEvent event) {
        stampaTutto();
        svuota(utilizzati);
        System.out.println("Controller. finita Cilco di clock " + controller.sistema.cpu.getStatoECiclo());


    }

    @Override
    public void onCpuAspettaImpulsoDiClockEvent(CpuAspettaImpulsoDiClockEvent event) {
        aspettaComando( "", "inserisci un comando o invio per un ciclo di clock :" );
    }

    public void svuota(ArrayList<Registro> utilizzati) {

        for (Registro r : utilizzati) {
            VistaRegistro v = viste.get(r);
            v.setNonUtilizzato();
        }
        ;
        utilizzati.clear();

    }
}


class VistaRegistro {
    private int valore;
    private String valoreAStampa;
    private String prefisso;
    private Registro registro;
    private String nome;

    public VistaRegistro(String nome , Registro registro) {
        prefisso = " ";
        this.nome = nome;
        this.registro = registro;
        setValore(registro.getValore());

    }

    public void setValore(int valore) {
        this.valore = valore;

    }

    public void letto() {
        prefisso = "r";

    }

    public void scritto() {
        prefisso = "w";

    }

    public void setNonUtilizzato() {
        prefisso = " ";

    }

    public void stampa() {
        String valoreAStampa;
        if (valore == -1) {
            prefisso = " ";
            valoreAStampa = prefisso + " " + nome + "=" + String.format("%1$5s", "");
            ;
        } else {
            valoreAStampa = prefisso + " " + nome + "=" + String.format("%1$5s", String.valueOf(valore));
        }

        valoreAStampa = String.format("%1$9s", valoreAStampa);
        System.out.print(valoreAStampa);
    }

    public void stampaNome() {

        System.out.print(String.format("%1$3s", nome));
    }

    public void stampaValore() {
        System.out.print(prefisso + " " + String.format("%1$3s", String.valueOf(valore)));
    }
}