import java.util.HashMap;
import java.util.Scanner;

public class Vista {
    Controller controller;
    private HashMap<Registro, VistaRegistro> viste;
    private boolean enable;


    public Vista() {
        enable = false;
    }

    public void setup(Controller controller) {
        this.controller = controller;

        viste = new HashMap<Registro, VistaRegistro>();
        viste.put(controller.sistema.cpu.MAR, new VistaRegistro(controller.sistema.cpu.MAR));
        viste.put(controller.sistema.cpu.MDR, new VistaRegistro(controller.sistema.cpu.MDR));
        viste.put(controller.sistema.cpu.IP, new VistaRegistro(controller.sistema.cpu.IP));
        viste.put(controller.sistema.cpu.IR, new VistaRegistro(controller.sistema.cpu.IR));
        viste.put(controller.sistema.cpu.A, new VistaRegistro(controller.sistema.cpu.A));
        viste.put(controller.sistema.cpu.B, new VistaRegistro(controller.sistema.cpu.B));
        viste.put(controller.sistema.cpu.C, new VistaRegistro(controller.sistema.cpu.C));
        viste.put(controller.sistema.cpu.D, new VistaRegistro(controller.sistema.cpu.D));
        viste.put(controller.sistema.cpu.RW, new VistaRegistro(controller.sistema.cpu.RW));
        viste.put(controller.sistema.addressBUS, new VistaRegistro(controller.sistema.addressBUS));
        viste.put(controller.sistema.dataBUS, new VistaRegistro(controller.sistema.dataBUS));
        viste.put(controller.sistema.RW, new VistaRegistro(controller.sistema.RW));

        for (int i = 0; i < 16; i++) {
            viste.put(controller.sistema.RAM.mem[i], new VistaRegistro(controller.sistema.RAM.mem[i]));
        }
        ;

    }

    public void inizia() {
        stampaTutto();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("premi invio PER AVVIARE ");
        input = scanner.nextLine();

        while (!input.equals("END")) {
           if (input.equals("")) {
                controller.notificaAlSistemaImpulsoDiClock();
            }
            System.out.println("premi invio");
            input = scanner.nextLine();
        } ;

        scanner.close();
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
        vSisRW = viste.get(controller.sistema.RW);

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
                System.out.print("    ");
                vr.stampaNome();
                System.out.print(" ");

            }
            ;
        }

        System.out.println();
        System.out.println("STATO CPU = "+controller.getStatoCpu());
        System.out.println();

        if(controller.getStatoCpu().equals("DECODE0"))
            System.out.println("istruzione ="+ controller.getDecodifica());
        else System.out.println();
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
            valoreAStampa = prefisso + " " + registro.getNome() + "=" + String.format("%1$5s", "");
            ;
        } else {
            valoreAStampa = prefisso + " " + registro.getNome() + "=" + String.format("%1$5s", String.valueOf(valore));
        }

        valoreAStampa = String.format("%1$9s", valoreAStampa);
        System.out.print(valoreAStampa);
    }

    public void stampaNome() {

        System.out.print(String.format("%1$3s", registro.getNome()));
    }

    public void stampaValore() {
        System.out.print(prefisso + " " + String.format("%1$3s", String.valueOf(valore)));
    }
}